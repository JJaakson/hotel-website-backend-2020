package ee.taltech.website.common;

import ee.taltech.website.dto.RoomDto;
import ee.taltech.website.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class RestTemplateTests {
    public static final ParameterizedTypeReference<List<RoomDto>> ROOMS_LIST = new ParameterizedTypeReference<>() {
    };
    public static final Class<RoomDto> ROOM = RoomDto.class;

    @Autowired
    protected TestRestTemplate testRestTemplate;
    @Autowired
    protected JwtUtil jwtUtil;


    public <T> T assertOk(ResponseEntity<T> exchange) {
        assertNotNull(exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        return exchange.getBody();
    }

    protected HttpHeaders authorizationHeader(String username) {
        HttpHeaders headers = new HttpHeaders();
        System.out.println(username);
        System.out.println(jwtUtil);
        headers.set("Authorization", "Bearer " + jwtUtil.createTokenForTests(username));
        return headers;
    }
}
