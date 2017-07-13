package subscription.restApi.jpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import subscription.restApi.entity.MessageTypeInSubscription;
import subscription.restApi.entity.Subscription;

public interface MessageTypeInSubscriptionRepository extends JpaRepository<MessageTypeInSubscription, Long>{
	
	public void deleteBySubscriptionId(Long id);
}
