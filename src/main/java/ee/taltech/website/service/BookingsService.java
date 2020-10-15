package ee.taltech.website.service;

import ee.taltech.website.dto.RoomDto;
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
        return bookingsRepository.findById(id).orElseThrow(BookingNotFoundException::new);
    }

    public Booking save(Booking booking) {
        if (booking.getId() != null || booking.getName() == null || booking.getStartDate() == null
                || booking.getEndDate() == null || booking.getPaymentInfo() == null || booking.getRoom() == null) {
            throw new InvalidBookingException("Insufficient data");
        }
        Room roomBeingBooked = roomsService.findById(booking.getRoom().getId());
        if (roomBeingBooked == null) {
            throw new RoomNotFoundException();
        }
        if (bookedRoomsCount(booking.getRoom().getId(), booking.getStartDate(), booking.getEndDate())
                == roomBeingBooked.getAmount()) {
            throw new InvalidBookingException("No rooms  available");
        }
        return bookingsRepository.save(booking);
    }

    public RoomDto sendAvailabilityData(DataToSearchBy data) {
        checkSearchExceptions(data);
        Room roomBeingBooked = roomsService.findById(data.getRoomId());
        int bookedRoomsCount = bookedRoomsCount(data.getRoomId(), data.getStartDate(), data.getEndDate());
        if (bookedRoomsCount == roomBeingBooked.getAmount())  {
            throw new InvalidBookingException("No rooms available");
        }
        Period period = Period.between(LocalDate.parse(data.getStartDate()), LocalDate.parse(data.getEndDate()));
        return new RoomDto(data.getRoomId(), roomBeingBooked.getName(),
                roomBeingBooked.getAmount() - bookedRoomsCount,
                roomBeingBooked.getCost() * (period.getDays() - 1));
    }

    public List<Booking> getBookingsByDate(DataToSearchBy data) {
        checkSearchExceptions(data);
        return filterByDate(bookingsRepository.findAll().stream(), data.getStartDate(), data.getEndDate());
    }

    private void checkSearchExceptions(DataToSearchBy data) {
        if (data.getRoomId() == null || data.getStartDate() == null || data.getEndDate() == null) {
            throw new InvalidSearchException("Data insufficient");
        }
    }

    private int bookedRoomsCount(Long roomId, String startDate, String endDate) {
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
