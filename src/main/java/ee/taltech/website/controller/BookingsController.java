package ee.taltech.website.controller;

import ee.taltech.website.model.Booking;
import ee.taltech.website.service.BookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("bookings")
@RestController
public class BookingsController {

    @Autowired
    private BookingsService bookingsService;

    @GetMapping
    public List<Booking> getBookings(@RequestParam(value = "id", required = false) Long id) {
        return bookingsService.findAll();
    }

    @GetMapping("{id}")
    public Booking getBooking(@PathVariable Long id) { return bookingsService.findById(id); }

    @PostMapping("{id}")
    public Booking saveBooking(@RequestBody Booking booking) { return bookingsService.save(booking); }

    /*@PutMapping("{id}")
    public Booking updateBooking(@RequestBody Booking booking) { return bookingsService.update(booking); }
*/
    @DeleteMapping("{id}")
    public void updateBooking(@PathVariable Long id) { bookingsService.delete(id); }



}
