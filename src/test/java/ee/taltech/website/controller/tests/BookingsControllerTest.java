package ee.taltech.website.controller.tests;

import ee.taltech.website.controller.utility.TestingUtility;
import ee.taltech.website.model.Booking;
import ee.taltech.website.model.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
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
    private static TestingUtility utilities = new TestingUtility();

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
        Booking addedBooking = utilities.assertOk(exchangeAddedBooking);
        assertEquals("Pille", addedBooking.getName());
        assertEquals(now.toString(), addedBooking.getStartDate());
        assertEquals(twoDaysLater.toString(), addedBooking.getEndDate());
        assertEquals("Paypal", addedBooking.getPaymentInfo());
        assertEquals(room.getId(), addedBooking.getRoom().getId());
        List<Booking> bookings = utilities.getListFromExhange(testRestTemplate, LIST_OF_BOOKINGS, "/bookings");
        assertFalse(bookings.isEmpty());
        assertEquals(1, bookings.size());
        assertEquals(addedBooking.getId(), bookings.get(0).getId());
    }

    @Test
    void query_add_multiple_and_list_of_bookings() {
        List<Room> rooms = utilities.getListFromExhange(testRestTemplate, LIST_OF_ROOMS, "/rooms");
        assertFalse(rooms.isEmpty());
        Room room = rooms.get(0);
        LocalDate now = LocalDate.now();
        LocalDate twoDaysLater = now.plusDays(2L);
        ResponseEntity<Booking> exchangeAddedBooking_1 = utilities.addBooking(testRestTemplate, "Pille",
                now.toString(), twoDaysLater.toString(), room, "Paypal");
        Booking addedBooking_1 = utilities.assertOk(exchangeAddedBooking_1);
        ResponseEntity<Booking> exchangeAddedBooking_2 = utilities.addBooking(testRestTemplate, "Maili",
                now.toString(), twoDaysLater.toString(), room, "MasterCard");
        Booking addedBooking_2 = utilities.assertOk(exchangeAddedBooking_2);
        ResponseEntity<Booking> exchangeAddedBooking_3 = utilities.addBooking(testRestTemplate, "Kalle",
                now.toString(), twoDaysLater.toString(), room, "Cash");
        Booking addedBooking_3 = utilities.assertOk(exchangeAddedBooking_3);
        List<Booking> bookings = utilities.getListFromExhange(testRestTemplate, LIST_OF_BOOKINGS, "/bookings");
        assertFalse(bookings.isEmpty());
        assertEquals(4, bookings.size()); // expected = 3 if test is run individually
        assertEquals(addedBooking_1.getId(), bookings.get(1).getId()); // actualIndex= 0 if test is run individually
        assertEquals(addedBooking_2.getId(), bookings.get(2).getId()); // actualIndex= 1 if test is run individually
        assertEquals(addedBooking_3.getId(), bookings.get(3).getId()); // actualIndex= 2 if test is run individually
    }
}
