package com.guild.simple.chat.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guild.simple.chat.exception.InvalidRequestException;
import com.guild.simple.chat.exception.RecordsNotFoundException;
import com.guild.simple.chat.model.Message;
import com.guild.simple.chat.repository.MessageRepository;
import com.guild.simple.chat.util.GlobalConstants;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	MessageRepository messageRepository;
	
	@Override
	public Long sendMessage(Message message) {		
 
		Message m=  messageRepository.save(message);		
		return m.getId();
	}

	@Override
	public List<Message> getReceivedMessages(String sender, String recipient,Optional<String> numberOfDays) {
		
		List<Message> messages = new ArrayList<>();
		
		if(StringUtils.isBlank(sender)) {
			throw new InvalidRequestException(sender, GlobalConstants.INVALID_SENDER);
		}
		if(StringUtils.isBlank(recipient)) {
			throw new InvalidRequestException(sender, GlobalConstants.INVALID_RECIPIENT);
		}
		
		if(numberOfDays.isPresent()) { 
			
			int days;
			try {
				days = Integer.parseInt(numberOfDays.get());
			}catch(Exception e) {
				throw new InvalidRequestException(numberOfDays.get(),GlobalConstants.INVALID_DAYS_IN_THE_REQUEST);
			}
			
			if(days>30) days = 30; 
			
			LocalDateTime thirtyDaysAgoDate = LocalDateTime.now().minusDays(days);
		    
		    messages = messageRepository.findBySenderAndRecipientAndSentDateGreaterThanOrderBySentDateDesc(sender,recipient,thirtyDaysAgoDate);
		    

		} else {
		
			messages = messageRepository.findTop100BySenderAndRecipientOrderBySentDateDesc(sender,recipient);
		}
				
		if(messages.isEmpty()) {
			throw new RecordsNotFoundException(sender, GlobalConstants.NO_RECORDS_FOUND); 
		}
		
		return messages;
	}

	@Override
	public List<Message> getMessagesForSender(String sender,Optional<String>  numberOfDays) {
		
		
		List<Message> messages = new ArrayList<>();

		if(StringUtils.isBlank(sender)) {
			throw new InvalidRequestException(sender, "Invalid sender");
		}
		
		
		if(numberOfDays.isPresent()) { 
			
			int days;
			try {
				days = Integer.parseInt(numberOfDays.get());
			}catch(Exception e) {
				throw new InvalidRequestException(numberOfDays.get(),GlobalConstants.INVALID_DAYS_IN_THE_REQUEST);
			}
			
			if(days>30) days = 30; 
			
		    LocalDateTime thirtyDaysAgoDate = LocalDateTime.now().minusDays(days);
		    		    
		    messages = messageRepository.findBySenderAndSentDateGreaterThanOrderBySentDateDesc(sender,thirtyDaysAgoDate);
		    

		} else {
		
			messages = messageRepository.findTop100BySenderOrderBySentDateDesc(sender);
		}
		
		if(messages.isEmpty()) {
			throw new RecordsNotFoundException(sender, GlobalConstants.NO_RECORDS_FOUND); 
		}


		return messages;
	}

}
