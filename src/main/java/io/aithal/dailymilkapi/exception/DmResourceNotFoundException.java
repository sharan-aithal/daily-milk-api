package io.aithal.dailymilkapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DmResourceNotFoundException extends Exception {
    public DmResourceNotFoundException ( String message ) {
        super ( message );
    }
}
