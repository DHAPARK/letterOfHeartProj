package com.project.letterOfHeart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.letterOfHeart.repository.TreeRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TreeService {

	private final TreeRepository repository;
	
<<<<<<< HEAD
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
=======
>>>>>>> parent of 74974f6 (기초작업)
}
