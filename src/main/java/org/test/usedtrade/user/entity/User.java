package org.test.usedtrade.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.test.usedtrade.user.dto.req.UserRequestDto;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "user_info")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "nickname")
    private String nickName;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles_connetor", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<UserRole> roles = new HashSet<>();


    public static User fromRequestDto(UserRequestDto userRequestDto){
        return User.builder()
                .email(userRequestDto.getEmail())
                .nickName(userRequestDto.getNickName())
                .password(userRequestDto.getPassword())
                .build();
    }
}
