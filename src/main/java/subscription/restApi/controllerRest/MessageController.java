package subscription.restApi.controllerRest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import subscription.restApi.customException.MissingDataException;
import subscription.restApi.entityDTO.MessageDTO;
import subscription.restApi.service.MessageService;
import subscription.restApi.service.MessageService;

@RestController
@RequestMapping("/api")
public class MessageController {
	
	@Autowired
	MessageService messageService;
	
	public static final Logger logger = (Logger) LoggerFactory.getLogger(MessageController.class);

	
	@RequestMapping(value = "/messages/", method = RequestMethod.GET)
	public ResponseEntity<List<MessageDTO>> listAllsubscriptions() {
		List<MessageDTO>  subscriptions = messageService.findAll();
		if (subscriptions.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<MessageDTO>>(subscriptions, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/messages/", method = RequestMethod.POST)
	public ResponseEntity<MessageDTO> createMessage(@RequestBody MessageDTO messageDTO,UriComponentsBuilder ucBuilder) throws MissingDataException {
		
		messageDTO= messageService.create(messageDTO);		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/messages/{id}").buildAndExpand(messageDTO.getId()).toUri());
		return new ResponseEntity<MessageDTO>(headers,HttpStatus.OK);
	}
	
	
}
