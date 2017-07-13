package subscription.restApi.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.UniqueConstraint;


@Entity
public class MessageTypeInSubscription {
	
	@Id
    @GeneratedValue
	private Long id;
	
	
	@ManyToOne	    
	MessageType messageType;
	@ManyToOne    
	Subscription subscription;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Subscription getSubscription() {
		return subscription;
	}
	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}
	public MessageType getMessageType() {
		return messageType;
	}
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	
	
	
}
