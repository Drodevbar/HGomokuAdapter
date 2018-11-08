package adapter.agent.communication;

import adapter.agent.AgentParams;
import adapter.agent.CommunicationHandler;
import adapter.agent.Translator;

import java.io.*;

public class Process extends CommunicationHandler {

    private final java.lang.Process processManager;
    private final BufferedWriter toProcess;
    private final BufferedReader fromProcess;

    public Process(Translator translator) throws IOException {
        super(translator);

        processManager = buildProcessByCmd(AgentParams.getCommand());
        toProcess = new BufferedWriter(new OutputStreamWriter(processManager.getOutputStream()));
        fromProcess = new BufferedReader(new InputStreamReader(processManager.getInputStream()));

        if (!processManager.isAlive()) {
            throw new IOException("Can not start process.");
        }
    }

    @Override
    public void startGame() throws IOException {
        toProcess.write(translator.startGame());
        toProcess.flush();
    }

    @Override
    public String doTurn() throws IOException {
        String line = fromProcess.readLine();
        while (!translator.isMoveProvided(line)) {
            line = fromProcess.readLine();
        }
        return translator.translateMoveToHGomoku(line);
    }

    @Override
    public void handleHGomokuTurn(String move) throws IOException {
        toProcess.write(translator.translateMoveFromHGomoku(move));
        toProcess.flush();
    }

    @Override
    public void close() throws IOException {
        toProcess.write(translator.endGame());
        toProcess.flush();
        toProcess.close();
        fromProcess.close();
        processManager.destroy();
    }

    private java.lang.Process buildProcessByCmd(String cmd) throws IOException {
        ProcessBuilder builder = new ProcessBuilder();
        builder.redirectErrorStream(true);
        builder.command(cmd.split(" "));

        return builder.start();
    }
}
