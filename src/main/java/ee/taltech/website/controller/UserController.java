package ee.taltech.website.controller;


import ee.taltech.website.dto.LoginDto;
import ee.taltech.website.dto.RegisterDto;
import ee.taltech.website.security.LoginResponse;
import ee.taltech.website.security.UserSessionHolder;
import ee.taltech.website.service.LoginService;
import ee.taltech.website.service.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("users")
@RestController
@AllArgsConstructor
public class UserController {

    private final RegisterService registerService;
    private final LoginService loginService;

    @PostMapping("register")
    public ResponseEntity<Void> register(@RequestBody RegisterDto registerDto){
        registerService.saveUser(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("login")
    public LoginResponse login(@RequestBody LoginDto loginDto){
        return loginService.login(loginDto);
    }

    @GetMapping("me")
    public Object getMe() {
        return UserSessionHolder.getLoggedInUser();
    }
}

