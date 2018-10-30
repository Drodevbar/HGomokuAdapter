package adapter.agent.translator;

import adapter.agent.Translator;
import adapter.util.Converter;

import org.json.JSONObject;

public class BlupigTranslator implements Translator {
    
    private static final int PLAYER_ONE = 1;
    private static final int PLAYER_TWO = 2;
    private static final int FIRST_MOVE_INDEX = 180;
    private static final int CODE_MIN_LETTER_ASCII = 97;
    
    private final int playerNumber;
    private final int opponentNumber;
    private final int boardHeight;
    private final int[] board;

    BlupigTranslator(boolean starting, int boardWidth, int boardHeight) {
        playerNumber = starting ? PLAYER_ONE : PLAYER_TWO;
        opponentNumber = starting ? PLAYER_TWO : PLAYER_ONE;
        board = new int[boardWidth * boardHeight];
        this.boardHeight = boardHeight;
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
        int y = boardHeight - Integer.parseInt(lastMove.substring(1));

        board[boardHeight * y + x] = opponentNumber;

        return " -s " + getBoardAsString() + " -p " + playerNumber;
    }

    @Override
    public String translateMoveToHGomoku(String lastMove) {
        JSONObject response = new JSONObject(lastMove);

        int moveC = Integer.parseInt(response.getJSONObject("result").getString("move_c"));
        int moveR = Integer.parseInt(response.getJSONObject("result").getString("move_r"));

        board[boardHeight * moveR + moveC] = playerNumber;

        String x = Character.toString((char)('a' + moveC));
        String y = Integer.toString(boardHeight - moveR);

        return x + y;
    }

    @Override
    public boolean isMoveProvided(String line) {
        return true;
    }

    @Override
    public String protocolType() {
        return "adapter.agent.protocol.BlupigCommand";
    }

    private String getBoardAsString() {
        return Converter.toStringfromIntArray(board);
    }
}
