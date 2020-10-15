package ee.taltech.website.service;

import ee.taltech.website.dto.DataToSearchBy;
import ee.taltech.website.dto.RoomDto;
import ee.taltech.website.exception.InvalidBookingException;
import ee.taltech.website.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class AvailabilityService {

    @Autowired
    private RoomsService roomsService;

    @Autowired
    private BookingsService bookingsService;

    public RoomDto sendAvailabilityData(DataToSearchBy data) {
        bookingsService.checkSearchExceptions(data);
        Room roomBeingBooked = roomsService.findById(data.getRoomId());
        int bookedRoomsCount = bookingsService.bookedRoomsCount(data.getRoomId(), data.getStartDate(),
                data.getEndDate());
        if (bookedRoomsCount == roomBeingBooked.getAmount())  {
            throw new InvalidBookingException("No rooms available");
        }
        Period period = Period.between(LocalDate.parse(data.getStartDate()), LocalDate.parse(data.getEndDate()));
        return new RoomDto(data.getRoomId(), roomBeingBooked.getName(),
                roomBeingBooked.getAmount() - bookedRoomsCount,
                roomBeingBooked.getCost() * period.getDays());
    }
}
