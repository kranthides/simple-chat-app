package com.guild.simple.chat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.guild.simple.chat.exception.RecordsNotFoundException;
import com.guild.simple.chat.model.Message;
import com.guild.simple.chat.service.MessageService;

@SpringBootTest
class SimpleChatAppApplicationTests {

	@Autowired
	MessageService messageService;

	@Test
	@DirtiesContext
	void testOnetoOneSendMessage() throws Exception {
		
		Message m = new Message();
		m.setSender("fromTest");
		m.setRecipient("toTest");
		m.setShortMessage("Hi From Test Case");
		Long id = messageService.sendMessage(m);
		
		assertNotNull(id);
		List<Message> messageFromDb = messageService.getReceivedMessages(m.getSender(),m.getRecipient(),Optional.empty());
		assertEquals(id,messageFromDb.get(0).getId());
		
	}
	
	@Test
	@DirtiesContext
	void testOnly100RecordsAreReturning() throws Exception {
		
		for(int i = 0; i< 150 ; i++) { 
			Message m = new Message();
			m.setSender("fromTest");
			m.setRecipient("toTest");
			m.setShortMessage("Hi From Test Case -> "+ i );
			messageService.sendMessage(m);			
		}
		
		List<Message> messageFromDb = messageService.getReceivedMessages("fromTest","toTest",Optional.empty());
		assertEquals(100,messageFromDb.size());
		
	}
	
	
	
	@Test
	@DirtiesContext
	void testOldRecordsAreNotReturning()  {
		
		Message m = new Message();
		m.setSender("fromTest");
		m.setRecipient("toTest");
		LocalDateTime fortyDaysAgoDate = LocalDateTime.now().minusDays(40);
		m.setSentDate(fortyDaysAgoDate);
		
		messageService.sendMessage(m);	
		
	    assertThrows(RecordsNotFoundException.class,
	            ()->{messageService.getReceivedMessages("fromTest","toTest",Optional.of("30"));
	            });

 		
	}


}
