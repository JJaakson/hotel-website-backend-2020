package ee.taltech.website.a_theory.question6.sheep.service;

import ee.taltech.website.a_theory.question6.sheep.model.Sheep;
import ee.taltech.website.a_theory.question6.sheep.exception.InvalidSheepException;
import ee.taltech.website.a_theory.question6.sheep.exception.SheepNotFoundException;
import ee.taltech.website.a_theory.question6.sheep.repository.SheepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SheepService {

    @Autowired
    private SheepRepository sheepRepository;

    public Sheep save(Sheep sheep) {
        if (sheep.getAge() == null) {
            throw new InvalidSheepException("Sheep has no age");
        }
        if (sheep.getColor() == null) {
            throw new InvalidSheepException("Sheep has no color");
        }
        if (sheep.getName() == null) {
            throw new InvalidSheepException("Sheep has no name");
        }
        if (sheep.getId() != null) {
            throw new InvalidSheepException("Id already exists");
        }
        return sheepRepository.save(sheep);
    }

    public void delete(Long id) {
        Sheep missingSheep = findById(id);
        sheepRepository.delete(missingSheep);
    }

    public Sheep findById(Long id) {
        return sheepRepository.findById(id).orElseThrow(SheepNotFoundException::new);
    }
}
