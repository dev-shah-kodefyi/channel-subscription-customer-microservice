package com.mindtree.channel.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.channel.dto.ChannelDto;
import com.mindtree.channel.exception.ServiceException;
import com.mindtree.channel.service.ChannelService;

@RestController
@RequestMapping("/channel")
public class ChannelController {

	@Autowired
	private ChannelService channelService;
	
	@PostMapping("/addChannel")
	public ResponseEntity<ChannelDto> addChannel(@RequestBody ChannelDto channelDto) throws ServiceException
	{
		ChannelDto channelAdded = channelService.insertChannel(channelDto);
		return new ResponseEntity<ChannelDto>(channelAdded, HttpStatus.OK);
	}
	
	@PutMapping("/updateChannelPrice/{channelId}")
	public ResponseEntity<ChannelDto> updateChannel(@PathVariable int channelId) throws ServiceException
	{
		ChannelDto channelUpdated = channelService.updateChannel(channelId);
		return new ResponseEntity<ChannelDto>(channelUpdated, HttpStatus.OK);
	}
	
	
	@GetMapping("/getChannel/{channelId}")
	public ResponseEntity<ChannelDto> getChannel(@PathVariable int channelId) throws ServiceException
	{
		ChannelDto channelRetrived = channelService.retriveChannel(channelId);
		return new ResponseEntity<ChannelDto>(channelRetrived, HttpStatus.OK);
	}
	
	@GetMapping("/getAllChannels")
	public ResponseEntity<Set<ChannelDto>> getAllChannels()
	{
		Set<ChannelDto> channelSet = channelService.retriveAllChannels();
		return new ResponseEntity<Set<ChannelDto>>(channelSet, HttpStatus.OK);
	}
}
