package subscription.restApi.jpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import subscription.restApi.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{
	
	public Message findByMessage(String message);
}
