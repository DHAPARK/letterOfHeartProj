package com.project.letterOfHeart.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.letterOfHeart.domain.Users;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UsersRepository {

	/*
	 * @PersistenceContext : jpa가 지원해주는 표준,
	 * 						spring이 entitymanager를 만들어서 em에다가 주입
	 */
	/*
	 * @Autowired			: spring book 라이브러리 사용시 @Autowired를 지원한다. 
	 */
//	@PersistenceContext
	@Autowired
	private final EntityManager em;
	
	/*
	 *  
	 	생성자 주입
		private MemberRepository(EntityManager em) {
			this.em = em;
		}
		
	 */
	
	// 저장
	public void save(Users users) {
		em.persist(users);
	}
	
	// 1건 조회
	public Users findOne(Long id) {		
		return em.find(Users.class, id);
	}
	
	
	// 여러건 조회
	public List<Users> findAll(){
		return em.createQuery("select u from Users u", Users.class).getResultList();
	}
	
	// 아이디로 조회 
	public List<Users> findById(String u_Id){
		return em.createQuery("select u from Users u where u.u_Id = :u_Id", Users.class)
				 .setParameter("u_Id", u_Id).getResultList();

	}
	
	public Users findByUsersId(String u_Id){
		List<Users> all = findAll();
		
		for(Users u : all) {
			if(u.getU_Id().equals(u_Id)) {
				return u;
			}
		}
		
		return null;
	}
}
