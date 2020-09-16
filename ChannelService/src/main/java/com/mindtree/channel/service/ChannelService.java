package com.mindtree.channel.service;

import java.util.Set;

import com.mindtree.channel.dto.ChannelDto;
import com.mindtree.channel.exception.ServiceException;

public interface ChannelService {

	ChannelDto insertChannel(ChannelDto channelDto) throws ServiceException;

	ChannelDto retriveChannel(int channelId) throws ServiceException;

	Set<ChannelDto> retriveAllChannels();

	ChannelDto updateChannel(int channelId) throws ServiceException;

}
