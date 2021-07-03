package io.aithal.dailymilkapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DmBadRequestException extends RuntimeException {
    public DmBadRequestException ( String message ) {
        super ( message );
    }
}
