package com.mindtree.subscription.dto;

public class ChannelDto {

	private int channelId;
	private String channelName;
	private float costPerMonth;
	
	public ChannelDto() {
		super();
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public float getCostPerMonth() {
		return costPerMonth;
	}

	public void setCostPerMonth(float costPerMonth) {
		this.costPerMonth = costPerMonth;
	}
	
}
