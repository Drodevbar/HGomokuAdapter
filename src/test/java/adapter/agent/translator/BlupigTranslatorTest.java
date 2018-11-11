package adapter.agent.translator;

import adapter.util.Converter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class BlupigTranslatorTest {

    private static final int BOARD_WIDTH = 19;
    private static final int BOARD_HEIGHT = 19;
    private static final int PLAYER_NUMBER = 1;
    private static final int OPPONENT_NUMBER = 2;
    
    private BlupigTranslator blupigTranslator;
    
    @Before
    public void setUp() {
        blupigTranslator = new BlupigTranslator(true);
    }
    
    @Test
    @Parameters({
            "a1, 342", "b2, 324", "c3, 306",
            "a10, 171", "d5, 269", "f12, 138",
            "j1, 351", "o4, 299", "l13, 125"
    })
    public void shouldTranslateFromHGomokuToBlupig(String hGomokuMove, int opponentLastMovePosition) {
        assertEquals(getBlupigResponseCommand(opponentLastMovePosition), blupigTranslator.translateMoveFromHGomoku(hGomokuMove));
    }
    
    @Test
    @Parameters({
            "a1, 0, 18", "b2, 1, 17", "c3, 2, 16",
            "a10, 0, 9", "d5, 3, 14", "f12, 5, 7",
            "j1, 9, 18", "o4, 14, 15", "l13, 11, 6"
    })
    public void shouldTranslateFromBlupigToHGomoku(String hGomokuMove, String[] blupigMove) {
        assertEquals(hGomokuMove, blupigTranslator.translateMoveToHGomoku(getBluepigAgentResponse(blupigMove)));
    }
    
    private String getBlupigResponseCommand(int opponentLastMovePosition) {
        int[] board = new int[BOARD_WIDTH * BOARD_HEIGHT];
        board[opponentLastMovePosition] = OPPONENT_NUMBER;
        
        return " -s " + Converter.toStringfromIntArray(board) + " -p " + PLAYER_NUMBER;
    }
    
    private String getBluepigAgentResponse(String[] move) {
       return String.format(
               "{\"result\":{\"move_c\":\"%s\",\"move_r\":\"%s\"}}",
               move[0], move[1]
       );
    }
}
