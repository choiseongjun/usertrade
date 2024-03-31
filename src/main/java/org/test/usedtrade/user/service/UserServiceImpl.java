package org.test.usedtrade.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.test.usedtrade.user.dto.req.RequestLoginUserDto;
import org.test.usedtrade.user.dto.req.UserRequestDto;
import org.test.usedtrade.user.dto.res.UserResponseDto;
import org.test.usedtrade.user.entity.User;
import org.test.usedtrade.user.entity.UserRole;
import org.test.usedtrade.user.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    @Override
    public RequestLoginUserDto createUser(RequestLoginUserDto requestLoginUserDto) {
        return null;
    }

    @Override
    public UserResponseDto getUserByUserId(String userId) {
        return null;
    }

    @Override
    public UserResponseDto getUserDetailsByEmail(String email) {
        User user = userRepository.findByEmail(email);

        if(user==null){
            throw new UsernameNotFoundException(email);
        }
        UserResponseDto userDto = new UserResponseDto();
        return userDto.fromReponseDtoUser(user);
    }

    @Override
    public UserResponseDto saveUser(UserRequestDto userRequest) {
//        User user = User.fromRequestDto(userRequest);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        UserRole userRole = new UserRole();
        userRole.setId(1);
        userRole.setName("role_user");

        Set<UserRole> roleSet = new HashSet<>();
        roleSet.add(userRole);


        User user = new User();
        String encodedPassword = encoder.encode(userRequest.getPassword());
        user.setEmail(userRequest.getEmail());
        user.setPassword(encodedPassword);

        user.setRoles(roleSet);
        User rtnUser =  userRepository.save(user);
        return UserResponseDto.fromReponseDtoUser(rtnUser);
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email);
//        if(user == null){
//            throw new UsernameNotFoundException(email);
//        }
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                true,
//                true,
//                true,
//                true,
//                new ArrayList<>()
//        );
//    }
}
