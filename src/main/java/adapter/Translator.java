package adapter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Translator {
   
    private static final Pattern TROKA_REGEX_PATTERN = Pattern.compile("move (?:black|white) (\\d+) (\\d+)");
    private static final String EOL = "\n";
    private static final int CODE_MIN_LETTER_ASCII = 97;
    private static String side;

    public static void initialize(boolean starting) {
        side = starting ? "black" : "white";
    }

    public static String startGame() {
        if (side.equals("black")) {
            return "newgame black ai white human" + EOL;
        } else {
            return "newgame black human white ai" + EOL;
        }
    }
    
    public static String endGame() {
        return "exit" + EOL;
    }

    public static boolean isMoveProvided(String line) {
        return line.contains("move " + side);
    }
    
    public static String translateMoveToTroka(String lastMove) {
        int x = (lastMove.substring(0, 1)).codePointAt(0) - CODE_MIN_LETTER_ASCII + 1;
        int y = Integer.parseInt(lastMove.substring(1));
        
        return String.format("makemove %d %d" + EOL, x, y);
    }
    
    public static String translateMoveToHGomoku(String lastMove) {
        Matcher matcher = TROKA_REGEX_PATTERN.matcher(lastMove);
        matcher.find();

        String x = Character.toString(
               (char)('a' + Integer.parseInt(matcher.group(1)) - 1)
        );
        String y = Integer.toString(
                Integer.parseInt(matcher.group(2))
        );
        
        return x + y;
    }
}
