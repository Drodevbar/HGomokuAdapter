package adapter.agent.translator;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class ZhixiangliTranslatorTest {

    private static final int BOARD_WIDTH = 15;
    private static final int BOARD_HEIGHT = 15;

    private ZhixiangliTranslator zhixiangliTranslator;

    @Before
    public void setUp() {
        zhixiangliTranslator = new ZhixiangliTranslator(true, BOARD_WIDTH, BOARD_HEIGHT);
    }

    @Test
    @Parameters({
            "a1, e, 0", "b2, d, 1", "c3, c, 2",
            "a10, 5, 0", "d5, a, 3", "f12, 3, 5",
            "j1, e, 9", "o4, b, e", "l13, 2, b"
    })
    public void shouldTranslateFromHGomokuToZhixiangli(String hGomokuMove, String[] trokaMove) {
        assertEquals(getZhixiangliMoveCommand(trokaMove), zhixiangliTranslator.translateMoveFromHGomoku(hGomokuMove));
    }

    @Test
    @Parameters({
            "a1, 14, 0", "b2, 13, 1", "c3, 12, 2",
            "a10, 5, 0", "d5, 10, 3", "f12, 3, 5",
            "j1, 14, 9", "o4, 11, 14", "l13, 2, 11"
    })
    public void shouldTranslateFromZhixiangliToHGomoku(String hGomokuMove, String[] trokaMove) {
        assertEquals(hGomokuMove, zhixiangliTranslator.translateMoveToHGomoku(getZhiangiAgentResponse(trokaMove)));
    }

    private String getZhixiangliMoveCommand(String[] move) {
        return String.format(
                "{\"command\":\"NEXT_BLACK\",\"rows\":%d,\"columns\":%d,\"chessboard\":\"W[%s%s];\"}\n",
                BOARD_WIDTH, BOARD_HEIGHT, move[0], move[1]
        );
    }

    private String getZhiangiAgentResponse(String[] move) {
        return String.format("{\"rowIndex\":%s,\"columnIndex\":%s}", move[0], move[1]);
    }
}
