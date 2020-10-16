package ee.taltech.website.a_theory.question6.sheep.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SheepNotFoundException extends RuntimeException {
}
