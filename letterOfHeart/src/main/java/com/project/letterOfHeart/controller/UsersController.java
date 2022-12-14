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
public class UsersController {
	
	private final UsersService usersService;
	
	@GetMapping("/")
	public String loginForm(@ModelAttribute("loginForm")LoginForm loginForm,
							@ModelAttribute("usersForm")UsersForm usersForm) {
		return "index";
	}
	
	// 로그인
	@PostMapping("/users/login")
	public String loginOk(@ModelAttribute LoginForm form, Model model, 
			RedirectAttributes redirectAttributes, HttpServletRequest request,
			@RequestParam(defaultValue="/createTree")String redirectURL) {
		
		Users loginUsers = usersService.login(form.getU_Id(), form.getPassword());
		if( loginUsers == null ) {
			// 로그인 실패
			return "index";
		} 
		
		// 로그인 성공
		HttpSession session = request.getSession();
		// 세션에 로그인 회원 정보 보관
		session.setAttribute(SessionConst.LOGIN_USERS, loginUsers);
		session.setAttribute("userInfo", loginUsers.getNickname());
		return "createTree";
	}
	
	// 회원가입
	@PostMapping("/users/new")
	public String create(@Valid UsersForm form, BindingResult result) {
		
		
		// error 발생 시
		if(result.hasErrors()) {
			return "index";
		}
		
		// 정상 로직, service
		Users users = new Users();
		users.setU_Id(form.getU_Id());
		users.setPassword(form.getPassword());
		users.setNickname(form.getNickname());
		users.setCreateDate(LocalDateTime.now());
		
		usersService.join(users);
		
		return "redirect:/";
	}
	
	@PostMapping("/users/logout")
	public String logout(HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		if( session != null ) {
			session.invalidate();
		}
		return "redirect:/";
	}
	
	
}
