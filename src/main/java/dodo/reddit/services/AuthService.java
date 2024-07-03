package dodo.reddit.services;

import dodo.reddit.dto.UserRequest;
import dodo.reddit.models.User.User;
import dodo.reddit.models.User.UserRoleEnum;
import dodo.reddit.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
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

    @Transactional
    public boolean singUp(UserRequest request){
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
}
