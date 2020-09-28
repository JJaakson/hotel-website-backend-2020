package ee.taltech.website.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRoomException extends RuntimeException {

    public InvalidRoomException() {
    }

    public InvalidRoomException(String message) {
        super(message);
    }
}
