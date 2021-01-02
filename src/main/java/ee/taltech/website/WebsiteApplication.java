package ee.taltech.website;

import ee.taltech.website.model.User;
import ee.taltech.website.repository.UsersRepository;
import ee.taltech.website.security.DbRole;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@AllArgsConstructor
@SpringBootApplication
public class WebsiteApplication {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository repository;

    @PostConstruct
    public void initUsers() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRole(DbRole.ADMIN);
        repository.save(admin);
    }

    public static void main(String[] args) {
        SpringApplication.run(WebsiteApplication.class, args);
    }
}
