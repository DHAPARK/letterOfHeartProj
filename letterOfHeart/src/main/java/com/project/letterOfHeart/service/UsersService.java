package com.project.letterOfHeart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.letterOfHeart.domain.Users;
import com.project.letterOfHeart.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService {

	private final UsersRepository usersRepository;
	
	// 회원가입
	// @Transactional : org.springframework.transaction.annotation
	@Transactional
	public Long join(Users users) {
		usersRepository.save(users);
		return users.getId();
	}
	
	// 로그인
	public Users login(String u_Id, String password) {
		
		Users users = usersRepository.findByUsersId(u_Id);
		
		if(users != null && users.getU_Id().equals(u_Id)
				&& users.getPassword().equals(password)) {
			// 로그인 성공
			return users;
		}else {
			return null;	
		}
		
	}
}
