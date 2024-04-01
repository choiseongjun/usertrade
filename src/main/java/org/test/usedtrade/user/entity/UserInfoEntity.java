package org.test.usedtrade.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.test.usedtrade.user.dto.req.UserRequestDto;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "user_info")
public class UserInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "nickname")
    private String nickName;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles_connetor", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<UserRole> roles = new HashSet<>();


    public static UserInfoEntity fromRequestDto(UserRequestDto userRequestDto){
        return UserInfoEntity.builder()
                .email(userRequestDto.getEmail())
                .nickName(userRequestDto.getNickName())
                .password(userRequestDto.getPassword())
                .build();
    }
}
