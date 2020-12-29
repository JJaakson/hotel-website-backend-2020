package ee.taltech.website.utility;

import ee.taltech.website.common.RestTemplateTests;
import ee.taltech.website.model.Booking;
import ee.taltech.website.model.Room;
import ee.taltech.website.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



public class TestingUtility extends RestTemplateTests {

    public static final String ADMIN = "admin";


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
                HttpMethod.POST, entity(newBooking, "user"), Booking.class);
    }

    public <T> T assertOk(ResponseEntity<T> exchange) {
        assertNotNull(exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        return exchange.getBody();
    }

    private <T> HttpEntity<T> entity(T booking, String username) {
        HttpHeaders headers = authorizationHeader(username);
        return new HttpEntity<>(booking, headers);
    }

}
