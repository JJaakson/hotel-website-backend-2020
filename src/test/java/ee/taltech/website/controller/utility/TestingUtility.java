package ee.taltech.website.controller.utility;

import ee.taltech.website.model.Booking;
import ee.taltech.website.model.Room;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestingUtility {

    public  <T> List<T> getListFromExhange(TestRestTemplate testRestTemplate,
                                           ParameterizedTypeReference<List<T>> exchange, String url) {
        return assertOk(testRestTemplate.exchange(url,
                HttpMethod.GET, null, exchange));
    }

    public ResponseEntity<Booking> addBooking(TestRestTemplate testRestTemplate,
                                              String name, String startDate, String endDate,
                                               Room room, String paymentInfo) {
        Booking newBooking = new Booking (name, startDate, endDate, room, paymentInfo);
        return testRestTemplate.exchange("/bookings",
                HttpMethod.POST, new HttpEntity<>(newBooking), Booking.class);
    }

    public <T> T assertOk(ResponseEntity<T> exchange) {
        assertNotNull(exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        return exchange.getBody();
    }
}
