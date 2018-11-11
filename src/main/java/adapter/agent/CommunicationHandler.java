package adapter.agent;

import java.io.Closeable;

public abstract class CommunicationHandler implements Closeable {

    protected final Translator translator;

    public CommunicationHandler(Translator translator) {
        this.translator = translator;
    }

    public int getTurnPower() {
        return translator.getTurnPower();
    }

    public int getFirstTurnPower() {
        return translator.getFirstTurnPower();
    }

    public abstract void startGame() throws Exception;

    public abstract String doTurn() throws Exception;

    public abstract void handleHGomokuTurn(String move) throws Exception;
}
