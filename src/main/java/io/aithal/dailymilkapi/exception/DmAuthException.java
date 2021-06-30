package io.aithal.dailymilkapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class DmAuthException extends RuntimeException {
    public DmAuthException ( String message ) {
        super ( message );
    }
}
