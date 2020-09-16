package com.mindtree.subscription.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.subscription.dto.SubscriptionDto;
import com.mindtree.subscription.dto.SubscriptionResponseDto;
import com.mindtree.subscription.exception.ServiceException;
import com.mindtree.subscription.service.ChannelFeign;
import com.mindtree.subscription.service.CustomerFeign;
import com.mindtree.subscription.service.SubscriptionService;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;
	
	@PostMapping("/addSubscription")
	public ResponseEntity<SubscriptionDto> addSubscription(@RequestBody SubscriptionDto subscriptDto) throws ServiceException
	{
		SubscriptionDto subscriptionAdded = subscriptionService.insertSubscription(subscriptDto);
		return new ResponseEntity<SubscriptionDto>(subscriptionAdded, HttpStatus.OK);
	}
	
	@GetMapping("/getSubscriptionDetails/{subscriberId}")
	public ResponseEntity<SubscriptionResponseDto> getSubscriptionDetails(@PathVariable String subscriberId) throws ServiceException
	{
		SubscriptionResponseDto result = subscriptionService.getAllDetails(subscriberId);
		return new ResponseEntity<SubscriptionResponseDto>(result, HttpStatus.OK);
	}
	
}
