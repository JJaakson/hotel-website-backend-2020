package ee.taltech.website.service;

import ee.taltech.website.dto.LoginDto;
import ee.taltech.website.exception.UserException;
import ee.taltech.website.security.jwt.JwtUtil;
import ee.taltech.website.security.LoginResponse;
import ee.taltech.website.security.MyUser;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
@AllArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginDto loginDto) {
        if (isBlank(loginDto.getUsername())) {
            throw new UserException("missing username");
        }
        if (isBlank(loginDto.getPassword())) {
            throw new UserException("missing password");
        }
        //this will validate that the password is correct (without us validating it explicitly)
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        MyUser myUser = (MyUser) authenticate.getPrincipal(); //it is UserDetails and in our case it is MyUser
        String token = jwtUtil.generateToken(myUser);
        return LoginResponse.builder()
                .username(myUser.getUsername())
                .token(token)
                .role(myUser.getDbRole())
                .build();

    }
}
