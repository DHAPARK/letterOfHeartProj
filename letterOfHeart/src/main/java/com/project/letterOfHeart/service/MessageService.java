package com.project.letterOfHeart.service;

import java.util.List;


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
	
	// 해당 아이디 리스트 조회
	@Transactional(readOnly = true)
	public List<Message> messageList(Long id){
		return messageRepository.findByIdList(id);
	}
	
	@Transactional(readOnly = true)
	public int findAllById(Long id) {
		return messageRepository.findByIdList(id).size();
	}
	
}
