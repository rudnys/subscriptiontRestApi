package subscription.restApi.controllerRest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import subscription.restApi.customException.ConflictPersistentOpException;
import subscription.restApi.customException.MissingDataException;
import subscription.restApi.customException.ObjectNotFoundException;
import subscription.restApi.entityDTO.ErrorInfoDTO;

@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler{
	
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(Exception.class)
//	@ResponseBody ErrorInfoDTO
//	handleBadRequest(HttpServletRequest req, Exception ex) {
//	    return new ErrorInfoDTO("Url: "+req.getRequestURL().toString()+" reason: "+ex.getMessage());
//	} 
	
	@ExceptionHandler(ObjectNotFoundException.class)
	ResponseEntity<ErrorInfoDTO> handleProductNotFound(HttpServletRequest req, Exception ex){		
		return new ResponseEntity<ErrorInfoDTO>(new ErrorInfoDTO(ex.getMessage()),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ConflictPersistentOpException.class)
	ResponseEntity<ErrorInfoDTO> handleConflict(HttpServletRequest req, Exception ex){		
		return new ResponseEntity<ErrorInfoDTO>(new ErrorInfoDTO(ex.getMessage()),HttpStatus.CONFLICT);
	}
	@ExceptionHandler(MissingDataException.class)
	ResponseEntity<ErrorInfoDTO> handleDataMissing(HttpServletRequest req, Exception ex){		
		return new ResponseEntity<ErrorInfoDTO>(new ErrorInfoDTO(ex.getMessage()),HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(Exception.class)
	ResponseEntity<ErrorInfoDTO> handleGeneral(HttpServletRequest req, Exception ex){		
		return new ResponseEntity<ErrorInfoDTO>(new ErrorInfoDTO(ex.getMessage()),HttpStatus.CONFLICT);
	}
}
