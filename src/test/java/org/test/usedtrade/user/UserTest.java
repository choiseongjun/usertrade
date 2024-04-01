package org.test.usedtrade.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.test.usedtrade.user.dto.req.UserRequestDto;
import org.test.usedtrade.user.dto.res.UserResponseDto;
import org.test.usedtrade.user.entity.UserInfoEntity;
import org.test.usedtrade.user.repository.UserRepository;
import org.test.usedtrade.user.service.UserService;


import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @BeforeEach
    void init() {
        userRepository.deleteAll();
    }
    @DisplayName("유저 생성내용과 return값이 다르다")
    @Test
    void userSaveWithReturn() throws Exception{
        //given
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .email("asd1@naver.com")
                .password("123")
                .nickName("testnickname")
                .build();
        //when
        UserResponseDto rtnUser = userService.saveUser(userRequestDto);
        //then
        assertFalse(rtnUser.getEmail()=="asd2");
    }
    @DisplayName("유저를 생성한다")
    @Test
    void userSave() throws Exception{
        //given
        UserInfoEntity user = new UserInfoEntity();
        user.setEmail("asd1@naver.com");
        user.setPassword("123");
        user.setNickName("testnickname");

        UserRequestDto userRequestDto = UserRequestDto.builder()
                .email("asd1@naver.com")
                .password("123")
                .nickName("testnickname")
                .build();
        //when
        UserResponseDto rtnUser = userService.saveUser(userRequestDto);
        //then
        assertThat(rtnUser.getEmail()).isEqualTo(user.getEmail());
    }
    @DisplayName("이메일이 고유한지 테스트한다")
    @Test
    @Transactional // 트랜잭션 적용
    public void testDuplicateEmailException() {
        // Given
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .email("asd1")
                .password("123")
                .nickName("testnickname")
                .build();
        UserRequestDto userRequestDto2 = UserRequestDto.builder()
                .email("asd1")
                .password("123")
                .nickName("testnickname")
                .build();

        // When
        userService.saveUser(userRequestDto); // 첫 번째 유저 저장

        // Then
        // 중복 이메일이 예외를 발생시키는지 테스트
        assertThatThrownBy(() -> userService.saveUser(userRequestDto2))
                .isEqualTo(new IllegalStateException("asd1"));
    }
}
