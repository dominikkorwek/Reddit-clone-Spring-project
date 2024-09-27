package dodo.reddit.controllers;

import dodo.reddit.ReqRes.AuthResponse;
import dodo.reddit.ReqRes.LoginRequest;
import dodo.reddit.ReqRes.RefreshTokenRequest;
import dodo.reddit.ReqRes.SignRequest;
import dodo.reddit.services.AuthService;
import dodo.reddit.services.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Scope("singleton")
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final TokenService tokenService;

    @PostMapping("/signUp")
    public ResponseEntity<String> SignUp(@RequestBody SignRequest request){
        authService.singUp(request);
        return new ResponseEntity<>("User Registration Succesfull", HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh/token")
    public AuthResponse refreshTokens(@Validated @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Validated @RequestBody RefreshTokenRequest refreshTokenRequest) {
        tokenService.deleteRefreshToken(refreshTokenRequest.refreshToken());
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Succesfully logout");
    }
}
