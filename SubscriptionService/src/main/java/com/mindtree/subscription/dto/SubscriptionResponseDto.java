package com.mindtree.subscription.dto;

import java.util.List;

public class SubscriptionResponseDto {

	private String subscriberId;
	private String subscriberName;
	
	private float totalSubscriptionCost;
	
	private List<ChannelDto> channelList;

	public SubscriptionResponseDto() {
		super();
	}

	public String getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}

	public String getSubscriberName() {
		return subscriberName;
	}

	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}

	public float getTotalSubscriptionCost() {
		return totalSubscriptionCost;
	}

	public void setTotalSubscriptionCost(float totalSubscriptionCost) {
		this.totalSubscriptionCost = totalSubscriptionCost;
	}

	public List<ChannelDto> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<ChannelDto> channelList) {
		this.channelList = channelList;
	}
		
}
