package subscription.restApi.customException;

public class ObjectNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5596413620865057200L;

	public ObjectNotFoundException(String message){
        super(message);    
	}
}
