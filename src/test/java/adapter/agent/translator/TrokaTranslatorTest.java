package adapter.agent.translator;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class TrokaTranslatorTest {

    private TrokaTranslator trokaTranslator;
    
    @Before
    public void setUp() {
        trokaTranslator = new TrokaTranslator(true);
    }
    
    @Test
    @Parameters({
            "a1, 1, 15", "b2, 2, 14", "c3, 3, 13",
            "a10, 1, 6", "d5, 4, 11", "f12, 6, 4",
            "j1, 10, 15", "o4, 15, 12", "l13, 12, 3"
    })
    public void shouldTranslateFromHGomokuToTroka(String hGomokuMove, String[] trokaMove) {
        assertEquals(getTrokaMoveCommand(trokaMove), trokaTranslator.translateMoveFromHGomoku(hGomokuMove));
    }
    
    @Test
    @Parameters({
            "a1, 1, 15", "b2, 2, 14", "c3, 3, 13",
            "a10, 1, 6", "d5, 4, 11", "f12, 6, 4",
            "j1, 10, 15", "o4, 15, 12", "l13, 12, 3"
    })
    public void shouldTranslateFromTrokaToHGomoku(String hGomokuMove, String[] trokaMove) {
        assertEquals(hGomokuMove, trokaTranslator.translateMoveToHGomoku(getTrokaAgentResponse(trokaMove)));
    }

    private String getTrokaMoveCommand(String[] move) {
        return String.format("makemove %s %s\n", move[0], move[1]);
    }

    private String getTrokaAgentResponse(String[] move) {
        return String.format("move black %s %s", move[0], move[1]);
    }
}
