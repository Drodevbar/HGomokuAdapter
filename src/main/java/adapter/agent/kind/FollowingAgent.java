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
        handleHGomokuTurn(AgentParams.getFirstTurnPower());
        doTurn(AgentParams.getTurnPower());
        while (true) {
            handleHGomokuTurn(AgentParams.getTurnPower());
            doTurn(AgentParams.getTurnPower());
        }
    }
}
