package com.mindtree.subscription.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.subscription.entity.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer>{

	@Query(value = "select * from subscription where subscriber_id =?1", nativeQuery = true)
	List<Subscription> findAllBySubscriberId(String subscriberId);

}
