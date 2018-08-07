package adapter;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class TranslatorTest {

    @Test
    @Parameters({
            "a1, 1, 1", "b2, 2, 2", "c3, 3, 3",
            "a10, 1, 10", "d5, 4, 5", "f12, 6, 12",
            "j1, 10, 1", "o21, 15, 21", "l13, 12, 13"
    })
    public void shouldTranslateFromHGomokuToTroka(String hGomokuMove, String[] trokaMove) {
        assertEquals(getTrokaMoveCommand(trokaMove), Translator.translateMoveToTroka(hGomokuMove));
    }

    @Test
    @Parameters({
            "a1, 1, 1", "b2, 2, 2", "c3, 3, 3",
            "a10, 1, 10", "d5, 4, 5", "f12, 6, 12",
            "j1, 10, 1", "o21, 15, 21", "l13, 12, 13"
    })
    public void shouldTranslateFromTrokaToHGomoku(String hGomokuMove, String[] trokaMove) {
        assertEquals(hGomokuMove, Translator.translateMoveToHGomoku(getTrokaAgentResponse(trokaMove)));
    }

    private String getTrokaMoveCommand(String[] move) {
        return String.format("makemove %s %s\n", move[0], move[1]);
    }

    private String getTrokaAgentResponse(String[] move) {
        return String.format("move black %s %s", move[0], move[1]);
    }
}
