package ee.taltech.website.repository;

import ee.taltech.website.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Room, Integer> {
}
