package com.peisia.dto;

import lombok.Data;

@Data
public class UserDto {

	// 로그인, 로그아웃, 회원가입은 같은 UserDto에서 모두 처리한다. 따로 만들어줘도되지만 그점이 경제적이다.
	private String id;
	private String userName;
	private String pw;
	private String email;

}
