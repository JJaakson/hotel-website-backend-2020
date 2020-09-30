package ee.taltech.website;

import ee.taltech.website.model.Room;
import ee.taltech.website.repository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomsApplicationInit implements CommandLineRunner {

    @Autowired
    private RoomsRepository roomsRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Room> rooms = List.of(
                new Room("Room nr. 1", 1L, 1),
                new Room("Room nr. 2", 2L, 1),
                new Room("Room nr. 3", 3L, 1),
                new Room("Room nr. 4", 4L, 1),
                new Room("Room nr. 5", 5L, 1),
                new Room("Room nr. 69", 6L, 1)
        );
        roomsRepository.saveAll(rooms);
    }
}
