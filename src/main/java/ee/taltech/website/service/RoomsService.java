package ee.taltech.website.service;

import ee.taltech.website.exception.RoomNotFoundException;
import ee.taltech.website.model.Room;
import ee.taltech.website.repository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomsService {

    @Autowired
    private RoomsRepository roomsRepository;

    public List<Room> findAll() {
        return roomsRepository.findAll();
    }

    public Room findById(Long id) {
        return roomsRepository.findById(id)
                .orElseThrow(RoomNotFoundException::new);
    }

    public Room updateCost(Integer cost, Long id) {
        Room room = findById(id);
        room.setCost(cost);
        return roomsRepository.save(room);
    }

}
