package offlineblc.agent.util;

public class Logger {
    private static final String PREFIX = "[Offline BLC] ";
    
    public static void info(final Object message) {
        System.out.println(new StringBuilder().append("[Offline BLC] ").append(message).toString());
    }
    
    public static void warn(final Object message) {
        System.err.println(new StringBuilder().append("[Offline BLC] ").append(message).toString());
    }
    
    public static void error(final Object message) {
        System.err.println(new StringBuilder().append("[Offline BLC] ").append(message).toString());
    }
}
