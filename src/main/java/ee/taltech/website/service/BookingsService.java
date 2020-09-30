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
        //checkExceptions(booking);
        if (booking.getId() != null) {
            throw new InvalidBookingException("Id is already present");
        }
        return bookingsRepository.save(booking);
    }

    /*private boolean areThereAnyRoomsAvailable(Booking booking) {
        Room roomType = booking.getRoom();
        List<Booking> bookedRooms = bookingsRepository.findAll().stream()
                .filter(b -> b.getRoom().getId().equals(roomType.getId())
                        && b.getStartDate().isBefore(booking.getStartDate())
                        && b.getEndDate().isAfter(booking.getEndDate())).collect(Collectors.toList());
        return bookedRooms.size() < roomType.getAmount();
    }

    private void checkExceptions(Booking booking) {
        if (booking.getRoom() == null) {
            throw new InvalidBookingException("Booking has no room");
        } else if (booking.getStartDate() == null) {
            throw new InvalidBookingException("Booking has no start date");
        } else if (booking.getEndDate() == null){
            throw new InvalidBookingException("Booking has no end date");
        } else if (!areThereAnyRoomsAvailable(booking)) {
            throw new InvalidBookingException("This type of rooms are all booked out for that time period");
        }
    }

    public Booking update(Booking booking) {
        checkExceptions(booking);
        Booking dbBooking = findById(booking.getId());
        dbBooking.setRoom(booking.getRoom());
        dbBooking.setStartDate(booking.getStartDate());
        dbBooking.setEndDate(booking.getEndDate());
        return bookingsRepository.save(dbBooking);
    }*/

    public void delete(Long id) {
        Booking dbBooking = findById(id);
        bookingsRepository.delete(dbBooking);
    }
}
