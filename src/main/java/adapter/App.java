package adapter;

import adapter.agent.Agent;
import adapter.agent.AgentParams;

public class App {

    public static void main(String[] args) {
        try {
            AgentParams.load(args);
            try (Agent trokaAgent = Agent.build()) {
                trokaAgent.startGame();
                trokaAgent.run();
            }
        } catch (Exception e) {
            System.err.println("[ERROR] " + e.getMessage());
        }
    }
}
