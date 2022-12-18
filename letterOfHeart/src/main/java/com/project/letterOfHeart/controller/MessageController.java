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
<<<<<<< HEAD
	public String messageForm( Model model, MessageForm messageForm) {
		model.addAttribute("messageForm", new MessageForm());
//		model.addAttribute("messages", messageService.messageList());
		return "redirect:/myTree/"+ messageForm.getId();
	}
	@PostMapping("/message/write")
	public String boardWritePro(MessageForm messageForm ,Model model) {
		Message message = new Message();
		Users user = userService.findOne(messageForm.getId());
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
		
		System.out.println(messageForm.getId());
		return "redirect:/myTree/"+messageForm.getId();
=======
	public String messageForm() {
		return "message/message";
	}

	@PostMapping("/board/writepro")
	public String boardWritePro(Message message) {

		messageService.wirte(message);

		return "index";
>>>>>>> parent of 74974f6 (기초작업)
	}

}
