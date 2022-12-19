package com.project.letterOfHeart.controller;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.boot.autoconfigure.cassandra.CassandraProperties.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.letterOfHeart.domain.Users;
import com.project.letterOfHeart.dto.LoginForm;
import com.project.letterOfHeart.dto.MessagesRequestDto;
import com.project.letterOfHeart.dto.SendSmsResponseDto;
import com.project.letterOfHeart.service.SmsService;
import com.project.letterOfHeart.service.UsersService;
import com.project.letterOfHeart.session.SessionConst;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SENSVerifyController {

	private final SmsService smsService;

	// pdh
	@PostMapping("/message/code")
	public SendSmsResponseDto sendSms(@RequestBody MessagesRequestDto messageDto) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        SendSmsResponseDto responseDto = smsService.sendSms(messageDto);
        return responseDto;
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
