package pro.sky.MapEmployeeHW8.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)   // =====   ошибка 400 Bad Request =======
public class AlreadyExistsException extends RuntimeException {
}
