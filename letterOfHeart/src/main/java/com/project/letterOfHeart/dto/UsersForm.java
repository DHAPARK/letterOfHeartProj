package com.project.letterOfHeart.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UsersForm {
	private Long id;
	
	@NotBlank(message = "회원 아이디는 필수입니다.")	
//	@Pattern(regexp = "^[a-z0-9]{4,20}$", message = "아이디는 영어 소문자와 숫자만 사용하여 4~20자리여야 합니다.")
	private String accoutid;
	
	@NotBlank(message = "회원 비밀번호는 필수입니다.")
//	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$"
//	, message = "비밀번호는 영문 대소문자, 숫자, 특수문자를 1개 이상 포함, 8~16자리수여야 합니다.")
	private String password;
	
	@NotBlank(message = "회원 닉네임은 필수입니다.")
//    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,8}$", message = "닉네임은 특수문자를 제외한 2~8자리여야 합니다.")
	private String nickname;
	
	@Pattern(regexp = "(\\\\d{3})(\\\\d{3,4})(\\\\d{4})", message = "휴대폰번호 8자리를 입력해주세요")
	private String phone;
	private LocalDateTime createDate;
}
