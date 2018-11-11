package adapter;

import adapter.agent.AgentParams;
import adapter.agent.TranslatorFactory;
import adapter.agent.Translator;

public class App {

    public static void main(String[] args) {
        try {
            AgentParams.load(args);
            Translator translator = TranslatorFactory.build(AgentParams.getTranslator());
            try (Agent agent = Agent.build(translator)) {
                agent.startGame();
                agent.run();
            }
        } catch (Exception e) {
            System.err.println("[ERROR] " + e.getMessage());
        }
    }
}
