//package ee.taltech.website;
//
//import ee.taltech.website.model.Room;
//import ee.taltech.website.repository.ItemRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class WebsiteApplicationInit implements CommandLineRunner {
//
//    @Autowired
//    private ItemRepository itemRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        List<Room> heroes = List.of(
//                new Room("Batman", 1),
//                new Room("Superman", 2),
//                new Room("Wonder Woman", 3)
//        );
//        itemRepository.saveAll(heroes);
//    }
//}
