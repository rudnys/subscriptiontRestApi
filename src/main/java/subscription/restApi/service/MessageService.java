package subscription.restApi.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import subscription.restApi.customException.MissingDataException;
import subscription.restApi.entity.Message;
import subscription.restApi.entityDTO.MessageDTO;
import subscription.restApi.jpaRepository.MessageRepository;
import subscription.restApi.jpaRepository.MessageTypeRepository;
@Service
public class MessageService{
	public static final Logger logger = (Logger) LoggerFactory.getLogger(MessageService.class);

	@Autowired
	MessageRepository messageRepository;
	@Autowired
	MessageTypeRepository messageTypeRepository;
	ModelMapper modelMapper = new ModelMapper();
	
	public List<MessageDTO> findAll(){
		List<Message> currentsubscriptionList=messageRepository.findAll();		
		return 	modelMapper.map(currentsubscriptionList, new TypeToken<List<MessageDTO>>() {}.getType());

	}
	public  MessageDTO create(MessageDTO messageDTO) throws MissingDataException{
		messageDTO.setMessage(HtmlUtils.htmlEscape(messageDTO.getMessage()));
		if(messageDTO.getMessage()==null ||messageDTO.getMessage().isEmpty()){
			logger.error("Unable to create message missing body mesage.");
			throw new MissingDataException("Unable to create message missing body mesage.");
		}
		Message newMessage=modelMapper.map(messageDTO, Message.class);
		newMessage.setMessageType(messageTypeRepository.findById(Long.valueOf(messageDTO.getMessageTypeId())).get());
		messageRepository.saveAndFlush(newMessage);		
		return 	modelMapper.map(newMessage, MessageDTO.class);

	}
}
