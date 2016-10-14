/**
 * Created by nwpwr on 12/10/2016.
 */
public class NotValidException extends Exception {

    /** For limitation exception & if limitation cases not valid this exception will throw **/
    public NotValidException(String message) {
        super(message);
    }

    public NotValidException(Throwable cause) {
        super(cause);
    }
}
