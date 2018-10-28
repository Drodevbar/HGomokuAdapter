package adapter.agent.kind;

import adapter.Agent;
import adapter.agent.AgentParams;
import adapter.agent.Protocol;

public class FollowingAgent extends Agent {

    public FollowingAgent(Protocol protocol) {
        super(protocol);
    }
    
    @Override
    public void run() throws Exception {
        while (true) {
            handleHGomokuTurn(AgentParams.getTurnPower());
            doTurn(AgentParams.getTurnPower());
        }
    }
}
