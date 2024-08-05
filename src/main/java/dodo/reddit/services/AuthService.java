package dodo.reddit.services;

import dodo.reddit.ReqRes.LoginRequest;
import dodo.reddit.ReqRes.AuthResponse;
import dodo.reddit.ReqRes.RefreshTokenRequest;
import dodo.reddit.ReqRes.SignRequest;
import dodo.reddit.models.User.User;
import dodo.reddit.models.User.UserRoleEnum;
import dodo.reddit.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Transactional
@Scope("singleton")
@Slf4j
public class AuthService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Transactional
    public boolean singUp(SignRequest request){
        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(encoder.encode(request.password()))
                .created(Instant.now())
                .enabled(true)
                .role(UserRoleEnum.USER)
                .build();

        if(userRepository.findUserByUsernameOrEmail(user.getUsername(), user.getEmail())
                .isPresent()){
            return false;
        }

        userRepository.save(user);
        return true;
    }

    public AuthResponse login(LoginRequest request){
        Authentication authentication = new UsernamePasswordAuthenticationToken(request.username(),request.password());
        authentication = authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return  AuthResponse.builder()
                .jwtToken(tokenService.generateJwtToken(authentication))
                .refreshToken(tokenService.generateRefreshToken().getToken())
                .user(request.username())
                .expires(tokenService.getExpirationInDays())
                .build();
    }

    public AuthResponse refreshToken(RefreshTokenRequest request){
        tokenService.validateRefreshToken(request.refreshToken());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return AuthResponse.builder()
                .jwtToken(tokenService.generateJwtToken(authentication))
                .refreshToken(request.refreshToken())
                .user(request.username())
                .expires(tokenService.getExpirationInDays())
                .build();
    }


    public User getCurrentUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findUserByUsernameOrEmail(username,null).orElseThrow();
    }
}
