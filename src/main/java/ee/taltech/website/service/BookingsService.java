package ee.taltech.website.service;

import ee.taltech.website.dto.RoomDto;
import ee.taltech.website.exception.BookingNotFoundException;
import ee.taltech.website.exception.InvalidBookingException;
import ee.taltech.website.exception.InvalidSearchException;
import ee.taltech.website.exception.RoomNotFoundException;
import ee.taltech.website.model.Booking;
import ee.taltech.website.model.DataToSearchBy;
import ee.taltech.website.model.Room;
import ee.taltech.website.repository.BookingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        if (booking.getPaymentInfo() == null) {
            throw new InvalidBookingException("There is no payment info");
        }
        if (booking.getRoom() == null) {
            throw new InvalidBookingException("There is no information about the room");
        }
        Room roomBeingBooked = roomsService.findById(booking.getRoom().getId());
        checkRoomExceptions(roomBeingBooked);
        if (bookedRoomsCount(booking.getRoom().getId(), booking.getStartDate(), booking.getEndDate())
               == roomBeingBooked.getAmount())  {
            throw new InvalidBookingException("No rooms available");
        }
        return bookingsRepository.save(booking);
    }


    public RoomDto updateAvailabilityData(DataToSearchBy data) {
        checkSearchExceptions(data);
        Room roomBeingBooked = roomsService.findById(data.getRoomId());
        checkRoomExceptions(roomBeingBooked);
        int bookedRoomsCount = bookedRoomsCount(data.getRoomId(), data.getStartDate(), data.getEndDate());
        if (bookedRoomsCount == roomBeingBooked.getAmount())  {
            throw new InvalidBookingException("No rooms  available");
        }
        return new RoomDto(data.getRoomId(), roomBeingBooked.getName(),
                roomBeingBooked.getAmount() - bookedRoomsCount);
    }

    public List<Booking> getBookingsByDate(DataToSearchBy data) {
        checkSearchExceptions(data);
        return filterByDate(bookingsRepository.findAll().stream(), data.getStartDate(), data.getEndDate());
    }

    private void checkRoomExceptions(Room room) {
        if (room == null) {
            throw new RoomNotFoundException();
        }
    }

    private void checkSearchExceptions(DataToSearchBy data) {
        if (data.getRoomId() == null) {
            throw new InvalidSearchException("No info about the room");
        }
        if (data.getStartDate() == null) {
            throw new InvalidSearchException("No info about the startDate");
        }
        if (data.getEndDate() == null) {
            throw new InvalidSearchException("No info about the endDate");
        }
    }

    private int bookedRoomsCount(Long roomId, String startDate, String endDate) {
        Stream<Booking> bookingStreamByCorrectRooms = bookingsRepository.findAll().stream()
                .filter(b -> b.getRoom().getId().intValue() == roomId.intValue());
        return filterByDate(bookingStreamByCorrectRooms, startDate, endDate).size();
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
