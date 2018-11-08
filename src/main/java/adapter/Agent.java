package adapter;

import adapter.agent.AgentParams;
import adapter.agent.CommunicationHandler;
import adapter.agent.Translator;
import adapter.agent.kind.FollowingAgent;
import adapter.agent.kind.StartingAgent;

import java.io.*;
import java.util.Scanner;

abstract public class Agent implements Closeable {

    private final CommunicationHandler communicationHandler;
    private final Scanner scanner;
    
    public static Agent build(Translator translator) throws Exception {
        CommunicationHandler communicationHandler = (CommunicationHandler) Class
                .forName(translator.protocolType())
                .getConstructor(Translator.class)
                .newInstance(translator);

        return AgentParams.isStarting() ? new StartingAgent(communicationHandler) : new FollowingAgent(communicationHandler);
    }

    public Agent(CommunicationHandler communicationHandler) {
        this.communicationHandler = communicationHandler;
        scanner = new Scanner(System.in);
    }

    public void startGame() throws Exception {
        communicationHandler.startGame();
    }

    protected void doTurn(int power) throws Exception {
        for (int i = 0; i < power; i++) {
            System.out.println(communicationHandler.doTurn());
        }
    }

    protected void handleHGomokuTurn(int power) throws Exception {
        for (int i = 0; i < power; i++) {
            communicationHandler.handleHGomokuTurn(scanner.nextLine());
        }
    }

    @Override
    public void close() throws IOException {
        communicationHandler.close();
        scanner.close();
    }
    
    public abstract void run() throws Exception;
}
