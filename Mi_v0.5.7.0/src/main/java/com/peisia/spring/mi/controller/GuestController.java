package com.peisia.spring.mi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.peisia.dto.GuestDto;
import com.peisia.spring.mi.service.GuestService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j // 로그 출력을 위한 Log4j 라이브러리 사용
@RequestMapping // URL경로에 "/guest/"로 시작하는 요청처리
@AllArgsConstructor // 롬복(lombok)을 이용하여 생성자 자동생성
@Controller // spring MVC에서 컨트롤러로 인식되는 클래스
public class GuestController {

	// GuestService 인터페이스의 구현체를 주입받아 서비스 계층의 메서드를 호출
	private GuestService service;

	@GetMapping("/guest/getList") // "/guest/getList" 요청을 처리 (GET방식)
	public void getList(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage, Model model) {
		// currentPage 값을 요청 파라미터로 받으며, 기본값은 1로 설정
		model = service.getList(model, currentPage); // 현재 페이지 번호를 사용하여 리스트 조회
	}

	@GetMapping("/guest/listSearch") // Get요청을 처리하는 컨트롤러 메소드로 "guest/listSearch" 경로에 대한 요청을 처리함
	public String listSearch(Model model, @RequestParam(value = "page", defaultValue = "1") int page,
			// 요청 파라미터 'page'를 받아오며, 기본값은 1로 설정
			@RequestParam(value = "search", defaultValue = "") String search) {
		// 요청 파라미터 'search'를 받아오며, 기본값은 빈 문자열로 설정
		// "guest/listSearch" 뷰를 반환, 즉 해당이름의 view페이지로 이동
		return "guest/listSearch";
	}

	@GetMapping("/guest/listSearchProc") // GET요청을 처리하는 컨트롤러 메서드로, "/guest/listSearch" 경로에 대한 요청을 처리함
	public String listSearchProc(Model model, @RequestParam(value = "page", defaultValue = "1") int page,
			// 요청 파라미터 'page'로 받아오며 기본값을 1로 설정
			@RequestParam(value = "search", defaultValue = "") String search) {
		// 요청 파라미터 'search'를 받아오며, 기본값을 빈 문자열로 설정
		log.info("-----------검색처리 컨트롤러에 진입했음 -------------" + search);
		// 서비스 메소드를 호출하여 검색어(search)와 페이지(page)에 맞는 데이터를 Model 객체에 추가
		service.listSearch(model, page, search); // search 파라미터가 추가된 상태로 서비스 메소드 호출
		// "guest/listSearch" 뷰를 반환, 즉 해당 뷰페이지로 이동
		return "guest/searchResult";
	}

	@GetMapping("/guest/read") // "/guest/read" 및 "/guest/modify"요청을 처리 (GET방식)
	// bno라는 이름의 요청 파라미터로 글번호 (Long 타입)을 받음
	public void read(@RequestParam("bno") Long bno, Model model, @RequestParam("btext") String btext) {
		log.info("컨트롤러 ================== 번호 ===============" + bno); // 로그로 글 번호 출력
		log.info("컨트롤러 ================== 내용 ===============" + btext); // 로그로 글 번호 출력
		model.addAttribute("bno", bno); // 서비스에서 글을 읽어와 모델에 추가
		model.addAttribute("btext", btext); // 서비스에서 글을 읽어와 모델에 추가
	}

	@GetMapping("/guest/modify") // "/guest/read" 및 "/guest/modify"요청을 처리 (GET방식)
	// bno라는 이름의 요청 파라미터로 글번호 (Long 타입)을 받음
	public void modify(@RequestParam("bno") Long bno, Model model) {
		log.info("컨트롤러 ================== 번호 ===============" + bno); // 로그로 글 번호 출력
		// 서비스 계층의 read 메서드를 호출하여, 해당 글 번호에 대한 상세 정보를 조회
		GuestDto x = service.read(bno);

		// 조회된 글 정보를 "x"라는 이름으로 Model에 추가하여 뷰에서 접근 가능하게 함
		model.addAttribute("x", x);
	}

	@GetMapping("/guest/del") // "/guest/del" 요청을 처리 (GET 방식)
	// bno라는 이름의 요청 파라미터로 삭제할 글 번호를 받음
	public String del(@RequestParam("bno") Long bno) {
		log.info("컨트롤러 ---------------- 번호 ----------" + bno); // 로그로 글 번호 출력
		service.del(bno); // 서비스에서 글 삭제 정리
		return "redirect:/guest/getList"; // 삭제 후 리스트 페이지로 리다이렉트
	}

	@PostMapping("/guest/write") // /guest/write 요청처리(post방식) - 글 작성처리
	// GuestDto 객체를 받아서 글 작성처리
	public String write(GuestDto dto) {
		service.write(dto); // 서비스에서 글 작성처리
		return "redirect:/guest/getList"; // 작성 후 리스트 페이지로 이동
	}

	/**
	 * GET 방식으로 "/guest/write" 요청을 처리하는 메서드
	 * 
	 * 단순히 "/guest/write" URL로 접근할 때 호출되는 메서드로, 특별한 로직 없이 글 작성 페이지로 이동합니다.
	 */
	@GetMapping("/guest/write")
	public void write() {
		// 별도의 로직 없이 글 작성 페이지로 이동
	}

	/**
	 * GET 방식으로 "/guest/modifyProc" 요청을 처리하는 메서드
	 * 
	 * @param dto - 수정할 글 정보를 포함하는 GuestDto 객체
	 * @return String - 수정 완료 후 "/guest/getList" 페이지로 리다이렉트
	 */
	@GetMapping("/guest/modifyProc")
	public String modifyProc(GuestDto dto) {
		// 서비스 계층의 modify 메서드를 호출하여 전달받은 dto의 정보를 수정
		service.modify(dto);

		// 수정 작업이 완료되면 "/guest/getList" 페이지로 리다이렉트하여 목록 페이지로 이동
		return "redirect:/guest/getList";
	}

}
