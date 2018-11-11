package adapter.agent;

import java.util.Arrays;

public class AgentParams {

    private static String translator;
    private static boolean starting;
    private static String command;
    
    public static void load(String[] args) {
        validateArgsLength(args.length);
        translator = args[0];
        starting = convertToBoolean(args[1]);
        command = buildCommand(Arrays.copyOfRange(args, 2, args.length));
    }
    
    public static String getTranslator() {
        return translator;
    }
    
    public static boolean isStarting() {
        return starting;
    }
    
    public static String getCommand() {
        return command;
    }
    
    private static String buildCommand(String[] args) {
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
        if (length < 3) {
            throw new RuntimeException("Please provide required parameters.");
        }
    }
}
