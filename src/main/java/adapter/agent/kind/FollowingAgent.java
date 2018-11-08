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
        while (true) {
            handleHGomokuTurn(AgentParams.getTurnPower());
            doTurn(AgentParams.getTurnPower());
        }
    }
}
