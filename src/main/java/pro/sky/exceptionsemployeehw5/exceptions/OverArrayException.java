package pro.sky.exceptionsemployeehw5.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // =====  ошибка 500 Internal Server Error =======

public class OverArrayException extends RuntimeException {

}
