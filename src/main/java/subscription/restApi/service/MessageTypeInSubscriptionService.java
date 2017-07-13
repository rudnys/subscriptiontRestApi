package subscription.restApi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import subscription.restApi.entity.MessageType;
import subscription.restApi.entity.MessageTypeInSubscription;
import subscription.restApi.entity.Subscription;
import subscription.restApi.entityDTO.MessageTypeDTO;
import subscription.restApi.jpaRepository.MessageTypeInSubscriptionRepository;
import subscription.restApi.jpaRepository.MessageTypeRepository;
import subscription.restApi.jpaRepository.MessageTypeRepository;
import subscription.restApi.util.BuilderHelp;
@Service
public class MessageTypeInSubscriptionService{
	public static final Logger logger = (Logger) LoggerFactory.getLogger(MessageTypeInSubscriptionService.class);

	@Autowired
	MessageTypeInSubscriptionRepository messageTypeInSubscriptionRepository;
	@Autowired
	MessageTypeRepository messageTypeRepository;

	public void deleteBySubscriptionId(Long subscriptionId){
		messageTypeInSubscriptionRepository.deleteBySubscriptionId(subscriptionId);
	}
	public List<MessageTypeInSubscription> saveAllByListId(Subscription newSubscription,List<String> messageTypeIdList){
		List<MessageType> currentsubscriptionList=messageTypeRepository.findAll();
		
		
		List<MessageType> messageTypeAllList= messageTypeRepository.findAll();
		
		List<MessageType> messageTypeList=messageTypeIdList.stream().map(obj->messageTypeAllList.stream().filter(x->x.getId().toString().equals(obj)).findFirst().get()).collect(Collectors.toList());
		
		List<MessageTypeInSubscription> toCreate=messageTypeList.stream().map(x->BuilderHelp.newMessageTypeInSubscription(newSubscription, x)).collect(Collectors.toList());
		messageTypeInSubscriptionRepository.saveAll(toCreate);
		messageTypeInSubscriptionRepository.flush();
		return 	toCreate;

	}
	
}
