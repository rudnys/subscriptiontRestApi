/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subscription.restApi.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;

/**
 *
 * @author rudny
 */
@Entity
public class Subscription {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String email;
    
    @CreatedDate
    private Date creationDate;
    
    @OneToMany(mappedBy="subscription",fetch=FetchType.EAGER)
    private List<MessageTypeInSubscription> messageTypeInSubscription=new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<MessageTypeInSubscription> getMessageTypeInSubscription() {
		return messageTypeInSubscription;
	}

	public void setMessageTypeInSubscription(List<MessageTypeInSubscription> messageTypeInSubscription) {
		this.messageTypeInSubscription = messageTypeInSubscription;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	

    
}
