package org.test.usedtrade.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.test.usedtrade.user.dto.JwtResponseDTO;
import org.test.usedtrade.user.dto.req.RequestLoginUserDto;
import org.test.usedtrade.user.dto.req.UserRequestDto;
import org.test.usedtrade.user.dto.res.UserResponseDto;
import org.test.usedtrade.user.jwt.JwtService;
import org.test.usedtrade.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;
    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }
    @PostMapping("/save")
    public ResponseEntity saveUser(@RequestBody UserRequestDto userRequest) {
        try {
            UserResponseDto userResponse = userService.saveUser(userRequest);
            return ResponseEntity.ok(userResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/testtest")
    public ResponseEntity testtest(){
        return ResponseEntity.ok("oksucce");
    }
    @PostMapping("/login")
    public JwtResponseDTO AuthenticateAndGetToken(@RequestBody RequestLoginUserDto authRequestDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDTO.getEmail(),
                        authRequestDTO.getPassword()
                )
        );
        if(authentication.isAuthenticated()){
//            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
            return JwtResponseDTO.builder()
                    .accessToken(jwtService.GenerateToken(authRequestDTO.getEmail())).build();
//                    .token(refreshToken.getToken()).build();

        } else {
            throw new UsernameNotFoundException("user not found");
        }

    }
}
