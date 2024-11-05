package com.peisia.spring.mi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peisia.dto.UserDto;
import com.peisia.spring.mi.mapper.UserMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class UserServiceImpl implements UserService {
	@Setter(onMethod_ = @Autowired)
	// Lombok의 @Setter를 통해 setter 메서드를 자동 생성하고,
	// @Autowired로 의존성 주입을 활성화합니다.
	UserMapper mapper;

	/**
	 * 회원가입을 처리하는 메서드
	 *
	 * @param user - 회원가입할 사용자의 정보를 포함하는 UserDto 객체
	 */
	public void register(UserDto user) {
		// 로그 출력: 회원가입 요청을 확인하기 위해 사용자의 이름을 로그로 출력
		log.info("impl 테스트입니다=========" + user.getUserName());

		// UserMapper의 register 메서드를 호출하여 회원 정보를 데이터베이스에 저장
		mapper.register(user);
	}

	/**
	 * 로그인 기능을 처리하는 메서드
	 *
	 * @param user - 로그인할 사용자의 정보를 포함하는 UserDto 객체
	 * @return String - 로그인한 사용자의 ID를 반환
	 */
	@Override
	public String login(UserDto user) {
		// 로그 출력: 로그인 요청을 확인하기 위해 사용자의 ID와 비밀번호를 로그로 출력
		log.info("impl login text =======" + user.getId() + user.getPw());

		// UserMapper의 login 메서드를 호출하여 해당 사용자의 정보를 데이터베이스에서 조회
		UserDto x = mapper.login(user);

		// 조회된 사용자의 ID와 비밀번호를 콘솔에 출력
		System.out.println(x.getId() + x.getPw());

		// 로그인한 사용자의 ID를 변수에 저장
		String id = user.getId();

		// 로그인한 사용자의 ID를 반환
		return id;
	}
}
