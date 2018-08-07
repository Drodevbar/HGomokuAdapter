package adapter.agent;

public class AgentParams {

    private static final String DEFAULT_COMMAND = "wine resources/engine.exe";

    private static int turnPower;
    private static int firstTurnPower;
    private static boolean starting;
    private static String command;
    
    public static void load(String[] args) {
        validateArgsLength(args.length);
        turnPower = Integer.parseInt(args[0]);
        firstTurnPower = Integer.parseInt(args[1]);
        starting = convertToBoolean(args[2]);
        command = (args.length > 3) ? args[3] : DEFAULT_COMMAND;
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
    
    private static boolean convertToBoolean(String value) {
        return "1".equalsIgnoreCase(value)
                || "yes".equalsIgnoreCase(value)
                || "true".equalsIgnoreCase(value)
                || "first".equalsIgnoreCase(value);
    }
    
    
    private static void validateArgsLength(int length) {
        if (length < 3) {
            throw new RuntimeException("Please provide required parameters.");
        }
    }
}
