package ee.taltech.website.controller;

import ee.taltech.website.model.Room;
import ee.taltech.website.utility.TestingUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RoomsControllerTest {

    public static final ParameterizedTypeReference<List<Room>> LIST_OF_ROOMS =
            new ParameterizedTypeReference<>() { };
    private static final TestingUtility utilities = new TestingUtility();


    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void one_can_query_for_all_rooms() {
        List<Room> rooms = utilities.getListFromExhange(testRestTemplate, LIST_OF_ROOMS, "/rooms");
        assertFalse(rooms.isEmpty());
        assertEquals("Standard Room", rooms.get(0).getName());
        assertEquals("Superior Room", rooms.get(1).getName());
        assertEquals("Deluxe", rooms.get(2).getName());
    }

    @Test
    void one_can_query_for_1_room() {
        Room room = utilities.assertOk(testRestTemplate.exchange("/rooms/2",
                HttpMethod.GET, null, Room.class));
        assertEquals("Superior Room", room.getName());
    }
}
