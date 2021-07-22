package com.guild.simple.chat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.guild.simple.chat.model.Message;

@Service
public interface MessageService {

	public Long sendMessage(Message message);
	public List<Message> getReceivedMessages(String sender,String recepient,Optional<String> numberOfDays);
	public List<Message> getMessagesForSender(String sender,Optional<String>  numberOfDays);
	
}
