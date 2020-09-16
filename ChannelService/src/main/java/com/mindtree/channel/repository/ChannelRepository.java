package com.mindtree.channel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.channel.entity.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Integer>{

}
