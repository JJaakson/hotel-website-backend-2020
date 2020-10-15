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

    @GetMapping("{id}")
    public Room getRoom(@PathVariable Long id) {
        return roomsService.findById(id);
    }
}
