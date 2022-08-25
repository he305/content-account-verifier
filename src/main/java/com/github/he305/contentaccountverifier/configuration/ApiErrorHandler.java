package com.github.he305.contentaccountverifier.configuration;

import com.github.he305.contentaccountverifier.domain.exceptions.PlatformVerifierNotImplementedException;
import com.github.he305.contentaccountverifier.shared.api.ApiError;
import com.github.he305.contentaccountverifier.shared.exceptions.ContentAccountVerifierException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@ControllerAdvice
public class ApiErrorHandler {
    @ExceptionHandler(value = {ContentAccountVerifierException.class})
    public ResponseEntity<Object> handleContentAccountVerifierException(ContentAccountVerifierException ex) {
        final HttpStatus status;
        if (ex instanceof PlatformVerifierNotImplementedException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }

        ApiError error = new ApiError(
                ex.getMessage(),
                status,
                LocalDateTime.now(ZoneOffset.UTC)
        );
        return new ResponseEntity<>(error, status);
    }
}
