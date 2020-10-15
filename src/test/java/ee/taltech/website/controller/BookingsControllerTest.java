package ee.taltech.website.controller;

import ee.taltech.website.utility.TestingUtility;
import ee.taltech.website.model.Booking;
import ee.taltech.website.model.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookingsControllerTest {

    private static final ParameterizedTypeReference<List<Booking>> LIST_OF_BOOKINGS =
            new ParameterizedTypeReference<>() { };
    private static final ParameterizedTypeReference<List<Room>> LIST_OF_ROOMS =
            new ParameterizedTypeReference<>() { };
    private static final TestingUtility utilities = new TestingUtility();

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    void query_for_saving_a_booking() {
        List<Room> rooms = utilities.getListFromExhange(testRestTemplate, LIST_OF_ROOMS, "/rooms");
        assertFalse(rooms.isEmpty());
        Room room = rooms.get(0);
        LocalDate now = LocalDate.now();
        LocalDate twoDaysLater = now.plusDays(2L);
        ResponseEntity<Booking> exchangeAddedBooking = utilities.addBooking(testRestTemplate, "Pille",
                now.toString(), twoDaysLater.toString(), room, "Paypal");
        utilities.addBooking(testRestTemplate, "Kalle",
                now.toString(), twoDaysLater.toString(), room, "Cash");
        Booking addedBooking = utilities.assertOk(exchangeAddedBooking);
        assertEquals("Pille", addedBooking.getName());
        assertEquals(now.toString(), addedBooking.getStartDate());
        assertEquals(twoDaysLater.toString(), addedBooking.getEndDate());
        assertEquals("Paypal", addedBooking.getPaymentInfo());
        assertEquals(room.getId(), addedBooking.getRoom().getId());
        List<Booking> bookings = utilities.getListFromExhange(testRestTemplate, LIST_OF_BOOKINGS, "/bookings");
        assertFalse(bookings.isEmpty());
        assertEquals(2, bookings.size());
        assertEquals(addedBooking.getId(), bookings.get(0).getId());
    }

    @Test
    void query_for_getting_by_id() {
        Booking booking = utilities.assertOk(testRestTemplate.exchange("/bookings/4",
                HttpMethod.GET, null, Booking.class));
        assertEquals("Pille", booking.getName());
        assertEquals(LocalDate.now().toString(), booking.getStartDate());
    }
}
