package adapter.agent;

import java.io.IOException;

public class FollowingAgent extends Agent {

    public FollowingAgent() throws IOException {
        super();
    }
    
    @Override
    public void run() throws IOException {
        while (true) {
            handleHGomokuTurn();
            doTurn();
        }
    }
}
