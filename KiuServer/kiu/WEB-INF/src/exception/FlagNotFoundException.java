package exception;

/**
 * Created by spronghi on 15/09/16.
 */
public class FlagNotFoundException extends Throwable {
    public FlagNotFoundException(){
        super();
    }
    public FlagNotFoundException(String message){
        super(message);
    }
}
