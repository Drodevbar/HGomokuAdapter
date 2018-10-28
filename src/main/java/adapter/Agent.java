package adapter;

import adapter.agent.AgentParams;
import adapter.agent.Protocol;
import adapter.agent.Translator;
import adapter.agent.kind.FollowingAgent;
import adapter.agent.kind.StartingAgent;

import java.io.*;
import java.util.Scanner;

abstract public class Agent implements Closeable {

    private final Protocol protocol;
    private final Scanner scanner;
    
    public static Agent build(Translator translator) throws Exception {
        Protocol protocol = (Protocol) Class
                .forName(translator.protocolType())
                .getConstructor(Translator.class)
                .newInstance(translator);

        return AgentParams.isStarting() ? new StartingAgent(protocol) : new FollowingAgent(protocol);
    }

    public Agent(Protocol protocol) {
        this.protocol = protocol;
        scanner = new Scanner(System.in);
    }

    public void startGame() throws Exception {
        protocol.startGame();
    }

    protected void doTurn(int power) throws Exception {
        for (int i = 0; i < power; i++) {
            System.out.println(protocol.doTurn());
        }
    }

    protected void handleHGomokuTurn(int power) throws Exception {
        for (int i = 0; i < power; i++) {
            protocol.handleHGomokuTurn(scanner.nextLine());
        }
    }

    @Override
    public void close() throws IOException {
        protocol.close();
        scanner.close();
    }
    
    public abstract void run() throws Exception;
}
