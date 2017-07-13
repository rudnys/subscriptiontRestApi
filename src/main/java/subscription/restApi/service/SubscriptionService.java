package subscription.restApi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import subscription.restApi.customException.ConflictPersistentOpException;
import subscription.restApi.customException.MissingDataException;
import subscription.restApi.customException.ObjectNotFoundException;
import subscription.restApi.entity.MessageTypeInSubscription;
import subscription.restApi.entity.Subscription;
import subscription.restApi.entityDTO.SubscriptionDTO;
import subscription.restApi.jpaRepository.MessageTypeRepository;
import subscription.restApi.jpaRepository.SubscriptionRepository;
@Service
public class SubscriptionService{
	public static final Logger logger = (Logger) LoggerFactory.getLogger(SubscriptionService.class);

	@Autowired
	SubscriptionRepository subscriptionRepository;
	
	@Autowired
	MessageTypeRepository messageTypeRepository;
	@Autowired
	MessageTypeInSubscriptionService messageTypeInSubscriptionService;
	
	ModelMapper modelMapper = new ModelMapper();
	
	public List<SubscriptionDTO> findAll(){
		List<Subscription> currentsubscriptionList=subscriptionRepository.findAll();		
		List<SubscriptionDTO> subscriptionDTOList=currentsubscriptionList.stream().map(obj->convertToDTO(obj)).collect(Collectors.toList());
		return subscriptionDTOList;
	}
	
	public SubscriptionDTO findById(Long id)throws ObjectNotFoundException{
		
		
		Optional<Subscription> currentsubscription=subscriptionRepository.findById(id);
		SubscriptionDTO subscriptionDTO=new SubscriptionDTO();
		if(!currentsubscription.isPresent()){
			logger.error("subscription with id {} not found.", id);
			throw new ObjectNotFoundException("subscription with id " + id + " not found");	
		}
		modelMapper.map(currentsubscription.get(),subscriptionDTO);
		return subscriptionDTO;	
		
	}
	
	public boolean issubscriptionExist(String email){
		Subscription a=subscriptionRepository.findByEmail(email);		
		return a!=null;
	}
	
	@Transactional
	public SubscriptionDTO create(SubscriptionDTO subscriptionDTO,BindingResult bindingResult) throws ConflictPersistentOpException, MissingDataException{
		
		if(issubscriptionExist(subscriptionDTO.getEmail())){
			logger.error("Unable to create. subscription with email {} already exists.", subscriptionDTO.getEmail());
			throw new ConflictPersistentOpException("Unable to create subscription with email. subscription with email " + subscriptionDTO.getEmail() + " already exists");
				
		}	
		if(subscriptionDTO.getMessageTypeList().isEmpty()){
			logger.error("Unable to create. subscription with email {} missing message types.");
			throw new MissingDataException("Unable to create. subscription missing message types.");
		}
		if(bindingResult.hasErrors()){
			logger.error("Unable to create. subscription missing email with problems.");
			throw new MissingDataException("Unable to create subscription  email with problems");
		}
		Subscription newSub=modelMapper.map(subscriptionDTO, Subscription.class);		
		subscriptionRepository.saveAndFlush(newSub);		
		
		//createdMessageType
		List<MessageTypeInSubscription> created=messageTypeInSubscriptionService.saveAllByListId(newSub,subscriptionDTO.getMessageTypeList());
		newSub.setMessageTypeInSubscription(created);
		
		return  convertToDTO(subscriptionRepository.findById(newSub.getId()).get());
	}
	@Transactional
	public SubscriptionDTO updateSubscription(SubscriptionDTO subscriptionDTO,BindingResult bindingResult)throws ObjectNotFoundException, ConflictPersistentOpException, MissingDataException{
		
		Optional<Subscription> currentSubscription = subscriptionRepository.findById(subscriptionDTO.getId());
		if(!currentSubscription.isPresent()){
			logger.error("Unable to update. subscription with id {} not found.", subscriptionDTO.getId());
			throw new ObjectNotFoundException("Unable to upate. subscription with id " + subscriptionDTO.getId() + " not found.");
		}
		
		if(subscriptionDTO.getMessageTypeList().isEmpty()){
			logger.error("Unable to update subscription missing message types.");
			throw new MissingDataException("Unable to update. subscription  missing message types.");
		}
		if(bindingResult.hasErrors()){
			logger.error("Unable to create. subscription missing email with problems.");
			throw new MissingDataException("Unable to create subscription  email with problems");
		}
		if(issubscriptionExist(subscriptionDTO.getEmail())){
			logger.error("Unable to update. subscription with email {} already exists.", subscriptionDTO.getEmail());
			throw new ConflictPersistentOpException("Unable to update subscription with email. subscription with email " + subscriptionDTO.getEmail() + " already exists");
				
		}	
		currentSubscription.get().setEmail(subscriptionDTO.getEmail());
		subscriptionRepository.saveAndFlush(currentSubscription.get());
		
		//delete&createdMessageType
		messageTypeInSubscriptionService.deleteBySubscriptionId(currentSubscription.get().getId());		
		List<MessageTypeInSubscription> created=messageTypeInSubscriptionService.saveAllByListId(currentSubscription.get(),subscriptionDTO.getMessageTypeList());
		
		return convertToDTO(subscriptionRepository.findById(currentSubscription.get().getId()).get());
		
	}
	
	
	private SubscriptionDTO convertToDTO(Subscription sub){
		SubscriptionDTO dto=new SubscriptionDTO();
		
		dto.setEmail(sub.getEmail());
		dto.setId(sub.getId());
		
		List<String> list=sub.getMessageTypeInSubscription().stream().map(obj->obj.getMessageType().getType()).collect(Collectors.toList());
		
		List<Object[]> countMessagetypeBySubscription=messageTypeRepository.countBySubscriptionRelated(list);
		list=countMessagetypeBySubscription.stream().map(x->x[0]+"-"+x[1]).collect(Collectors.toList());
		dto.setMessageTypeList(list);
		
		return dto;
	}
	
	
	
}
