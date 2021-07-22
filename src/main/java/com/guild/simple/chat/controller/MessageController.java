package com.guild.simple.chat.controller;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.guild.simple.chat.model.Message;
import com.guild.simple.chat.service.MessageService;

 
@RestController
@RequestMapping("/api/message")
public class MessageController {
	
	@Autowired
	MessageService messageService;
	
	@PostMapping 
	public Long sendMessage(@Valid @RequestBody Message message) { 
		message.setSentDate(LocalDateTime.now());
		return messageService.sendMessage(message);
	}

	@GetMapping(path = "/{sender}")
	public List<Message> getMessagesForSender(@PathVariable String sender,@RequestParam Optional<String>  numberOfDays) { 
		return messageService.getMessagesForSender(sender,numberOfDays);
	}

	@GetMapping(path = "/{sender}/{recipient}")
	public List<Message> getMessageBetweenSenderAndReceiver(@PathVariable String sender,@PathVariable String recipient,@RequestParam Optional<String> numberOfDays) { 
		return messageService.getReceivedMessages(sender, recipient,numberOfDays);
	}



}
