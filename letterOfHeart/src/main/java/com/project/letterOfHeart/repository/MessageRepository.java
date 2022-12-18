package com.project.letterOfHeart.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.letterOfHeart.domain.Message;
import com.project.letterOfHeart.domain.Tree;

import lombok.RequiredArgsConstructor;
@Repository
@RequiredArgsConstructor
public class MessageRepository{

	private final EntityManager em;
	
	public void save(Message message) {
		em.persist(message);
	}

	public List<Message> findByIdList(Long id) {
		return em.createQuery("select m from Message m where user_id=:id",Message.class)
				.setParameter("id", id).getResultList();
	}




}
