package adapter.agent;

import java.io.IOException;

public class StartingAgent extends Agent {

    public StartingAgent() throws IOException {
        super();
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
