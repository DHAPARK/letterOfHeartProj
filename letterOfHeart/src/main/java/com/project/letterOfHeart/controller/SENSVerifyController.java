package com.project.letterOfHeart.controller;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.letterOfHeart.dto.MessagesRequestDto;
import com.project.letterOfHeart.dto.SendSmsResponseDto;
import com.project.letterOfHeart.service.SmsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SENSVerifyController {

	private final SmsService smsService;

	// pdh
	//@PostMapping("/message/code")
	@GetMapping("/message/code")
	public String sendSms(@RequestBody MessagesRequestDto messageDto) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
		System.out.println(" abcdefgabcdefg ") ;
		System.out.println(" abcdefgabcdefg ") ;
        SendSmsResponseDto responseDto = smsService.sendSms(messageDto);
        
        
        //return responseDto;
        return "suc";
    }

	// 로그인
//	@PostMapping("/users/login")
//	public String loginOk(@ModelAttribute LoginForm form, Model model, RedirectAttributes redirectAttributes,
//			HttpServletRequest request, @RequestParam(defaultValue = "/createTree") String redirectURL) {
//
//		Users loginUsers = userService.login(form.getU_Id(), form.getPassword());
//		if (loginUsers == null) {
//			// 로그인 실패
//			return "index";
//		}
//
//		// 로그인 성공
//		HttpSession session = request.getSession();
//		// 세션에 로그인 회원 정보 보관
//		session.setAttribute(SessionConst.LOGIN_USERS, loginUsers);
//		session.setAttribute("userInfo", loginUsers.getNickname());
//		return "myTree";
//	}

}
