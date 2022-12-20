package com.project.letterOfHeart.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class LoginForm {
	private Long id;
	private String accoutid;
	private String password;
}
