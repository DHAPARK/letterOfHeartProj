package com.project.letterOfHeart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.letterOfHeart.domain.Users;
import com.project.letterOfHeart.repository.UsersRepository;
import com.project.letterOfHeart.repository.repository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService {

	private final repository repository;
	private final UsersRepository usersRepository;
	
	// 회원가입
	@Transactional
	public Long join(Users users) {
		usersRepository.save(users);
		return users.getId();
	}
	
	// 로그인
	public Users login(String accoutid, String password) {
		
		Users users = usersRepository.findByAccountsId(accoutid);
//		if(users != null && users.getUsername().equals(accoutid)
//				&& users.getPassword().equals(password)) {
		if(users != null && users.getPassword().equals(password)) {
			// 로그인 성공
			return users;
		}else {
			return null;	
		}
		
	}
	
	//  1건 조회
	public Users findOne(Long id) {
		return usersRepository.findOne(id);
	}
	
	public Users findById(String accoutid) {
		return usersRepository.findByAccountId(accoutid);
	}
	
	
}
