package adapter.agent.kind;

import adapter.Agent;
import adapter.agent.AgentParams;
import adapter.agent.Protocol;

public class StartingAgent extends Agent {

    public StartingAgent(Protocol protocol) {
        super(protocol);
    }
        
    @Override
    public void run() throws Exception {
        doTurn(AgentParams.getFirstTurnPower());
        handleHGomokuTurn(AgentParams.getFirstTurnPower());
        while (true) {
            doTurn(AgentParams.getTurnPower());
            handleHGomokuTurn(AgentParams.getTurnPower());
        }
    }   
}
