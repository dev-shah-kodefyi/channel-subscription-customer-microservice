package com.mindtree.subscription.service;

import com.mindtree.subscription.dto.SubscriptionDto;
import com.mindtree.subscription.dto.SubscriptionResponseDto;
import com.mindtree.subscription.exception.ServiceException;

public interface SubscriptionService {

	SubscriptionDto insertSubscription(SubscriptionDto subscriptDto) throws ServiceException;

	SubscriptionResponseDto getAllDetails(String subscriberId) throws ServiceException;

}
