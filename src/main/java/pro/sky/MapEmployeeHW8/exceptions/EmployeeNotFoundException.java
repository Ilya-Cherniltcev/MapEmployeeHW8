package pro.sky.MapEmployeeHW8.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // =====  ошибка 404 Not Found =======
public class EmployeeNotFoundException extends RuntimeException {
}
