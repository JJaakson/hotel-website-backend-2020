package ee.taltech.website.service;

import ee.taltech.website.dto.RoomDto;
import ee.taltech.website.exception.BookingNotFoundException;
import ee.taltech.website.exception.InvalidBookingException;
import ee.taltech.website.model.AvailabilityData;
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
        if (booking.getPaymentInfo() == null) {
            throw new InvalidBookingException("There is no payment info");
        }
        if (booking.getRoom() == null) {
            throw new InvalidBookingException("There is no information about the room");
        }
        if (availableRooms(booking.getRoom().getId(), booking.getStartDate(), booking.getEndDate())
               == booking.getRoom().getAmount())  {
            throw new InvalidBookingException("No rooms available");
        }
        return bookingsRepository.save(booking);
    }

    public int availableRooms(Long roomId, String startDate, String endDate) {
        LocalDate bookingStart = LocalDate.parse(startDate);
        LocalDate bookingEnd = LocalDate.parse(endDate);
        List<Booking> bookedRooms = bookingsRepository.findAll().stream()
                .filter(b -> b.getRoom().getId().intValue() == roomId.intValue())
                .filter(b -> bookingStart.isAfter(LocalDate.parse(b.getStartDate()))
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

    public RoomDto updateAvailabilityData(AvailabilityData data) {
        Room roomBeingBooked = roomsService.findById(data.getRoomId());
        int availableRoomsCount = availableRooms(data.getRoomId(), data.getStartDate(), data.getEndDate());
        if (availableRoomsCount == roomBeingBooked.getAmount())  {
            throw new InvalidBookingException("No rooms available");
        }
        return new RoomDto(data.getRoomId(), roomBeingBooked.getName(),
                roomBeingBooked.getAmount() - availableRoomsCount);
    }
}
