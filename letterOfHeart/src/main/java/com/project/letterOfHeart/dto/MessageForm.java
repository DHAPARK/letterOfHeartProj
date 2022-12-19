package com.project.letterOfHeart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MessageForm {
	private Long id; 	// 유저 PK
    private String titleNickname;
    private String content;

}