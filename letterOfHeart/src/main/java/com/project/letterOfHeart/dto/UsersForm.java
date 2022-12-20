package com.project.letterOfHeart.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UsersForm {
	private Long id;
	
	@NotBlank(message = "회원 아이디는 필수입니다.")	
	private String accoutid;
	@NotBlank(message = "회원 비밀번호는 필수입니다.")
	private String password;
	
	@NotBlank(message = "회원 닉네임은 필수입니다.")
	private String nickname;
	private LocalDateTime createDate;
}
