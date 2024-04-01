package org.test.usedtrade.user.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.test.usedtrade.user.entity.UserInfoEntity;

import java.io.Serializable;

/**
 * DTO for {@link UserInfoEntity}
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto implements Serializable {
    Long id;
    String email;
    String password;
    String nickName;



    public static UserResponseDto fromReponseDtoUser(UserInfoEntity userInfoEntity){
        return UserResponseDto.builder()
                .id(userInfoEntity.getId())
                .email(userInfoEntity.getEmail())
                .password(userInfoEntity.getPassword())
                .nickName(userInfoEntity.getNickName())
                .build();
    }
}