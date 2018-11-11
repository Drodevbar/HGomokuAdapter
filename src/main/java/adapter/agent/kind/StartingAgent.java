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
        doTurn(AgentParams.getFirstTurnPower());
        handleHGomokuTurn(AgentParams.getTurnPower());
        while (true) {
            doTurn(AgentParams.getTurnPower());
            handleHGomokuTurn(AgentParams.getTurnPower());
        }
    }   
}
