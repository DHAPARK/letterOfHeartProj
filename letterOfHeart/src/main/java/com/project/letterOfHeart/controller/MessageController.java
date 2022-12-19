package com.project.letterOfHeart.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.letterOfHeart.domain.Message;
import com.project.letterOfHeart.domain.Tree;
import com.project.letterOfHeart.domain.Users;
import com.project.letterOfHeart.dto.MessageForm;
import com.project.letterOfHeart.service.MessageService;
import com.project.letterOfHeart.service.TreeService;
import com.project.letterOfHeart.service.UsersService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor  
public class MessageController {

	private final MessageService messageService;
	private final UsersService userService;
	private final TreeService treeService;

	@GetMapping("/message/write")
	public String messageForm( Model model, MessageForm messageForm) {
		model.addAttribute("messageForm", new MessageForm());
		return "redirect:/myTree/"+ messageForm.getId();
	}
	
	@PostMapping("/message/write")
	public String boardWritePro(MessageForm messageForm ,Model model) {
		Message message = new Message();
		// 유저 아이디 1건 조회
		Users user = userService.findOne(messageForm.getId());
		// 트리 아이디 1건 조회
		Tree tree = treeService.findOne(messageForm.getId());
		
		LocalDateTime opendate = LocalDateTime.parse("2022-12-25T00:00:00.000");
		message.setTitleNickname(messageForm.getTitleNickname());
		message.setContent(messageForm.getContent());
		// 날짜 비교 조건은 프론트단에서?? 둘다?
		message.setOpenDate(opendate);
		message.setSendDate(LocalDateTime.now());
		message.setStatus(0L);
		message.setUsers(user);
		message.setTree(tree);
		messageService.wirte(message);
		
		return "redirect:/myTree/"+messageForm.getId();
	}

}

