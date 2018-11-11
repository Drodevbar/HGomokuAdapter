package adapter.agent.translator;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class CloudictTranslatorTest {

    private CloudictTranslator cloudictTranslator;

    @Before
    public void setUp() {
        cloudictTranslator = new CloudictTranslator(true);
    }

    @Test
    @Parameters({
            "a1, A, A", "b2, B, B", "c3, C, C",
            "a10, A, J", "d5, D, E", "f12, F, L",
            "j1, J, A", "o4, O, D", "l13, L, M"
    })
    public void shouldTranslateFromHGomokuToCloudict(String hGomokuMove, String[] cloudictMove) {
        Assert.assertEquals(getCloudictMoveCommand(cloudictMove), cloudictTranslator.translateMoveFromHGomoku(hGomokuMove));
    }

    @Test
    @Parameters({
            "a1, A, A", "b2, B, B", "c3, C, C",
            "a10, A, J", "d5, D, E", "f12, F, L",
            "j1, J, A", "o4, O, D", "l13, L, M"
    })
    public void shouldTranslateFromCloudictToHGomoku(String hGomokuMove, String[] cloudictMove) {
        Assert.assertEquals(hGomokuMove, cloudictTranslator.translateMoveToHGomoku(getCloudictAgentResponse(cloudictMove)));
    }

    private String getCloudictMoveCommand(String[] move) {
        return String.format("move %s%s\n", move[0], move[1]);
    }

    private String getCloudictAgentResponse(String[] move) {
        return String.format("move %s%s", move[0], move[1]);
    }
}
