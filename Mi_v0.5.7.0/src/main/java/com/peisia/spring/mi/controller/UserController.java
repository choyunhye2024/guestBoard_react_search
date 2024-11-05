package com.peisia.spring.mi.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peisia.dto.UserDto;
import com.peisia.spring.mi.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j // Log4j를 통해 로깅 기능을 추가하여, 디버깅 및 운영 중 로그 기록을 남길 수 있습니다.
@Controller // Spring MVC의 Controller 클래스임을 나타내며, 클라이언트 요청을 처리하고 응답을 반환합니다.
@RequestMapping("/user/*") // "/user" 경로로 시작하는 모든 요청을 이 컨트롤러에서 처리합니다.
@AllArgsConstructor // Lombok을 사용하여 모든 필드에 대한 생성자를 자동으로 생성합니다.

public class UserController {

	// UserService의 의존성을 주입받기 위한 필드 선언
	private UserService userService;

	/**
	 * GET 방식으로 "/register" 요청을 처리하는 메서드
	 * 
	 * 단순히 "/register" URL로 접근할 때 호출되며, 회원가입 페이지로 이동합니다.
	 */
	@GetMapping("/register")
	public void register() {
		// 별도의 로직 없이 회원가입 페이지로 이동
	}

	/**
	 * GET 방식으로 "/registerProc" 요청을 처리하여 회원가입을 수행하는 메서드
	 * 
	 * @param user - 회원가입할 사용자의 정보를 포함하는 UserDto 객체
	 * @return String - 회원가입이 완료된 후 이동할 페이지 경로 ("/home" 페이지로 이동)
	 */
	@GetMapping("/registerProc")
	public String registerProc(UserDto user) {
		// 로그 출력: 등록 작업을 확인하기 위해 사용자의 이름을 로그로 출력
		log.info("등록입니다=====================" + user.getUserName());

		// 서비스 계층의 register 메서드를 호출하여 전달받은 user 객체의 정보를 등록 처리
		userService.register(user);

		// 회원가입이 완료되면 "/home" 페이지로 이동
		return "/home";
	}

	/**
	 * GET 방식으로 "/login" 요청을 처리하는 메서드
	 * 
	 * 단순히 "/login" URL로 접근할 때 호출되며, 로그인 페이지로 이동합니다.
	 */
	@GetMapping("/login")
	public void login() {
		// 별도의 로직 없이 로그인 페이지로 이동
	}

	/**
	 * GET 방식으로 "/loginProc" 요청을 처리하여 로그인 절차를 수행하는 메서드
	 * 
	 * @param user    - 로그인 정보를 포함하는 UserDto 객체
	 * @param session - 사용자 세션을 관리하기 위한 HttpSession 객체
	 * @return String - 로그인 완료 후 이동할 페이지 경로 ("/home" 페이지로 이동)
	 */
	@GetMapping("/loginProc")
	public String loginProc(UserDto user, HttpSession session) {
		// 로그인 요청 확인 로그
		log.info("로그인입니다=======================");

		// userService를 통해 로그인 ID를 확인
		String id = userService.login(user);

		// 조회된 로그인 ID를 로그에 출력
		log.info("id:" + id);

		// 세션에 로그인 ID를 저장하여 로그인 상태 유지
		session.setAttribute("id", id);

		// 세션에 저장된 ID를 로그로 확인
		String se = (String) session.getAttribute("id");
		log.info(se);

		// 로그인 완료 후 홈 페이지로 이동
		return "/home";
	}

	/**
	 * GET 방식으로 "/memberContent" 요청을 처리하여 회원 전용 페이지에 접근하는 메서드
	 * 
	 * @param session - 사용자 세션을 관리하기 위한 HttpSession 객체
	 * @return String - 회원 여부에 따라 접근할 페이지 경로
	 */
	@GetMapping("/memberContent")
	public String memberContent(HttpSession session) {
		// 세션에서 로그인 ID를 확인
		String se = (String) session.getAttribute("id");

		if (se == null) { // 비회원인 경우
			log.info("============비회원입니다.==============");
			return "/home"; // 홈 페이지로 리다이렉트
		} else { // 회원인 경우
			log.info("============회원입니다.==============" + se);
			return "/user/memberContent"; // 회원 전용 페이지로 이동
		}
	}

	/**
	 * GET 방식으로 "/logout" 요청을 처리하여 로그아웃을 수행하는 메서드
	 * 
	 * @param session - 사용자 세션을 관리하기 위한 HttpSession 객체
	 * @return String - 로그아웃 후 이동할 페이지 경로 ("/home" 페이지로 이동)
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 세션 무효화하여 로그아웃 처리
		session.invalidate();

		// 로그아웃 후 홈 페이지로 이동
		return "/home";
	}

}