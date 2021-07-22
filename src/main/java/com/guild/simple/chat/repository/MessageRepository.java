package com.guild.simple.chat.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guild.simple.chat.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
  	
	
    LocalDate thirtyDaysAgoDate = LocalDate.now().minusDays(30);

	List<Message> findTop100BySenderAndRecipientOrderBySentDateDesc(String sender,String recipient);
	List<Message> findBySenderAndRecipientAndSentDateGreaterThanOrderBySentDateDesc(String sender,String recipient,LocalDateTime thirtyDaysAgoDate);

	List<Message> findTop100BySenderOrderBySentDateDesc(String sender);
	List<Message> findBySenderAndSentDateGreaterThanOrderBySentDateDesc(String sender,LocalDateTime thirtyDaysAgoDate);

}
