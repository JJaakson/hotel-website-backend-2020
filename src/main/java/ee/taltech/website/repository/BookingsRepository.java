package ee.taltech.website.repository;

import ee.taltech.website.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingsRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByUsername(String username);
}
