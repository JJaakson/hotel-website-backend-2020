package ee.taltech.website;

import ee.taltech.website.model.Booking;
import ee.taltech.website.model.RoomType;
import ee.taltech.website.repository.BookingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class BookingsApplicationInit implements CommandLineRunner {

    @Autowired
    private BookingsRepository bookingsRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Booking> bookings = List.of(
        );
        bookingsRepository.saveAll(bookings);
    }
}
