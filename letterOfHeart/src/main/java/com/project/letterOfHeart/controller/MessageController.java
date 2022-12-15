package com.project.letterOfHeart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.letterOfHeart.domain.Message;
import com.project.letterOfHeart.service.MessageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MessageController {
	@Autowired
	private MessageService messageService;

	@GetMapping("/message/write")
	public String messageForm() {
		return "message/message";
	}

	@PostMapping("/board/writepro")
	public String boardWritePro(Message message) {

		messageService.wirte(message);

		return "index";
	}

}
