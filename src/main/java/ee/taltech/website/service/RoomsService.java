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

    public List<Room> findAll(String name) {
        return roomsRepository.findAll();
    }

    public Room findById(Long id) {
        return roomsRepository.findById(id)
                .orElseThrow(RoomNotFoundException::new);
    }
}
