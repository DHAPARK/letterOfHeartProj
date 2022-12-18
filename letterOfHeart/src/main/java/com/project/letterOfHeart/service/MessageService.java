package com.project.letterOfHeart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.letterOfHeart.domain.Message;
import com.project.letterOfHeart.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	public void wirte(Message message) {
		messageRepository.save(message);
	}
	
	
}
