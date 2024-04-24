package pres.xgo.fim.error;

/**
 * 业务异常
 */
public class FimException extends RuntimeException{

    public FimException() {
    }

    public FimException(String message) {
        super(message);
    }
}
