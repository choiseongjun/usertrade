package org.test.usedtrade.user.security;//package org.test.userservice.user.security;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.core.env.Environment;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.test.userservice.user.dto.req.RequestLoginUserDto;
//import org.test.userservice.user.dto.res.UserResponseDto;
//import org.test.userservice.user.entity.User;
//import org.test.userservice.user.service.UserService;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    private  UserService userService;
//    private  Environment env;
//
//
//
//    public AuthenticationFilter(AuthenticationManager authenticationManager,
//                                UserService userService,
//                                Environment env) {
//        super.setAuthenticationManager(authenticationManager);
//        this.userService = userService;
//        this.env = env;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request,
//                                                HttpServletResponse response) throws AuthenticationException {
////        return super.attemptAuthentication(request, response);
//        try {
//            RequestLoginUserDto user = new ObjectMapper().readValue(request.getInputStream(),RequestLoginUserDto.class);
//            return getAuthenticationManager().authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            user.getEmail(),
//                            user.getPassword(),
//                            new ArrayList<>()
//                    )
//            );
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request,
//                                            HttpServletResponse response,
//                                            FilterChain chain,
//                                            Authentication authResult) throws IOException, ServletException {
//        String email = ((User)authResult.getPrincipal()).getEmail();
//        UserResponseDto user = userService.getUserDetailsByEmail(email);
//    }
//}
