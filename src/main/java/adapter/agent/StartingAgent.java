package adapter.agent;

import java.io.IOException;
import adapter.translator.Translator;

public class StartingAgent extends Agent {

    public StartingAgent(Translator translator) throws IOException {
        super(translator);
    }
        
    @Override
    public void run() throws IOException {
        doTurn(AgentParams.getFirstTurnPower());
        handleHGomokuTurn();
        while (true) {
            doTurn();
            handleHGomokuTurn();
        }
    }   
}
