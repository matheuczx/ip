package HarryBotter;

/**
 * A custom exception type used within the HarryBotter application.
 * This exception can be thrown to indicate application-specific errors
 * or unexpected conditions that occur during execution. It extends
 */

public class HarryBotterException extends Exception{

    public HarryBotterException(String message){
        super(message);
    }
}
