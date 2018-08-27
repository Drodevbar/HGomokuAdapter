package adapter.translator;

import adapter.agent.AgentParams;

public class TranslatorFactory {
    
    public static Translator build(String translator) {
        switch (translator.toLowerCase()) {
            case "troka":
                return new TrokaTranslator(AgentParams.isStarting(), AgentParams.getBoardHeight());
            case "zhixiangli":
                return new ZhixiangliTranslator(AgentParams.isStarting(), AgentParams.getBoardWidth(), AgentParams.getBoardHeight());
            default:
                throw new IllegalArgumentException("Given translator does not exist.");
        }
    }
}
