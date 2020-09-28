package ee.taltech.website.service;

import ee.taltech.website.exception.InvalidRoomException;
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
        //todo add name
        return roomsRepository.findAll();
    }

    public Room findById(Long id) {
        return roomsRepository.findById(id)
                .orElseThrow(RoomNotFoundException::new);
    }

    public Room save(Room room) {
        if (room.getName() == null) {
            throw new InvalidRoomException("Room has no name");
        }
        if (room.getId() != null){
            throw new InvalidRoomException("Id is already present");
        }
        // save will generate id for object
        return roomsRepository.save(room);
    }

    public Room update(Room room, Long id) {
        if (room.getName() == null) {
            throw new InvalidRoomException("Room has no name");
        }
        Room dbRoom = findById(id);
        dbRoom.setName(room.getName());
        // save works as update when id is present
        return roomsRepository.save(dbRoom);
    }

    public void delete(Long id) {
        Room dbRoom = findById(id);
        roomsRepository.delete(dbRoom);
    }
}
