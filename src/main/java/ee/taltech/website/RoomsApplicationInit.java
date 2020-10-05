package ee.taltech.website;

import ee.taltech.website.model.Room;
import ee.taltech.website.model.RoomType;
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
                new Room ("Standard Single", RoomType.STANDARD_SINGLE),
                new Room( "Standard Double", RoomType.STANDARD_DOUBLE),
                new Room( "Deluxe", RoomType.DELUXE)
        );
        roomsRepository.saveAll(rooms);
    }
}
