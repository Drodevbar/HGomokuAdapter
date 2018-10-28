package adapter.agent;

import java.util.Arrays;

public class AgentParams {

    private static String translator;
    private static int boardWidth;
    private static int boardHeight;
    private static int kToWin;
    private static int turnPower;
    private static int firstTurnPower;
    private static boolean starting;
    private static String command;
    
    public static void load(String[] args) {
        validateArgsLength(args.length);
        translator = args[0];
        boardWidth = Integer.parseInt(args[1]);
        boardHeight = Integer.parseInt(args[2]);
        kToWin = Integer.parseInt(args[3]);
        turnPower = Integer.parseInt(args[4]);
        firstTurnPower = Integer.parseInt(args[5]);
        starting = convertToBoolean(args[6]);
        command = buildCommand(Arrays.copyOfRange(args, 7, args.length));
    }
    
    public static String getTranslator() {
        return translator;
    }
    
    public static int getBoardWidth() {
        return boardWidth;
    }
    
    public static int getBoardHeight() {
        return boardHeight;
    }
    
    public static int getKToWin() {
        return kToWin;
    }
    
    public static int getTurnPower() {
        return turnPower;
    }
    
    public static int getFirstTurnPower() {
        return firstTurnPower;
    }
    
    public static boolean isStarting() {
        return starting;
    }
    
    public static String getCommand() {
        return command;
    }
    
    private static String buildCommand(String[] args) {
        if (args.length == 0) {
            return "";
        }

        StringBuilder commandBuilder = new StringBuilder();
        for (String arg : args) {
            commandBuilder.append(arg).append(" ");
        }
        return commandBuilder.toString();
    }
    
    private static boolean convertToBoolean(String value) {
        return "1".equalsIgnoreCase(value)
                || "yes".equalsIgnoreCase(value)
                || "true".equalsIgnoreCase(value)
                || "first".equalsIgnoreCase(value);
    } 
    
    private static void validateArgsLength(int length) {
        if (length < 7) {
            throw new RuntimeException("Please provide required parameters.");
        }
    }
}
