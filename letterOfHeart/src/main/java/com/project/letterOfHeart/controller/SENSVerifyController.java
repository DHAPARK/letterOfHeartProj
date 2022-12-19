package com.project.letterOfHeart.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.project.letterOfHeart.domain.Users;
import com.project.letterOfHeart.dto.LoginForm;
import com.project.letterOfHeart.dto.UsersForm;
import com.project.letterOfHeart.service.UsersService;
import com.project.letterOfHeart.session.SessionConst;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SENSVerifyController {

	private final SendSmsResponseDto smsService;

	// pdh
	@PostMapping("/message/code")
	void sendSms() throws JsonProcessingException, ParseException, UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        smsService.sendSms("01012345678","문자 보내는거 너무 쉬워요!");
    }

	// 로그인
	@PostMapping("/users/login")
	public String loginOk(@ModelAttribute LoginForm form, Model model, RedirectAttributes redirectAttributes,
			HttpServletRequest request, @RequestParam(defaultValue = "/createTree") String redirectURL) {

		Users loginUsers = usersService.login(form.getU_Id(), form.getPassword());
		if (loginUsers == null) {
			// 로그인 실패
			return "index";
		}

		// 로그인 성공
		HttpSession session = request.getSession();
		// 세션에 로그인 회원 정보 보관
		session.setAttribute(SessionConst.LOGIN_USERS, loginUsers);
		session.setAttribute("userInfo", loginUsers.getNickname());
		return "myTree";
	}


}
