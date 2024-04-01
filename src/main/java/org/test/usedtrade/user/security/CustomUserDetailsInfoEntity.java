package org.test.usedtrade.user.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.test.usedtrade.user.entity.UserInfoEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetailsInfoEntity extends UserInfoEntity implements UserDetails {
    private String email;
    private String password;
    Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetailsInfoEntity(UserInfoEntity byUsername) {
        this.email = byUsername.getEmail();
        this.password= byUsername.getPassword();
        List<GrantedAuthority> auths = new ArrayList<>();

//        for(UserRole role : byUsername.getRoles()){
//
//            auths.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
//        }
        this.authorities = auths;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
