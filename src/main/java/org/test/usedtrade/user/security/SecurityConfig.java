package org.test.usedtrade.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.test.usedtrade.user.jwt.JwtAuthFilter;
import org.test.usedtrade.user.service.UserDetailsServiceImpl;

import java.util.function.Supplier;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    JwtAuthFilter jwtAuthFilter;
    public static final String ALLOWED_IP_ADDRESS = "0.0.0.0";
    public static final String SUBNET = "/32";
    public static final IpAddressMatcher ALLOWED_IP_ADDRESS_MATCHER = new IpAddressMatcher(ALLOWED_IP_ADDRESS + SUBNET);
    public static final String IP_CHECK_PATH_PREFIX = "/user-service";

    public static final String IP_CHECK_PATH_PATTERN = IP_CHECK_PATH_PREFIX + "/**";

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }


    private static final String[] WHITE_LIST = {
            "/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests.anyRequest().permitAll()
                );

        return http.build();
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(authorizeRequest ->
//                        authorizeRequest.anyRequest().permitAll()
////                                .requestMatchers(IP_CHECK_PATH_PATTERN).access(this::hasIpAddress)
////                                .requestMatchers(
////                                        AntPathRequestMatcher.antMatcher("/**")
////                                ).authenticated()
////                                .requestMatchers(new IpAddressMatcher("127.0.0.1")).permitAll()
////                                .requestMatchers(
////                                        AntPathRequestMatcher.antMatcher("/**")
////                                ).permitAll()
////                                .requestMatchers("/**").permitAll()
//                )
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//        ;
//
////                .headers(
////                        headersConfigurer ->
////                                headersConfigurer
////                                        .frameOptions(
////                                                HeadersConfigurer.FrameOptionsConfig::sameOrigin
////                                        )
////                )
//                ;
//
//        return http.build();
    }
    private AuthorizationDecision hasIpAddress(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        return new AuthorizationDecision(ALLOWED_IP_ADDRESS_MATCHER.matches(object.getRequest()));
    }
//    @Bean
//    protected SecurityFilterChain config(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(authorize -> {
//            try {
//                authorize.anyRequest().permitAll();
////                        .requestMatchers(WHITE_LIST).permitAll();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        })
//        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
////                .addFilter(getAuthenticationFilter());
//
//        return http.build();
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}