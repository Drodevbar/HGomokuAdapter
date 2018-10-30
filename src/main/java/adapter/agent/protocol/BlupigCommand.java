package adapter.agent.protocol;

import adapter.agent.AgentParams;
import adapter.agent.Protocol;
import adapter.agent.Translator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Process;

public class BlupigCommand extends Protocol {

    private final String command;
    private String lastCommandParams;

    public BlupigCommand(Translator translator) {
        super(translator);
        command = AgentParams.getCommand();
    }

    @Override
    public void startGame() {
        translator.startGame();
    }

    @Override
    public String doTurn() throws Exception {
        Process process = Runtime.getRuntime().exec(command + lastCommandParams);

        BufferedReader stdInput = new BufferedReader(
                new InputStreamReader(process.getInputStream())
        );

        StringBuilder response = new StringBuilder();
        String line = stdInput.readLine();
        while (line != null) {
            response.append(line);
            line = stdInput.readLine();
        }

         return translator.translateMoveToHGomoku(response.toString());
    }

    @Override
    public void handleHGomokuTurn(String move) {
        lastCommandParams = translator.translateMoveFromHGomoku(move);
    }

    @Override
    public void close() {
    }
}
