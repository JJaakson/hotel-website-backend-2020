package ee.taltech.website.controller.tests;

import ee.taltech.website.model.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RoomsControllerTest {

    public static final ParameterizedTypeReference<List<Room>> LIST_OF_ROOMS =
            new ParameterizedTypeReference<List<Room>>() { };

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void one_can_query_for_all_rooms() {
        ResponseEntity<List<Room>> exchange = testRestTemplate.exchange("/rooms",
                HttpMethod.GET, null, LIST_OF_ROOMS);
        List<Room> rooms = assertOk(exchange);
        assertFalse(rooms.isEmpty());
        Room room = rooms.get(0);
        assertEquals("Standard Room", room.getName());
    }

    @Test
    void one_can_query_for_1_room() {
        ResponseEntity<Room> exchange = testRestTemplate.exchange("/rooms/2",
                HttpMethod.GET, null, Room.class);
        Room room = assertOk(exchange);
        assertEquals("Superior Room", room.getName());
    }

    private <T> T assertOk(ResponseEntity<T> exchange) {
        assertNotNull(exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        return exchange.getBody();
    }

}
