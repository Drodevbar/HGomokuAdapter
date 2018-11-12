package adapter.agent.translator;

import adapter.agent.Translator;
import adapter.util.Converter;

import org.json.JSONObject;

public class BlupigTranslator implements Translator {
    
    private static final int PLAYER_ONE = 1;
    private static final int PLAYER_TWO = 2;
    private static final int BOARD_WIDTH = 19;
    private static final int BOARD_HEIGHT = 19;
    private static final int ALPHA_BETA_DEPTH = 6;
    private static final int FIRST_MOVE_INDEX = 180;
    
    private final int playerNumber;
    private final int opponentNumber;
    private final int[] board;

    public BlupigTranslator(boolean starting) {
        playerNumber = starting ? PLAYER_ONE : PLAYER_TWO;
        opponentNumber = starting ? PLAYER_TWO : PLAYER_ONE;
        board = new int[BOARD_WIDTH * BOARD_HEIGHT];
    }

    @Override
    public String startGame() {
        if (playerNumber == PLAYER_ONE) {
            board[FIRST_MOVE_INDEX] = PLAYER_ONE;
        }
        return "";
    }

    @Override
    public String endGame() {
        return "";
    }

    @Override
    public String translateMoveFromHGomoku(String lastMove) {
        int x = (lastMove.substring(0, 1)).codePointAt(0) - CODE_MIN_LETTER_ASCII;
        int y = BOARD_HEIGHT - Integer.parseInt(lastMove.substring(1));

        board[BOARD_HEIGHT * y + x] = opponentNumber;

        return " -s " + getBoardAsString() + " -p " + playerNumber + " -d " + ALPHA_BETA_DEPTH;
    }

    @Override
    public String translateMoveToHGomoku(String lastMove) {
        JSONObject response = new JSONObject(lastMove);

        int moveC = Integer.parseInt(response.getJSONObject("result").getString("move_c"));
        int moveR = Integer.parseInt(response.getJSONObject("result").getString("move_r"));

        board[BOARD_HEIGHT * moveR + moveC] = playerNumber;

        String x = Character.toString((char)('a' + moveC));
        String y = Integer.toString(BOARD_HEIGHT - moveR);

        return x + y;
    }

    @Override
    public boolean isMoveProvided(String line) {
        return true;
    }

    @Override
    public String getCommunicationHandler() {
        return "adapter.agent.communication.BlupigCommand";
    }

    @Override
    public int getTurnPower() {
        return 1;
    }

    @Override
    public int getFirstTurnPower() {
        return 1;
    }

    private String getBoardAsString() {
        return Converter.toStringfromIntArray(board);
    }
}
