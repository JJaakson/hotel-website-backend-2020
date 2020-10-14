package ee.taltech.website.controller;

import ee.taltech.website.model.Booking;
import ee.taltech.website.model.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookingsControllerTest {

    public static final ParameterizedTypeReference<List<Booking>> LIST_OF_BOOKINGS =
            new ParameterizedTypeReference<>() { };
    public static final ParameterizedTypeReference<List<Room>> LIST_OF_ROOMS =
            new ParameterizedTypeReference<>() { };

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void query_for_saving_a_booking() {
        ResponseEntity<List<Room>> exchangeRooms = testRestTemplate.exchange("/rooms",
                HttpMethod.GET, null, LIST_OF_ROOMS);
        List<Room> rooms = assertOk(exchangeRooms);
        assertFalse(rooms.isEmpty());
        Room room = rooms.get(0);
        LocalDate now = LocalDate.now();
        LocalDate twoDaysLater = now.plusDays(2L);
        ResponseEntity<Booking> exchangeAddedBooking = addBooking("Pille", now.toString(),
                twoDaysLater.toString(), room, "Paypal");
        Booking addedBooking = assertOk(exchangeAddedBooking);
        assertEquals("Pille", addedBooking.getName());
        assertEquals(now.toString(), addedBooking.getStartDate());
        assertEquals(twoDaysLater.toString(), addedBooking.getEndDate());
        assertEquals("Paypal", addedBooking.getPaymentInfo());
        assertEquals(room.getId(), addedBooking.getRoom().getId());
        ResponseEntity<List<Booking>> exchangeBookings = testRestTemplate.exchange("/bookings",
                HttpMethod.GET, null, LIST_OF_BOOKINGS);
        List<Booking> bookings = assertOk(exchangeBookings);
        assertFalse(bookings.isEmpty());
        assertEquals(1, bookings.size());
        assertEquals(addedBooking.getId(), bookings.get(0).getId());
    }

    @Test
    void query_add_multiple_and_list_of_bookings() {
        ResponseEntity<List<Room>> exchangeRooms = testRestTemplate.exchange("/rooms",
                HttpMethod.GET, null, LIST_OF_ROOMS);
        List<Room> rooms = assertOk(exchangeRooms);
        assertFalse(rooms.isEmpty());
        Room room = rooms.get(0);
        LocalDate now = LocalDate.now();
        LocalDate twoDaysLater = now.plusDays(2L);
        ResponseEntity<Booking> exchangeAddedBooking_1 = addBooking("Pille", now.toString(),
                twoDaysLater.toString(), room, "Paypal");
        Booking addedBooking_1 = assertOk(exchangeAddedBooking_1);
        ResponseEntity<Booking> exchangeAddedBooking_2 = addBooking("Maili", now.toString(),
                twoDaysLater.toString(), room, "MasterCard");
        Booking addedBooking_2 = assertOk(exchangeAddedBooking_2);
        ResponseEntity<Booking> exchangeAddedBooking_3 = addBooking("Kalle", now.toString(),
                twoDaysLater.toString(), room, "Cash");
        Booking addedBooking_3 = assertOk(exchangeAddedBooking_3);
        ResponseEntity<List<Booking>> exchangeBookings = testRestTemplate.exchange("/bookings",
                HttpMethod.GET, null, LIST_OF_BOOKINGS);
        List<Booking> bookings = assertOk(exchangeBookings);
        assertFalse(bookings.isEmpty());
        assertEquals(4, bookings.size()); // expected = 3 if test is run individually
        assertEquals(addedBooking_1.getId(), bookings.get(1).getId()); // actualIndex= 0 if test is run individually
        assertEquals(addedBooking_2.getId(), bookings.get(2).getId()); // actualIndex= 1 if test is run individually
        assertEquals(addedBooking_3.getId(), bookings.get(3).getId()); // actualIndex= 2 if test is run individually
    }

    private ResponseEntity<Booking> addBooking(String name, String startDate, String endDate,
                                               Room room, String paymentInfo) {
        Booking newBooking = new Booking (name, startDate, endDate, room, paymentInfo);
        return testRestTemplate.exchange("/bookings",
                HttpMethod.POST, new HttpEntity<>(newBooking), Booking.class);
    }

    private <T> T assertOk(ResponseEntity<T> exchange) {
        assertNotNull(exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        return exchange.getBody();
    }

}
