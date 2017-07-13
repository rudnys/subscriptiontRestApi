package subscription.restApi.jpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import subscription.restApi.entity.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{
	
	public Subscription findByEmail(String email);
}
