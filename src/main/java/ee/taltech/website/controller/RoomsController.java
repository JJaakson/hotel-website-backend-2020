package ee.taltech.website.controller;

import ee.taltech.website.model.Room;
import ee.taltech.website.security.Roles;
import ee.taltech.website.service.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping({"rooms", "rooms2"})
@RestController
public class RoomsController {

    @Autowired
    private RoomsService roomsService;

    @GetMapping
    public List<Room> getRooms() {
        return roomsService.findAll();
    }

    @GetMapping("{id}")
    public Room getRoom(@PathVariable Long id) {
        return roomsService.findById(id);
    }

    @Secured(Roles.ADMIN)
    @PostMapping("/{id}/price")
    public Room updateRoomCost(@RequestBody String price, @PathVariable Long id) throws JSONException {
        JSONObject jsonObject = new JSONObject(price);
        Integer cost = jsonObject.getInt("price");
        return roomsService.updateCost(cost, id);
    }
}
