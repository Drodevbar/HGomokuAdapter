package adapter;

import adapter.agent.Agent;
import adapter.agent.AgentParams;
import adapter.translator.Translatable;
import adapter.translator.TranslatorFactory;

public class App {

    public static void main(String[] args) {
        try {
            AgentParams.load(args);
            Translatable translator = TranslatorFactory.build(AgentParams.getTranslator());
            try (Agent agent = Agent.build(translator)) {
                agent.startGame();
                agent.run();
            }
        } catch (Exception e) {
            System.err.println("[ERROR] " + e.getMessage());
        }
    }
}
