/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subscription.restApi.util;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import subscription.restApi.entity.MessageType;
import subscription.restApi.entity.MessageTypeInSubscription;
import subscription.restApi.entity.Subscription;
import subscription.restApi.entityDTO.SubscriptionDTO;

/**
 *
 * @author rudny
 */

public class BuilderHelp {
   
	public static MessageType newMessageType(String name){
		MessageType d= new MessageType();
		d.setType(name);
		return d;
		
	}
	
	
	public static Subscription newsubscription(String email){
		Subscription d= new Subscription();
		d.setEmail(email);
		
		return d;
		
	}
	public static MessageTypeInSubscription newMessageTypeInSubscription(Subscription s,MessageType m){
		MessageTypeInSubscription d= new MessageTypeInSubscription();
		d.setMessageType(m);
		d.setSubscription(s);
		return d;
		
	}
	
}
