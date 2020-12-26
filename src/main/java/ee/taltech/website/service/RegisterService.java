package ee.taltech.website.service;

import ee.taltech.website.dto.RegisterDto;
import ee.taltech.website.exception.UserException;
import ee.taltech.website.model.User;
import ee.taltech.website.repository.UsersRepository;
import ee.taltech.website.security.DbRole;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
@AllArgsConstructor
public class RegisterService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(RegisterDto registerDto) {
        if (isBlank(registerDto.getUsername())) {
            throw new UserException("missing username");
        }
        if (isBlank(registerDto.getPassword())) {
            throw new UserException("missing password");
        }
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(DbRole.USER);
        usersRepository.save(user);
    }
}
