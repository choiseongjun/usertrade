package org.test.usedtrade.user.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link org.test.userservice.user.entity.User}
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestLoginUserDto implements Serializable {
//    @NotNull(message = "이메일은 null이면 안됩니다.")
//    @Size(min=1,message = "최소 1자이상 입력해주세요.")
//    @Email
    String email;
//    @NotNull(message = "비밀번호는 null이면 안됩니다.")
//    @Size(min=1,message = "최소 1자이상 입력해주세요.")
    String password;
}