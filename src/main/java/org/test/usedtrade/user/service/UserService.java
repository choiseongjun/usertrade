package org.test.usedtrade.user.service;

import org.test.usedtrade.user.dto.req.RequestLoginUserDto;
import org.test.usedtrade.user.dto.req.UserRequestDto;
import org.test.usedtrade.user.dto.res.UserResponseDto;

public interface UserService  {
    RequestLoginUserDto createUser(RequestLoginUserDto requestLoginUserDto);

    UserResponseDto getUserByUserId(String userId);

    UserResponseDto getUserDetailsByEmail(String email);

    UserResponseDto saveUser(UserRequestDto userRequest);
}
