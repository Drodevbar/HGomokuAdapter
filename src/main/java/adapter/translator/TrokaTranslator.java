package adapter.translator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrokaTranslator implements Translatable {

    private static final String EOL = "\n";
    private static final int CODE_MIN_LETTER_ASCII = 97;
    private static final Pattern MOVE_PATTERN = Pattern.compile("move (?:black|white) (\\d+) (\\d+)");
            
    private final String side;
    private final int boardHeight;

    public TrokaTranslator(boolean starting, int boardHeight) {
        this.side = starting ? "black" : "white";
        this.boardHeight = boardHeight;
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
        int y = boardHeight - Integer.parseInt(lastMove.substring(1)) + 1;
        
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
                boardHeight - Integer.parseInt(matcher.group(2)) + 1
        );
        
        return x + y;
    }

    @Override
    public boolean isMoveProvided(String line) {
        return line.contains("move " + side);
    }
    
}
