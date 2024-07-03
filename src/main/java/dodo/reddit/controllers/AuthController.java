package dodo.reddit.controllers;

import dodo.reddit.dto.UserRequest;
import dodo.reddit.services.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
@Scope("singleton")
@Slf4j
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<String> SignUp(@RequestBody UserRequest request){
        boolean status = authService.singUp(request);
        if (status)
            return new ResponseEntity<>("User Registration Succesfull", HttpStatus.OK);

        return new ResponseEntity<>("User Registration Failed", HttpStatus.BAD_REQUEST);
    }

}
