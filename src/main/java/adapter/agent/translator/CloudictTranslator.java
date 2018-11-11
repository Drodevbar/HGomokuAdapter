package adapter.agent.translator;

import adapter.agent.Translator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CloudictTranslator implements Translator {

    private static final int ALPHA_BETA_DEPTH = 2;
    private static final Pattern MOVE_PATTERN = Pattern.compile("move ([A-S])([A-S])([A-S])?([A-S])?");

    private final String side;
    private boolean isFirstTurn = true;
    private String[] fromBuffer = new String[2];
    private String[] toBuffer = new String[2];

    public CloudictTranslator(boolean starting) {
        side = starting ? "black" : "white";
    }

    @Override
    public String startGame() {
        return "new " + side + EOL
                + "depth " + ALPHA_BETA_DEPTH + EOL;
    }

    @Override
    public String endGame() {
        return "exit" + EOL;
    }

    @Override
    public String translateMoveFromHGomoku(String lastMove) {
        String x = lastMove.substring(0, 1).toUpperCase();
        String y = Character.toString((char)('A' + Integer.parseInt(lastMove.substring(1)) - 1));

        String translatedMove;

        if (isFirstTurn) {
            isFirstTurn = false;
            translatedMove = String.format("move %s%s" + EOL, x, y);
        } else if (isBufferNotEmpty(fromBuffer)) {
            translatedMove = String.format("move %s%s%s%s" + EOL, fromBuffer[0], fromBuffer[1], x, y);
            resetBuffer(fromBuffer);
        } else {
            putMoveToBuffer(fromBuffer, x, y);
            translatedMove = "";
        }

        return translatedMove;
    }

    @Override
    public String translateMoveToHGomoku(String lastMove) {
        String translatedMove;

        if (isBufferNotEmpty(toBuffer)) {
            translatedMove = toBuffer[0] + toBuffer[1];
            resetBuffer(toBuffer);
        } else {
            Matcher matcher = MOVE_PATTERN.matcher(lastMove);
            matcher.find();

            if (isFirstTurn) {
                isFirstTurn = false;
                String[] move = getTranslatedMoveFromMatcher(matcher, 1, 2);
                translatedMove = move[0] + move[1];
            } else {
                String[] firstMove = getTranslatedMoveFromMatcher(matcher, 1, 2);
                String[] secondMove = getTranslatedMoveFromMatcher(matcher, 3, 4);

                translatedMove = firstMove[0] + firstMove[1];
                putMoveToBuffer(toBuffer, secondMove[0], secondMove[1]);
            }
        }

        return translatedMove;
    }

    @Override
    public boolean isMoveProvided(String line) {
        return MOVE_PATTERN.matcher(line).find() || isBufferNotEmpty(toBuffer);
    }

    @Override
    public String getCommunicationHandler() {
        return "adapter.agent.communication.Process";
    }

    private boolean isBufferNotEmpty(String[] buffer) {
        for (String el : buffer) {
            if (el != null) {
                return true;
            }
        }
        return false;
    }

    private void putMoveToBuffer(String[] buffer, String x, String y) {
        buffer[0] = x;
        buffer[1] = y;
    }

    private void resetBuffer(String[] buffer) {
        buffer[0] = null;
        buffer[1] = null;
    }

    private String[] getTranslatedMoveFromMatcher(Matcher matcher, int xIndex, int yIndex) {
        String x = matcher.group(xIndex).toLowerCase();
        String y = Integer.toString(
                matcher.group(yIndex).codePointAt(0) - CODE_MIN_UP_LETTER_ASCII + 1
        );

        return new String[] {x, y};
    }
}
