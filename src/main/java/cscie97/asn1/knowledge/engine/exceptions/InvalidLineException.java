package cscie97.asn1.knowledge.engine.exceptions;

/**
 * An Exception to be thrown when a line is invalid (for both import and query)
 * Note due to the generic parsing for both cases I didn't have the need to distinguish between the two cases, i.e no
 * need to have separate ImportException & QueryException
 *
 * @author khaled
 */
public class InvalidLineException extends RuntimeException {

    public InvalidLineException(String message) {
        super(message);
    }
}
