package adapter.agent;

import java.io.IOException;
import adapter.translator.Translator;

public class FollowingAgent extends Agent {

    public FollowingAgent(Translator translator) throws IOException {
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
