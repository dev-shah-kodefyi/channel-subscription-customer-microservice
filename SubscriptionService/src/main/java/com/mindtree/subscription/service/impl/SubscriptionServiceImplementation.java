package com.mindtree.subscription.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.mindtree.subscription.dto.ChannelDto;
import com.mindtree.subscription.dto.CustomerDto;
import com.mindtree.subscription.dto.SubscriptionDto;
import com.mindtree.subscription.dto.SubscriptionResponseDto;
import com.mindtree.subscription.entity.Subscription;
import com.mindtree.subscription.exception.ChannelNotFoundException;
import com.mindtree.subscription.exception.InvalidSubscription;
import com.mindtree.subscription.exception.ServiceException;
import com.mindtree.subscription.exception.SubscriberNotFoundException;
import com.mindtree.subscription.repository.SubscriptionRepository;
import com.mindtree.subscription.service.ChannelFeign;
import com.mindtree.subscription.service.CustomerFeign;
import com.mindtree.subscription.service.SubscriptionService;

@Service
public class SubscriptionServiceImplementation implements SubscriptionService{

	@Autowired
	private SubscriptionRepository subscriptionRepo;
	
	@Autowired
	private CustomerFeign customerFeign;
	
	@Autowired
	private ChannelFeign channelFeign;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	//To Insert a new Subscription
	@Override
	@CacheEvict(value = "subscription", allEntries = true)
	public SubscriptionDto insertSubscription(SubscriptionDto subscriptDto) throws ServiceException
	{
		Subscription subscript = null;
		int flag = 0;
		
		CustomerDto	customer = customerFeign.getCustomer(subscriptDto.getSubscriberId()).getBody();
		
		ChannelDto channel = channelFeign.getChannel(subscriptDto.getChannelId()).getBody();
			
		for(Subscription subscription : subscriptionRepo.findAll())
		{
			if(subscription.getChannelId() == channel.getChannelId() && subscription.getSubscriberId().equals(customer.getSubscriberId()))
			{
				flag = 1;
				break;
			}
		}
		
		if(flag == 0)
		{
			if(subscriptDto.getSubscriberId().equals(customer.getSubscriberId()))
			{
				if(subscriptDto.getChannelId() == channel.getChannelId())
				{
					subscript = subscriptionRepo.save(convertDtoToEntity(subscriptDto));
				}
				else
				{
					throw new ChannelNotFoundException("Invalid Channel ID!");
				}
			}
			else
			{
				throw new SubscriberNotFoundException("Invalid Subscriber ID!");
			}
		}
		else
		{
			throw new InvalidSubscription("Already Subscribed!");
		}
		
		return convertEntityToDto(subscript);
	}

	
	//To retrieve all the subscription details with respect to the particular Subscriber ID
	@Override
	@Cacheable(value = "subscription")
	public SubscriptionResponseDto getAllDetails(String subscriberId) throws ServiceException
	{
		List<Subscription> subscriptionList = subscriptionRepo.findAllBySubscriberId(subscriberId);
		
		if(subscriptionList.size() > 0)
		{
			List<ChannelDto> channelList = new ArrayList<ChannelDto>();
			float totalCost = 0;
			
			for(Subscription subscription : subscriptionList)
			{			
				ChannelDto channel = channelFeign.getChannel(subscription.getChannelId()).getBody();
				totalCost = totalCost + channel.getCostPerMonth();
				channelList.add(channel);
			}
			
			CustomerDto customer;
			
			try
			{
				customer = customerFeign.getCustomer(subscriberId).getBody();
			}
			catch(HttpClientErrorException e)
			{
				throw new ServiceException(e.getResponseBodyAsString());
			}
			
			SubscriptionResponseDto result = new SubscriptionResponseDto();
			result.setSubscriberId(subscriberId);
			result.setSubscriberName(customer.getFirstName() + " " +customer.getLastName());
			result.setChannelList(channelList);
			result.setTotalSubscriptionCost(totalCost);
			
			return result;
		}
		else
		{
			throw new SubscriberNotFoundException("No Subscriptions for this ID!");
		}
		
	}
	
	//Converting Entity to DTO
	private SubscriptionDto convertEntityToDto(Subscription subscript)
	{
		return modelMapper.map(subscript, SubscriptionDto.class);
	}

	//Converting DTO to Entity
	private Subscription convertDtoToEntity(SubscriptionDto subscriptDto)
	{
		return modelMapper.map(subscriptDto, Subscription.class);
	}

}
