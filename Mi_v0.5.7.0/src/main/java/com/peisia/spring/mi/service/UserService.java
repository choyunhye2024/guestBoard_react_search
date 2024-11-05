package com.peisia.spring.mi.service;

import com.peisia.dto.UserDto;

public interface UserService {

	// 회원가입
	public void register(UserDto user);

	// 로그인(해당 생성자에서 return을 쓰므로 String으로 받아줘야한다.)
	public String login(UserDto user);
}
