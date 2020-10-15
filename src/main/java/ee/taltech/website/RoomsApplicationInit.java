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
                new Room ("Standard Room", 5),
                new Room( "Superior Room", 4),
                new Room( "Deluxe", 2)
        );
        roomsRepository.saveAll(rooms);
    }
}
