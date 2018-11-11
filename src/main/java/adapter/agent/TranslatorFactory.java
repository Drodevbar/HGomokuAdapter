package adapter.agent;

import adapter.agent.translator.BlupigTranslator;
import adapter.agent.translator.CloudictTranslator;
import adapter.agent.translator.TrokaTranslator;
import adapter.agent.translator.ZhixiangliTranslator;

public class TranslatorFactory {
    
    public static Translator build(String translator) {
        switch (translator.toLowerCase()) {
            case "troka":
                return new TrokaTranslator(AgentParams.isStarting());
            case "zhixiangli":
                return new ZhixiangliTranslator(AgentParams.isStarting());
            case "blupig":
                return new BlupigTranslator(AgentParams.isStarting());
            case "cloudict":
                return new CloudictTranslator(AgentParams.isStarting());
            default:
                throw new IllegalArgumentException("Translator for given agent doesn't exist");
        }
    }
}
