package ee.taltech.website.a_theory.question6.sheep.repository;

import ee.taltech.website.a_theory.question6.sheep.model.Sheep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SheepRepository extends JpaRepository<Sheep, Long> {
}
