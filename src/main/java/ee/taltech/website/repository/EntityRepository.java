package ee.taltech.website.repository;

import ee.taltech.website.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepository extends JpaRepository<Item, Integer> {
}
