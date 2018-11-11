package adapter.agent.kind;

import adapter.Agent;
import adapter.agent.AgentParams;
import adapter.agent.CommunicationHandler;

public class FollowingAgent extends Agent {

    public FollowingAgent(CommunicationHandler communicationHandler) {
        super(communicationHandler);
    }
    
    @Override
    public void run() throws Exception {
        handleHGomokuTurn(communicationHandler.getFirstTurnPower());
        doTurn(communicationHandler.getTurnPower());
        while (true) {
            handleHGomokuTurn(communicationHandler.getTurnPower());
            doTurn(communicationHandler.getTurnPower());
        }
    }
}
