package adapter.agent.translator;

import adapter.agent.AgentParams;
import adapter.agent.Translator;

public class TranslatorFactory {
    
    public static Translator build(String translator) {
        switch (translator.toLowerCase()) {
            case "troka":
                return new TrokaTranslator(AgentParams.isStarting(), AgentParams.getBoardHeight());
            case "zhixiangli":
                return new ZhixiangliTranslator(AgentParams.isStarting(), AgentParams.getBoardWidth(), AgentParams.getBoardHeight());
            case "blupig":
                return new BlupigTranslator(AgentParams.isStarting(), AgentParams.getBoardWidth(), AgentParams.getBoardHeight());
            default:
                throw new IllegalArgumentException("Translator for given agent doesn't exist");
        }
    }
}
