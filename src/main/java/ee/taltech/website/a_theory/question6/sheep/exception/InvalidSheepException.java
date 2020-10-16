package ee.taltech.website.a_theory.question6.sheep.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidSheepException extends RuntimeException{

    public InvalidSheepException() {
    }

    public InvalidSheepException(String message) {
        super(message);
    }
}
