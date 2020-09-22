package ee.taltech.website;

import ee.taltech.website.model.Item;
import ee.taltech.website.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WebsiteApplicationInit implements CommandLineRunner {

    @Autowired
    private EntityRepository entityRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Item> heroes = List.of(
                new Item("Batman", 1),
                new Item("Superman", 2),
                new Item("Wonder Woman", 3)
        );
        entityRepository.saveAll(heroes);
    }
}
