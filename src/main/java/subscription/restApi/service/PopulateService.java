/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subscription.restApi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import subscription.restApi.entity.MessageType;
import subscription.restApi.entity.Subscription;
import subscription.restApi.jpaRepository.MessageRepository;
import subscription.restApi.jpaRepository.MessageTypeInSubscriptionRepository;
import subscription.restApi.jpaRepository.MessageTypeRepository;
import subscription.restApi.jpaRepository.SubscriptionRepository;
import subscription.restApi.util.BuilderHelp;

/**
 *
 * @author rudny
 */
@Service
public class PopulateService {
   
	@Autowired
	SubscriptionRepository subscriptionRepository;
	@Autowired
	MessageTypeRepository messageTypeRepository;
	
	@Autowired
	MessageTypeInSubscriptionRepository messageTypeInSubscriptionRepository;
	@Autowired
	MessageRepository messageRepository;
	
	@PostConstruct
	@Transactional
	public void init(){
		messageRepository.deleteAll();
		messageTypeInSubscriptionRepository.deleteAll();
		subscriptionRepository.deleteAll();
		messageTypeRepository.deleteAll();
		
		MessageType type1=BuilderHelp.newMessageType("Sport");
		MessageType type2=BuilderHelp.newMessageType("Fashion");
		MessageType type3=BuilderHelp.newMessageType("Famous People");
		MessageType type4=BuilderHelp.newMessageType("Movies");
		MessageType type5=BuilderHelp.newMessageType("Trump");
		MessageType type6=BuilderHelp.newMessageType("Anime");
		MessageType type7=BuilderHelp.newMessageType("Vacations");
		MessageType type8=BuilderHelp.newMessageType("Cruiser Offers");		
		
		messageTypeRepository.save(type1);
		messageTypeRepository.save(type2);
		messageTypeRepository.save(type3);
		messageTypeRepository.save(type4);
		messageTypeRepository.save(type5);
		messageTypeRepository.save(type6);
		messageTypeRepository.save(type7);
		messageTypeRepository.save(type8);
		messageTypeRepository.flush();
		
		
		Subscription s1=  BuilderHelp.newsubscription("rudny@gmail.com");
		subscriptionRepository.save(s1);
		subscriptionRepository.flush();
		messageTypeInSubscriptionRepository.save(BuilderHelp.newMessageTypeInSubscription(s1, type1));
		messageTypeInSubscriptionRepository.save(BuilderHelp.newMessageTypeInSubscription(s1, type2));
		messageTypeInSubscriptionRepository.save(BuilderHelp.newMessageTypeInSubscription(s1, type3));
		messageTypeInSubscriptionRepository.save(BuilderHelp.newMessageTypeInSubscription(s1, type4));
		messageTypeInSubscriptionRepository.save(BuilderHelp.newMessageTypeInSubscription(s1, type5));
		messageTypeInSubscriptionRepository.flush();
		//s1.setMessageTypeList(new HashSet<MessageType>(Arrays.asList(type2, type3)));
//		s1.getMessageTypeList().add(type2);
//		
//		
		subscriptionRepository.saveAndFlush(s1);
//		
//		Arrays.asList(type1, type4)
//		subscriptionRepository.save(BuilderHelp.newsubscription("killerbell@gmail.com",Arrays.asList(type2, type3)));
//		subscriptionRepository.save(BuilderHelp.newsubscription("craightlist@gmail.com",Arrays.asList( type8)));
//		
		
		subscriptionRepository.flush();
	}
    
}
