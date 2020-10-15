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
                new Room ("Standard Room", 4, 48, "Standard rooms are light colored with " +
                        "an average room size of 21 square meters and with a kingsize bed (180x200cm). All " +
                        "standard rooms have air conditioning, as well as flat-screen TV, high speed WiFi " +
                        "and cable Internet. Windows have blackout blinds, bathroom floor has heating and " +
                        "addition to the shower, there is a hairdryer. Standard rooms also have a wooden " +
                        "writing desk with a direct-dial telephone."),
                new Room( "Superior Room", 4, 60, "Superior rooms are light colored with " +
                        "an average room size of 21 square meters and with a kingsize bed (180x200cm). All " +
                        "superior rooms have air conditioning, carpeted floor, high-speed WiFi and cable Internet. " +
                        "The rooms have a refrigerator, modern coffee machine, flat-screen TV and blackout blinds " +
                        "on the windows. In the bathroom you can find a modern shower, hairdryer, robe and slippers," +
                        " and the floors have heating. "),
                new Room( "Deluxe", 2, 110, "Deluxe rooms are very spacious, with an " +
                        "average room size of 50 square meters. Deluxe room consists of living room, bed room " +
                        "with kingsize bed (180x200cm), bathroom with sauna and restroom for guests. Delux room " +
                        "has air conditioning, 2 flat-screen TV's, sofa table, sofa set, dining table with 4 " +
                        "chairs, modern coffee machine, refrigerator. It ofcourse has high speed WiFi and cable " +
                        "Internet, a writing desk, direct-dial telephone and blackout curtains. Bathroom has a " +
                        "bathtun, sauna with electrical heater, a shower, one hairdryer and the " +
                        "floors are heated. Perfect place to stay if you want to live like a king.")
        );
        roomsRepository.saveAll(rooms);
    }
}
