package org.test.usedtrade.user.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.test.usedtrade.user.entity.User;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto implements Serializable {
    Long userId;
    String email;
    String password;
    String nickName;


    public static UserResponseDto fromReponseDtoUser(User user){
        return UserResponseDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .password(user.getPassword())
                .nickName(user.getNickName())
                .build();
    }
}