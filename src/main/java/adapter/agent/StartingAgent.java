package adapter.agent;

import adapter.translator.Translatable;
import java.io.IOException;

public class StartingAgent extends Agent {

    public StartingAgent(Translatable translator) throws IOException {
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
