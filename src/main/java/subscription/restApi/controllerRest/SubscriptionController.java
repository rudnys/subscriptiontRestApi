package subscription.restApi.controllerRest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import subscription.restApi.customException.ConflictPersistentOpException;
import subscription.restApi.customException.MissingDataException;
import subscription.restApi.customException.ObjectNotFoundException;
import subscription.restApi.entityDTO.SubscriptionDTO;
import subscription.restApi.service.SubscriptionService;

@RestController
@RequestMapping("/api")
public class SubscriptionController {
	
	@Autowired
	SubscriptionService subscriptionService;
	
	public static final Logger logger = (Logger) LoggerFactory.getLogger(SubscriptionController.class);

	
	@RequestMapping(value = "/subscriptions/", method = RequestMethod.GET)
	public ResponseEntity<List<SubscriptionDTO>> listAllsubscriptions() {
		List<SubscriptionDTO>  subscriptions = subscriptionService.findAll();
		if (subscriptions.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<SubscriptionDTO>>(subscriptions, HttpStatus.OK);
	}


		// -------------------Create a subscription-------------------------------------------

		@RequestMapping(value = "/subscriptions/", method = RequestMethod.POST)
		public ResponseEntity<?> createsubscription(@Valid @RequestBody SubscriptionDTO subscriptionDTO,BindingResult bindingResult, UriComponentsBuilder ucBuilder) throws ConflictPersistentOpException, MissingDataException {
			logger.info("Creating subscription with name : {}", subscriptionDTO.getEmail());
			SubscriptionDTO dto=subscriptionService.create(subscriptionDTO,bindingResult);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/api/subscriptions/{id}").buildAndExpand(dto.getId()).toUri());
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		}


//		// ------------------- Update a subscription ------------------------------------------------
//
		@RequestMapping(value = "/subscriptions/{id}", method = RequestMethod.PUT)		
		public ResponseEntity<?> updatesubscription(@PathVariable("id") long id, @Valid @RequestBody SubscriptionDTO subscriptionDTO,BindingResult bindingResult) throws ObjectNotFoundException, ConflictPersistentOpException, MissingDataException{
			logger.info("Updating subscription with id {}", id);
			//avoiding 
			subscriptionDTO.setId(id);
			subscriptionDTO=subscriptionService.updateSubscription(subscriptionDTO,bindingResult);			
			return new ResponseEntity<>(HttpStatus.OK);
		}
}
