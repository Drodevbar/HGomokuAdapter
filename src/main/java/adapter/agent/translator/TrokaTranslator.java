package adapter.agent.translator;

import adapter.agent.Translator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrokaTranslator implements Translator {

    private static final int BOARD_HEIGHT = 15;
    private static final Pattern MOVE_PATTERN = Pattern.compile("move (?:black|white) (\\d+) (\\d+)");

    private final String side;

    public TrokaTranslator(boolean starting) {
        this.side = starting ? "black" : "white";
    }

    @Override
    public String startGame() {
        String command = side.equals("black")
                ? "newgame black ai white human"
                : "newgame black human white ai";
        
        return command + EOL;
    }

    @Override
    public String endGame() {
        return "exit" + EOL;
    }

    @Override
    public String translateMoveFromHGomoku(String lastMove) {
        int x = (lastMove.substring(0, 1)).codePointAt(0) - CODE_MIN_LETTER_ASCII + 1;
        int y = BOARD_HEIGHT - Integer.parseInt(lastMove.substring(1)) + 1;
        
        return String.format("makemove %d %d" + EOL, x, y);
    }

    @Override
    public String translateMoveToHGomoku(String lastMove) {
        Matcher matcher = MOVE_PATTERN.matcher(lastMove);
        matcher.find();

        String x = Character.toString(
               (char)('a' + Integer.parseInt(matcher.group(1)) - 1)
        );
        String y = Integer.toString(
                BOARD_HEIGHT - Integer.parseInt(matcher.group(2)) + 1
        );
        
        return x + y;
    }

    @Override
    public boolean isMoveProvided(String line) {
        return line.contains("move " + side);
    }

    @Override
    public String getCommunicationHandler() {
        return "adapter.agent.communication.Process";
    }

    @Override
    public int getTurnPower() {
        return 1;
    }

    @Override
    public int getFirstTurnPower() {
        return 1;
    }
}
