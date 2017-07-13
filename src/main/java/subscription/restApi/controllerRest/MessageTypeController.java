package subscription.restApi.controllerRest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import subscription.restApi.entityDTO.MessageTypeDTO;
import subscription.restApi.service.MessageTypeService;
import subscription.restApi.service.MessageTypeService;

@RestController
@RequestMapping("/api")
public class MessageTypeController {
	
	@Autowired
	MessageTypeService messageTypeService;
	
	public static final Logger logger = (Logger) LoggerFactory.getLogger(MessageTypeController.class);

	
	@RequestMapping(value = "/messageTypes/", method = RequestMethod.GET)
	public ResponseEntity<List<MessageTypeDTO>> listAllsubscriptions() {
		List<MessageTypeDTO>  subscriptions = messageTypeService.findAll();
		if (subscriptions.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<MessageTypeDTO>>(subscriptions, HttpStatus.OK);
	}
}
