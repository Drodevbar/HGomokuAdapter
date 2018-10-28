package adapter.agent;

import java.io.Closeable;

public abstract class Protocol implements Closeable {

    protected final Translator translator;

    public Protocol(Translator translator) {
        this.translator = translator;
    }

    public abstract void startGame() throws Exception;

    public abstract String doTurn() throws Exception;

    public abstract void handleHGomokuTurn(String move) throws Exception;
}
