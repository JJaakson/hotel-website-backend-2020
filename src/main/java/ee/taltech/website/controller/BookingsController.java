package ee.taltech.website.controller;

import ee.taltech.website.model.Booking;
import ee.taltech.website.dto.DataToSearchBy;
import ee.taltech.website.security.jwt.JwtUtil;
import ee.taltech.website.service.BookingsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("bookings")
@RestController
public class BookingsController {

    private JwtUtil jwtUtil;

    @Autowired
    private BookingsService bookingsService;

    @RequestMapping
    public List<Booking> getBookingByUsername(@RequestParam(value="username") String token) {
        String username = jwtUtil.getUsernameFromToken(token);
        return bookingsService.findAllByUsername(username);
    }

    @GetMapping("all")
    public List<Booking> getBookings() {
        return bookingsService.findAll();
    }

    @GetMapping("{id}")
    public Booking getBooking(@PathVariable Long id) {
        return bookingsService.findById(id);
    }

    @PostMapping
    public Booking saveBooking(@RequestBody Booking booking) {
        return bookingsService.save(booking);
    }

    @DeleteMapping("{id}")
    public void deleteBooking(@PathVariable Long id, @RequestHeader (name = "Authorization") String token) throws Exception {
        String username = jwtUtil.getUsernameFromToken(token.substring(7));
        bookingsService.deleteBooking(id, username);
    }

    @PutMapping
    public List<Booking> getBookingsByDate(@RequestBody DataToSearchBy data) {
        return bookingsService.getBookingsByDate(data);
    }


}
