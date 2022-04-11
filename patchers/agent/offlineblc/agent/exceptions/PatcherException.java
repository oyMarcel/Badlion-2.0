package offlineblc.agent.exceptions;

public class PatcherException extends RuntimeException {
    public PatcherException(final String cause) {
        super(cause);
    }
    
    public PatcherException(final Throwable cause) {
        super(cause);
    }
}
