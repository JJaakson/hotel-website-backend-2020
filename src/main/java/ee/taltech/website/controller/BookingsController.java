package ee.taltech.website.controller;

import ee.taltech.website.model.Booking;
import ee.taltech.website.dto.DataToSearchBy;
import ee.taltech.website.service.BookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("bookings")
@RestController
public class BookingsController {

    @Autowired
    private BookingsService bookingsService;

    @RequestMapping
    public List<Booking> getBookingByUsername(@RequestParam(value="username") String username) {
        return bookingsService.findAllByUsername(username);
    }

    @GetMapping("all")
    public List<Booking> getBookings() {
        return bookingsService.findAll();
    }

    @RequestMapping(value = "/{id}")
    public Booking getBooking(@PathVariable Long id) {
        return bookingsService.findById(id);
    }

    @PostMapping
    public Booking saveBooking(@RequestBody Booking booking) {
        return bookingsService.save(booking);
    }

    @PutMapping
    public List<Booking> getBookingsByDate(@RequestBody DataToSearchBy data) {
        return bookingsService.getBookingsByDate(data);
    }


}
