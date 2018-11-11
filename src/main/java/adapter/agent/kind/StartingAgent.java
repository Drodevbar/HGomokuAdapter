package adapter.agent.kind;

import adapter.Agent;
import adapter.agent.AgentParams;
import adapter.agent.CommunicationHandler;

public class StartingAgent extends Agent {

    public StartingAgent(CommunicationHandler communicationHandler) {
        super(communicationHandler);
    }
        
    @Override
    public void run() throws Exception {
        doTurn(communicationHandler.getFirstTurnPower());
        handleHGomokuTurn(communicationHandler.getTurnPower());
        while (true) {
            doTurn(communicationHandler.getTurnPower());
            handleHGomokuTurn(communicationHandler.getTurnPower());
        }
    }   
}
