package com.project.letterOfHeart.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsersForm {

	@NotEmpty(message = "회원 아이디는 필수입니다.")
	private String u_Id;
	private String password;
	private String nickname;
	private String phone;
	private LocalDateTime createDate;
}
