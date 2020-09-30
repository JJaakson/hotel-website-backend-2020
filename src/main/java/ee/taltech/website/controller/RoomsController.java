package ee.taltech.website.controller;

import ee.taltech.website.model.Room;
import ee.taltech.website.service.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("rooms")
@RestController
public class RoomsController {

    @Autowired
    private RoomsService roomsService;

    @GetMapping
    public List<Room> getRooms(@RequestParam(value = "name", required = false) String name) {
        return roomsService.findAll(name);
    }

    //todo get 1
    @GetMapping("{id}")
    public Room getRoom(@PathVariable Long id) {
        return roomsService.findById(id);
    }

    //todo save
    @PostMapping
    public Room saveRoom(@RequestBody Room room) {
        return roomsService.save(room);
    }

    //todo update
    @PutMapping("{id}")
    public Room updateRoom(@RequestBody Room room, @PathVariable Long id) {
        return roomsService.update(room, id);
    }

    //todo delete
    @DeleteMapping("{id}")
    public void updateRoom(@PathVariable Long id) {
        roomsService.delete(id);
    }
    //todo validation?
    //todo add tests
}
