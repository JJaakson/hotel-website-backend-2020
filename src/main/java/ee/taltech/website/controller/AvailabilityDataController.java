package ee.taltech.website.controller;

import ee.taltech.website.dto.DataToSearchBy;
import ee.taltech.website.dto.RoomDto;
import ee.taltech.website.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("availability")
@RestController
public class AvailabilityDataController {

    @Autowired
    private AvailabilityService availabilityService;

    @PutMapping
    public RoomDto sendAvailabilityData(@RequestBody DataToSearchBy data) {
        return availabilityService.sendAvailabilityData(data);
    }
}
