package subscription.restApi.customException;

public class ConflictPersistentOpException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5596999620865057200L;

	public ConflictPersistentOpException(String message){
        super(message);    
	}
}
