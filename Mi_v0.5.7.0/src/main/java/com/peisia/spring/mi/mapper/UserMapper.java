package com.peisia.spring.mi.mapper;

import com.peisia.dto.UserDto;

public interface UserMapper {

	public void register(UserDto user);

	// userDto로 부터 가져와야하니 userDto를 앞뒤로쓴다.
	public UserDto login(UserDto user);
}
