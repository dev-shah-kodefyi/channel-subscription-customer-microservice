package com.mindtree.channel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "channel")
public class Channel {
	
	@Id
	@Column(name = "channel_id")
	private int channelId;
	
	@Column(name = "channel_name")
	private String channelName;
	
	@Column(name = "cost_per_month")
	private float costPerMonth;

	public Channel() {
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
