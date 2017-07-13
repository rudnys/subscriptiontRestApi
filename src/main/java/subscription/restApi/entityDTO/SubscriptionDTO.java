/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subscription.restApi.entityDTO;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

/**
 *
 * @author rudny
 */

public class SubscriptionDTO {

	private Long id;
	
	@Email
	@NotNull
	private String email;

	private List<String> messageTypeList;
	
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

	public List<String> getMessageTypeList() {
		return messageTypeList;
	}

	public void setMessageTypeList(List<String> messageTypeList) {
		this.messageTypeList = messageTypeList;
	}

	
	
}
