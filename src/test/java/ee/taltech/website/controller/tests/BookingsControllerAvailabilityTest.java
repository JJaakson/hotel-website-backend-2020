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
class BookingsControllerAvailabilityTest {

    public static final ParameterizedTypeReference<List<Booking>> LIST_OF_BOOKINGS =
            new ParameterizedTypeReference<>() { };
    public static final ParameterizedTypeReference<List<Room>> LIST_OF_ROOMS =
            new ParameterizedTypeReference<>() { };
    private static TestingUtility utilities = new TestingUtility();

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    void query_for_availability_data() {
        List<Room> rooms = utilities.getListFromExhange(testRestTemplate, LIST_OF_ROOMS, "/rooms");
        assertFalse(rooms.isEmpty());
        Room room = rooms.get(0);
        LocalDate now = LocalDate.now();
        LocalDate twoDaysLater = now.plusDays(2L);
        ResponseEntity<Booking> exchangeAddedBooking_1 = utilities.addBooking(testRestTemplate, "Pille",
                now.toString(), twoDaysLater.toString(), room, "Paypal");
        Booking addedBooking_1 = utilities.assertOk(exchangeAddedBooking_1);
    }

}
