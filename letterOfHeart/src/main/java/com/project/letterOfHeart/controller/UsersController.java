package com.project.letterOfHeart.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.letterOfHeart.domain.Tree;
import com.project.letterOfHeart.domain.Users;
import com.project.letterOfHeart.dto.LoginForm;
import com.project.letterOfHeart.dto.UsersForm;
import com.project.letterOfHeart.jwt.JwtAuthenticationFilter;
import com.project.letterOfHeart.jwt.TokenInfo;
import com.project.letterOfHeart.service.MessageService;
import com.project.letterOfHeart.service.TreeService;
import com.project.letterOfHeart.service.UsersService;
import com.project.letterOfHeart.service.UsersToeknService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RestController
@RequiredArgsConstructor
public class UsersController {

	private final UsersService usersService;
	private final UsersToeknService usersToeknService;
	private final TreeService treeService;
	private final MessageService messageService;
	private ResponseCookie cookie;
	private final JwtAuthenticationFilter filter;

	@GetMapping("/")
	public ModelAndView loginForm(@ModelAttribute("loginForm") LoginForm loginForm,
			@ModelAttribute("usersForm") UsersForm usersForm) {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}

	@GetMapping("/users/login")
	public ModelAndView loginForm(LoginForm loginForm, Model model) {
		ModelAndView mv = new ModelAndView("myTree");
		model.addAttribute("member", loginForm);
		return mv;
	}

	@PostMapping("/users/login")
	public ModelAndView login(@ModelAttribute LoginForm form, Model model, RedirectAttributes redirectAttributes,  Errors errors,
			HttpServletResponse response) {

		Users loginUsers = usersService.login(form.getAccoutid(), form.getPassword());

		redirectAttributes.addAttribute("id", loginUsers.getId());
		ModelAndView mv = new ModelAndView("redirect:/myTree/{id}");

		loginToken(form, response);

		System.out.println(loginToken(form, response));

		return mv;
	}

	public TokenInfo loginToken(@RequestBody LoginForm loginForm, HttpServletResponse response) {

		String accoutid = loginForm.getAccoutid();
		String password = loginForm.getPassword();
		TokenInfo tokenInfo = usersToeknService.login(accoutid, password);

		// refrech token 쿠키에 저장
		cookie = ResponseCookie.from("refreshToken", tokenInfo.getRefreshToken()).maxAge(7 * 24 * 60 * 60).path("/")
				.secure(true).sameSite("None").httpOnly(true).build();
		response.setHeader("Set-Cookie", cookie.toString());

		cookie = ResponseCookie.from("accessToken", tokenInfo.getAccessToken()).maxAge(20 * 60).path("/").secure(true)
				.sameSite("None").httpOnly(true).build();
		response.setHeader("Set-Cookie", cookie.toString());

		System.out.println("memberLoginRequestDto : " + loginForm.toString());
		System.out.println("쿠키 : " + cookie.toString());
		System.out.println("access token:" + tokenInfo.getAccessToken());
		System.out.println("refresh token:" + tokenInfo.getRefreshToken());

		return tokenInfo;
	}

	@PostMapping("/users/logout")
	public ModelAndView logout(HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("redirect:/");

		cookie = ResponseCookie.from("accessToken", null).maxAge(0).path("/").secure(true).sameSite("None")
				.httpOnly(true).build();
		response.setHeader("Set-Cookie", cookie.toString());

		return mav;
	}

	@GetMapping("/myTree/{id}")
	public ModelAndView getId(Model model, @PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("myTree");
		model.addAttribute("userForm", new UsersForm());
		model.addAttribute("id", id);
		// 해당 id의 메세지 리스트
		model.addAttribute("messages", messageService.messageList(id));
		// 메세지 개수
		model.addAttribute("msgCnt", messageService.findAllById(id));
		return mv;
	}

	@GetMapping("/designTree")
	// public ModelAndView designTree(HttpServletRequest request,
	// HttpServletResponse response, FilterChain chain) {
	public ModelAndView designTree(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
		ModelAndView mv = new ModelAndView("designTree");
		try {
			filter.doFilter(request, response, chain);
			System.out.println(" out? ");
		} catch (IllegalStateException e) {
			System.out.println(" ILLEGAL  " + e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IOE : " + e);

			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			System.out.println("SERE" + e);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(" AllE : " + e);
			e.printStackTrace();
		}
		return mv;
	}

	// 회원가입
	@PostMapping("/users/new")
	public ModelAndView create(LoginForm loginForm, @Valid UsersForm form, Errors errors, Model model) {

		ModelAndView mv = new ModelAndView("redirect:/");

		/* post요청시 넘어온 user 입력값에서 Validation에 걸리는 경우 */
		if (errors.hasErrors()) {
			/* 회원가입 실패시 입력 데이터 유지 */
			model.addAttribute("usersForm", form);
			/* 회원가입 실패시 message 값들을 모델에 매핑해서 View로 전달 */
			Map<String, String> validateResult = usersService.validateHandler(errors);
			// map.keySet() -> 모든 key값을 갖고온다.
			// 그 갖고온 키로 반복문을 통해 키와 에러 메세지로 매핑
			for (String key : validateResult.keySet()) {
				// ex) model.addAtrribute("valid_id", "아이디는 필수 입력사항 입니다.")
				model.addAttribute(key, validateResult.get(key));
			}
			model.addAttribute("msg", "회원가입 실패, 회원가입으로 돌아가주세요!!!");

			return mv = new ModelAndView("index");
		}

		// 정상 로직, service
		Users users = new Users();
		users.setAccoutid(form.getAccoutid());
		users.setPassword(form.getPassword());
		users.setNickname(form.getNickname());
		users.setCreateDate(LocalDateTime.now());
		// users.setRoles();

		// tree
		Tree tree = new Tree();
		users.addTree(tree);
		tree.setId(users.getId());
		tree.setMessageCnt(0);

		// 회원가입
		usersService.join(users);
		// 트리 생성
		treeService.save(tree);

		return mv;
	}
}
