package ee.taltech.website.repository;

import ee.taltech.website.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<User, Long> {

    List<User> findAllByUsername(String username);
}
