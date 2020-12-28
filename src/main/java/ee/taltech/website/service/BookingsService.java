package ee.taltech.website.service;

import ee.taltech.website.exception.BookingNotFoundException;
import ee.taltech.website.exception.InvalidBookingException;
import ee.taltech.website.exception.InvalidSearchException;
import ee.taltech.website.exception.RoomNotFoundException;
import ee.taltech.website.model.Booking;
import ee.taltech.website.dto.DataToSearchBy;
import ee.taltech.website.model.Room;
import ee.taltech.website.repository.BookingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Booking booking = bookingsRepository.findById(id).orElseThrow(BookingNotFoundException::new);
        Period period = Period.between(LocalDate.parse(booking.getStartDate()), LocalDate.parse(booking.getEndDate()));
        booking.getRoom().setCost(period.getDays() * booking.getRoom().getCost());
        return booking;
    }

    public List<Booking> findAllByUsername(String username) {
        return bookingsRepository.findAllByUsername(username);
    }

    public Booking save(Booking booking) {
        if (booking.getId() != null || booking.getName() == null || booking.getStartDate() == null
                || booking.getEndDate() == null || booking.getPaymentInfo() == null || booking.getRoom() == null) {
            throw new InvalidBookingException("Insufficient data");
        }
        Room roomBeingBooked = roomsService.findById(booking.getRoom().getId());
        if (bookedRoomsCount(booking.getRoom().getId(), booking.getStartDate(), booking.getEndDate())
                == roomBeingBooked.getAmount()) {
            throw new InvalidBookingException("No rooms  available");
        }
        return bookingsRepository.save(booking);
    }

    public List<Booking> getBookingsByDate(DataToSearchBy data) {
        checkSearchExceptions(data);
        return filterByDate(bookingsRepository.findAll().stream(), data.getStartDate(), data.getEndDate());
    }

    public void checkSearchExceptions(DataToSearchBy data) {
        if (data.getRoomId() == null || data.getStartDate() == null || data.getEndDate() == null) {
            throw new InvalidSearchException("Data insufficient");
        }
    }

    public int bookedRoomsCount(Long roomId, String startDate, String endDate) {
        return filterByDate(bookingsRepository.findAll().stream()
                .filter(b -> b.getRoom().getId().intValue() == roomId.intValue()), startDate, endDate).size();
    }

    private List<Booking> filterByDate(Stream<Booking> streamOfBookings, String start, String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return streamOfBookings.filter(b -> startDate.isAfter(LocalDate.parse(b.getStartDate()))
                && startDate.isBefore(LocalDate.parse(b.getEndDate()))
                || endDate.isAfter(LocalDate.parse(b.getStartDate()))
                && endDate.isBefore(LocalDate.parse(b.getEndDate()))
                || startDate.isBefore(LocalDate.parse(b.getStartDate()))
                && endDate.isAfter(LocalDate.parse(b.getEndDate()))
                || startDate.isEqual(LocalDate.parse(b.getStartDate()))
                || endDate.isEqual(LocalDate.parse(b.getEndDate()))
                || startDate.isEqual(LocalDate.parse(b.getEndDate()))
                || endDate.isEqual(LocalDate.parse(b.getStartDate())))
                .collect(Collectors.toList());
    }
}
