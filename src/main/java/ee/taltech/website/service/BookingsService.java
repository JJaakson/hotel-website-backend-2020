package ee.taltech.website.service;

import ee.taltech.website.exception.BookingNotFoundException;
import ee.taltech.website.exception.InvalidBookingException;
import ee.taltech.website.model.Booking;
import ee.taltech.website.model.Room;
import ee.taltech.website.repository.BookingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingsService {

    @Autowired
    private BookingsRepository bookingsRepository;

    @Autowired
    private RoomsService roomsService;

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
            throw new InvalidBookingException("There is no name");
        }
        if (booking.getStartDate() == null) {
            throw new InvalidBookingException("There is no startDate");
        }
        if (booking.getEndDate() == null) {
            throw new InvalidBookingException("There is no startDate");
        }
        Room roomBeingBooked = roomsService.findById(booking.getRoomId());
        if (availableRooms(booking) == roomBeingBooked.getAmount())  {
            throw new InvalidBookingException("No rooms available");
        }
        roomBeingBooked.setAmount(roomBeingBooked.getAmount() - 1);
        return bookingsRepository.save(booking);
    }

    public int availableRooms(Booking booking) {
        //long roomType = booking.getRoomId();
        LocalDate bookingStart = LocalDate.parse(booking.getStartDate());
        LocalDate bookingEnd = LocalDate.parse(booking.getEndDate());
        List<Booking> bookedRooms = bookingsRepository.findAll().stream()
                .filter(b -> b.getRoomId().equals(booking.getRoomId())
                        && bookingStart.isAfter(LocalDate.parse(b.getStartDate()))
                        && bookingStart.isBefore(LocalDate.parse(b.getEndDate()))
                        || bookingEnd.isAfter(LocalDate.parse(b.getStartDate()))
                        && bookingEnd.isBefore(LocalDate.parse(b.getEndDate()))
                        || bookingStart.isBefore(LocalDate.parse(b.getStartDate()))
                        && bookingEnd.isAfter(LocalDate.parse(b.getEndDate()))
                        || bookingStart.isEqual(LocalDate.parse(b.getStartDate()))
                        || bookingEnd.isEqual(LocalDate.parse(b.getEndDate()))
                        || bookingStart.isEqual(LocalDate.parse(b.getEndDate()))
                        || bookingEnd.isEqual(LocalDate.parse(b.getStartDate())))
                .collect(Collectors.toList());
        return bookedRooms.size();
    }

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
