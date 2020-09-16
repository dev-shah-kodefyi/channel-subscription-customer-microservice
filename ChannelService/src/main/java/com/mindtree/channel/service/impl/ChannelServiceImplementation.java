package com.mindtree.channel.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mindtree.channel.dto.ChannelDto;
import com.mindtree.channel.entity.Channel;
import com.mindtree.channel.exception.ChannelAlreadyPresentException;
import com.mindtree.channel.exception.ChannelNotFoundException;
import com.mindtree.channel.exception.InvalidChannelIdException;
import com.mindtree.channel.exception.ServiceException;
import com.mindtree.channel.repository.ChannelRepository;
import com.mindtree.channel.service.ChannelService;

@Service
public class ChannelServiceImplementation implements ChannelService{

	@Autowired
	private ChannelRepository channelRepo;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	//Inserting new Channel into Database
	@Override
	public ChannelDto insertChannel(ChannelDto channelDto) throws ServiceException
	{
		Channel channelInserted = null;
		
		try
		{
			if(!channelRepo.existsById(channelDto.getChannelId()))
			{
				try
				{
					if(channelDto.getChannelId() > 99 && channelDto.getChannelId() < 1000)
					{
						Channel channel = convertDtoToEntity(channelDto);
						channelInserted = channelRepo.save(channel);
					}
					else
					{
						throw new InvalidChannelIdException("Channel Id Invalid!");
					}
				}
				catch(InvalidChannelIdException e)
				{
					throw new ServiceException(e.getMessage());
				}
			}
			else
			{
				throw new ChannelAlreadyPresentException("Channel Id Already Present!");
			}
		}
		catch(ChannelAlreadyPresentException e)
		{
			throw new ServiceException(e.getMessage());
		}
		
		return convertEntityToDto(channelInserted);

	}

	//To retrieve the channels from the database
	@Override
	public ChannelDto retriveChannel(int channelId) throws ServiceException
	{
		Channel channelRetrived = null;
		
		try
		{
			if(channelRepo.existsById(channelId))
			{
				channelRetrived = channelRepo.findById(channelId).get();
			}
			else
			{
				throw new ChannelNotFoundException("Channel Not Found! Check Id!");
			}
		}
		catch(ChannelNotFoundException e)
		{
			throw new ServiceException(e.getMessage());
		}
		
		return convertEntityToDto(channelRetrived);
	}
	
	//To Retrieve All The Channels from the database
	@Override
	@Cacheable(value = "channels")
	public Set<ChannelDto> retriveAllChannels()
	{
		List<Channel> channelList = channelRepo.findAll();
		
		Set<ChannelDto> channelDtoSet = channelList
												.stream()
												.map(channel -> convertEntityToDto(channel))
												.collect(Collectors.toSet());
		return channelDtoSet;
	}
	
	
	//Updating the Channel Cost per Month by 1 rs
	@Override
	@CachePut(value = "channels")
	public ChannelDto updateChannel(int channelId) throws ServiceException
	{
		if(channelRepo.existsById(channelId))
		{
			Channel channel = channelRepo.findById(channelId).get();
			channel.setCostPerMonth(channel.getCostPerMonth() + 1);
			channelRepo.saveAndFlush(channel);
		}
		else
		{
			throw new ChannelNotFoundException("Invalid Channel ID!");
		}
		
		return null;
	}
	
	//Converting Entity To Dto
	private ChannelDto convertEntityToDto(Channel channel)
	{
		return modelMapper.map(channel, ChannelDto.class);
	}

	//Converting Dto To Entity
	private Channel convertDtoToEntity(ChannelDto channelDto)
	{
		return modelMapper.map(channelDto, Channel.class);
	}

}
