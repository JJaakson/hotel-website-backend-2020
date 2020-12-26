package ee.taltech.website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebsiteApplication {

//    @Autowired
//    private UsersRepository repository;
//
//    @PostConstruct
//    public void initUsers() {
//        repository.save(new User());
//    }

    public static void main(String[] args) {
        SpringApplication.run(WebsiteApplication.class, args);
    }
}
