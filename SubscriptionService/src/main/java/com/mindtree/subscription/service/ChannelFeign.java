package com.mindtree.subscription.service;

import org.hibernate.service.spi.ServiceException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mindtree.subscription.dto.ChannelDto;

@FeignClient(name = "channel-service" , url = "http://localhost:9092/channel")
public interface ChannelFeign {

	@GetMapping("/getChannel/{channelId}")
	public ResponseEntity<ChannelDto> getChannel(@PathVariable int channelId) throws ServiceException;
}
