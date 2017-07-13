package subscription.restApi.jpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import subscription.restApi.entity.MessageType;

public interface MessageTypeRepository extends JpaRepository<MessageType, Long>{
	
	public MessageType findByType(String type);
	
	@Query("Select mt.type, count(mit.id) from MessageType mt inner join mt.messageTypeInSubscription mit where mt.type in :messageTypeList group by mt.id")
	public List<Object[]> countBySubscriptionRelated(@Param("messageTypeList") List<String> messageTypeList);
}
