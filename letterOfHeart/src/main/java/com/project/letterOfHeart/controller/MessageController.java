package com.project.letterOfHeart.controller;

import java.time.LocalDateTime;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
//	@Autowired
	private final MessageService messageService;
	private final UsersService userService;
	private final TreeService treeService;

	@GetMapping("/message/write")
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
	}

}


//@Controller
//@RequiredArgsConstructor
//public class MessageController {
//
//    private final MessageService messageService;
//
//    @GetMapping("/message/write")
//    public String writeForm( Model model) {
//    	MessageForm messageForm = new MessageForm();
//        model.addAttribute("messageForm", messageForm );
//        return "myTree";
//    }
//
//    @PostMapping("/message/write")
//    public String write( MessageForm messageForm) {
//        Message message = new Message();
//        message.setTitleNickname(messageForm.getTitleNickname());
//        message.setContent(messageForm.getContent());
//
//
//        messageService.wirte(message);
//        return "myTree";
//    }
//}