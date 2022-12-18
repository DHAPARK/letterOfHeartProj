package com.project.letterOfHeart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.letterOfHeart.domain.Message;
import com.project.letterOfHeart.domain.Tree;
import com.project.letterOfHeart.repository.MessageRepository;
import com.project.letterOfHeart.repository.TreeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TreeService {

	private final TreeRepository treeRepository;
	private final MessageRepository messageRepository;
	
	@Transactional
	public void save(Tree tree) {
		treeRepository.save(tree);
	}
	
//	@Transactional
//	public Integer countById(String u_id) {
//		List<Message> msg =  messageRepository.findById(u_id);
//		int count = msg.size();
//		return count;
//	}

	@Transactional(readOnly = true)
	public Tree findOne(Long id) {
		return treeRepository.findOne(id);
	}
}
