package subscription.restApi.customException;

public class MissingDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5596413620865057200L;

	public MissingDataException(String message){
        super(message);    
	}
}
