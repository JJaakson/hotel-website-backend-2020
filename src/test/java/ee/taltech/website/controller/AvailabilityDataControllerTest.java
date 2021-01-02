package ee.taltech.website.controller;

import ee.taltech.website.common.RestTemplateTests;
import ee.taltech.website.dto.DataToSearchBy;
import ee.taltech.website.dto.RoomDto;
import ee.taltech.website.model.Booking;
import ee.taltech.website.model.Room;
import ee.taltech.website.utility.TestingUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AvailabilityDataControllerTest extends RestTemplateTests {

    private static final ParameterizedTypeReference<List<Room>> LIST_OF_ROOMS =
            new ParameterizedTypeReference<>() { };
    private static final ParameterizedTypeReference<List<Booking>> LIST_OF_BOOKINGS =
            new ParameterizedTypeReference<>() { };
    private static final TestingUtility utilities = new TestingUtility();

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void query_for_availability_data() {
        List<Room> rooms = utilities.getListFromExhange(testRestTemplate, LIST_OF_ROOMS, "/rooms");
        assertFalse(rooms.isEmpty());
        Room room = rooms.get(0);
        LocalDate now = LocalDate.now();
        LocalDate fiveDaysLater = now.plusDays(2L);
        DataToSearchBy data = new DataToSearchBy(room.getId(), now.toString(), fiveDaysLater.toString());
        ResponseEntity<RoomDto> exchangeData = testRestTemplate.exchange("/availability",
                HttpMethod.PUT, entity(data, "user"), RoomDto.class);
        RoomDto receivedData = utilities.assertOk(exchangeData);
        //List<Booking> bookings = utilities.getListFromExhange(testRestTemplate, LIST_OF_BOOKINGS, "/bookings");
        assertEquals(room.getId(), receivedData.getId());
       // assertEquals(room.getAmount() - bookings.size(), receivedData.getAmount());
        assertEquals(room.getName(), receivedData.getName());
    }

    private <T> HttpEntity<T> entity(T booking, String username) {
        HttpHeaders headers = authorizationHeader(username);
        return new HttpEntity<>(booking, headers);
    }
}
