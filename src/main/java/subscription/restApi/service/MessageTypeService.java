package subscription.restApi.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import subscription.restApi.entity.MessageType;
import subscription.restApi.entityDTO.MessageTypeDTO;
import subscription.restApi.jpaRepository.MessageTypeRepository;
@Service
public class MessageTypeService{
	public static final Logger logger = (Logger) LoggerFactory.getLogger(MessageTypeService.class);

	@Autowired
	MessageTypeRepository messageTypeRepository;
	ModelMapper modelMapper = new ModelMapper();
	
	public List<MessageTypeDTO> findAll(){
		List<MessageType> currentsubscriptionList=messageTypeRepository.findAll();
		
		return 	modelMapper.map(currentsubscriptionList, new TypeToken<List<MessageTypeDTO>>() {}.getType());

	}
	
}
