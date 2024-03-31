package org.test.usedtrade.user.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link org.test.userservice.user.entity.User}
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto implements Serializable {
    String email;
    String password;
    String nickName;
//    private Set<UserRole> roles;
}