package com.project.letterOfHeart.service;

<<<<<<< HEAD
import java.util.List;


=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> parent of 74974f6 (기초작업)
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.letterOfHeart.domain.Message;
import com.project.letterOfHeart.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

	private final MessageRepository messageRepository;
	@Transactional
	public void wirte(Message message) {
		messageRepository.save(message);
	}
<<<<<<< HEAD
	@Transactional(readOnly = true)
	public List<Message> messageList(Long id){
		return messageRepository.findByIdList(id);
	}
	
	
=======
>>>>>>> parent of 74974f6 (기초작업)
}
