package adapter.agent;

import adapter.Translator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

abstract public class Agent implements Closeable {

    private final Process process;
    private final BufferedWriter toProcess;
    private final BufferedReader fromProcess;
    private final Scanner scanner;
    
    public static Agent build() throws IOException {
        return AgentParams.isStarting() ? new StartingAgent() : new FollowingAgent();
    }

    Agent() throws IOException {
        this.process = buildProcessByCmd(AgentParams.getCommand());
        toProcess = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        fromProcess = new BufferedReader(new InputStreamReader(process.getInputStream()));
        scanner = new Scanner(System.in);
        Translator.initialize(AgentParams.isStarting());
        
        if (!process.isAlive()) {
             throw new IOException("Can not start process.");
        }
    }
    
    private Process buildProcessByCmd(String cmd) throws IOException {
        ProcessBuilder builder = new ProcessBuilder();
        builder.redirectErrorStream(true);
        builder.command(cmd.split(" "));
        
        return builder.start();
    }

    public void startGame() throws IOException {
        toProcess.write(Translator.startGame());
        toProcess.flush();
    }

    void doTurn(int power) throws IOException {
        for (int i = 0; i < power; i++) {
            String line = fromProcess.readLine();
            while (!Translator.isMoveProvided(line)) {
                line = fromProcess.readLine();
            }
            System.out.println(Translator.translateMoveToHGomoku(line));
        }
    }

    void handleHGomokuTurn(int power) throws IOException {
        for (int i = 0; i < power; i++) {
            toProcess.write(Translator.translateMoveToTroka(scanner.nextLine()));
            toProcess.flush();
        }
    }

    void doTurn() throws IOException {
        doTurn(AgentParams.getTurnPower());
    }

    void handleHGomokuTurn() throws IOException {
        handleHGomokuTurn(AgentParams.getTurnPower());
    }

    @Override
    public void close() throws IOException {
        toProcess.write(Translator.endGame());
        toProcess.flush();
        toProcess.close();
        fromProcess.close();
        process.destroy();
        scanner.close();
    }
    
    public abstract void run() throws IOException;
}
