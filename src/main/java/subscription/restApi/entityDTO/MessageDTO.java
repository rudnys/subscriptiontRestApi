package subscription.restApi.entityDTO;

public class MessageDTO {
	
	
	private Long id;
	private  String message;
	private String messageTypeId;
	private String messageTypeType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessageTypeId() {
		return messageTypeId;
	}
	public void setMessageTypeId(String messageTypeId) {
		this.messageTypeId = messageTypeId;
	}
	public String getMessageTypeType() {
		return messageTypeType;
	}
	public void setMessageTypeType(String messageTypeType) {
		this.messageTypeType = messageTypeType;
	}
	
}
