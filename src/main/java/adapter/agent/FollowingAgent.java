package adapter.agent;

import adapter.translator.Translatable;
import java.io.IOException;

public class FollowingAgent extends Agent {

    public FollowingAgent(Translatable translator) throws IOException {
        super(translator);
    }
    
    @Override
    public void run() throws IOException {
        while (true) {
            handleHGomokuTurn();
            doTurn();
        }
    }
}
