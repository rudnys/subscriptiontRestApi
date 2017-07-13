package subscription.restApi.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;


@Entity
public class MessageType {
	
	@Id
    @GeneratedValue
	private Long id;
	private  String type;
	
	@OneToMany(mappedBy="messageType")
    private List<MessageTypeInSubscription> messageTypeInSubscription=new ArrayList<>();
	@OneToMany(mappedBy="messageType")
    private List<Message> messageList=new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<MessageTypeInSubscription> getMessageTypeInSubscription() {
		return messageTypeInSubscription;
	}
	public void setMessageTypeInSubscription(List<MessageTypeInSubscription> messageTypeInSubscription) {
		this.messageTypeInSubscription = messageTypeInSubscription;
	}
	public List<Message> getMessageList() {
		return messageList;
	}
	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}
	
}
