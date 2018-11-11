package adapter.agent.translator;

import adapter.agent.Translator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZhixiangliTranslator implements Translator {

    private static final Pattern MOVE_PATTERN = Pattern.compile("\\{\"rowIndex\":(\\d+),\"columnIndex\":(\\d+)\\}");

    private final String side;
    private final String sideShortcut;
    private final int boardWidth;
    private final int boardHeight;
    private final StringBuffer movesBuffer = new StringBuffer();

    public ZhixiangliTranslator(boolean starting, int boardWidth, int boardHeight) {
        side = starting ? "NEXT_BLACK" : "NEXT_WHITE";
        sideShortcut = starting ? "B" : "W";
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }

    @Override
    public String startGame() {
        if (side.equals("NEXT_BLACK")) {
            return String.format(
                    "{\"command\":\"NEXT_BLACK\",\"rows\":%d,\"columns\":%d,\"chessboard\":\"\"}" + EOL,
                    boardWidth, boardHeight
            );
        }
        return "";
    }

    @Override
    public String endGame() {
        return "";
    }

    @Override
    public String translateMoveFromHGomoku(String lastMove) {
        int column = (lastMove.substring(0, 1)).codePointAt(0) - CODE_MIN_LETTER_ASCII;
        int row = boardHeight - Integer.parseInt(lastMove.substring(1));

        addMoveToBuffer(row, column, getOppositeSideShortcut());

        return String.format(
                "{\"command\":\"%s\",\"rows\":%d,\"columns\":%d,\"chessboard\":\"%s\"}" + EOL,
                side, boardWidth, boardHeight, movesBuffer.toString()
        );
    }

    @Override
    public String translateMoveToHGomoku(String lastMove) {
        Matcher matcher = MOVE_PATTERN.matcher(lastMove);
        matcher.find();

        int row = Integer.parseInt(matcher.group(1));
        int column = Integer.parseInt(matcher.group(2));

        addMoveToBuffer(row, column, sideShortcut);

        String x = Character.toString((char)('a' + column));
        String y = Integer.toString(boardHeight - row);

        return x + y;
    }

    @Override
    public boolean isMoveProvided(String line) {
        return line.contains("rowIndex") && line.contains("columnIndex");
    }

    @Override
    public String getCommunicationHandler() {
        return "adapter.agent.communication.Process";
    }

    private void addMoveToBuffer(int row, int col, String sideShortcut) {
        String rowHex = Integer.toHexString(row);
        String colHex = Integer.toHexString(col);

        movesBuffer.append(
                String.format("%s[%s%s];", sideShortcut, rowHex, colHex)
        );
    }

    private String getOppositeSideShortcut() {
        return sideShortcut.equals("B") ? "W" : "B";
    }
}
