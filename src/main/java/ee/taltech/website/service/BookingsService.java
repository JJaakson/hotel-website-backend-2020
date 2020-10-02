package ee.taltech.website.service;

import ee.taltech.website.exception.BookingNotFoundException;
import ee.taltech.website.exception.InvalidBookingException;
import ee.taltech.website.model.Booking;
import ee.taltech.website.model.Room;
import ee.taltech.website.repository.BookingsRepository;
import ee.taltech.website.repository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingsService {

    @Autowired
    private RoomsRepository roomsRepository;
    @Autowired
    private BookingsRepository bookingsRepository;

    public List<Booking> findAll() {
        return bookingsRepository.findAll();
    }

    public Booking findById(Long id) {
        return bookingsRepository.findById(id).orElseThrow(BookingNotFoundException::new);
    }

    public Booking save(Booking booking) {
        if (booking.getId() != null) {
            throw new InvalidBookingException("Id is already present");
        }
        if (booking.getName() == null) {
            throw new InvalidBookingException("ther is no name");
        }
        if (booking.getStartDate() == null) {
            throw new InvalidBookingException("There is no startDate");
        }
        if (booking.getEndDate() == null) {
            throw new InvalidBookingException("There is no startDate");
        }
        return bookingsRepository.save(booking);
    }

    /*private boolean areThereAnyRoomsAvailable(Booking booking) {
        long roomType = booking.getRoomId();
        List<Booking> bookedRooms = bookingsRepository.findAll().stream()
                .filter(b -> b.getRoomId() == roomType
                        && LocalDate.parse(b.getStartDate()).isBefore(LocalDate.parse(booking.getStartDate()))
                        && LocalDate.parse(b.getEndDate()).isAfter(LocalDate.parse(booking.getEndDate())))
                .collect(Collectors.toList());
        return bookedRooms.size() == 0;
    }*/

    public Booking update(Booking booking) {
        Booking dbBooking = findById(booking.getId());
        dbBooking.setName(booking.getName());
        return bookingsRepository.save(dbBooking);
    }

    public void delete(Long id) {
        Booking dbBooking = findById(id);
        bookingsRepository.delete(dbBooking);
    }
}
