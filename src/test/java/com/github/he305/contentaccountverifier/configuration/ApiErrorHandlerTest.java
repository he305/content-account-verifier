package com.github.he305.contentaccountverifier.configuration;

import com.github.he305.contentaccountverifier.domain.exceptions.PlatformVerifierNotImplementedException;
import com.github.he305.contentaccountverifier.domain.model.enums.Platform;
import com.github.he305.contentaccountverifier.shared.api.ApiError;
import com.github.he305.contentaccountverifier.shared.exceptions.ContentAccountVerifierException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class ApiErrorHandlerTest {

    private final ApiErrorHandler underTest = new ApiErrorHandler();

    @Test
    void handleContentAccountVerifierException_internalServiceError() {
        PlatformVerifierNotImplementedException exception = new PlatformVerifierNotImplementedException(Platform.TWITCH);
        HttpStatus expected = HttpStatus.INTERNAL_SERVER_ERROR;

        ResponseEntity<Object> response = underTest.handleContentAccountVerifierException(exception);
        assertEquals(expected, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof ApiError);
        ApiError body = (ApiError) response.getBody();
        assertEquals(expected, body.getStatus());
    }

    @Test
    void handleContentAccountVerifierException_default() {
        String message = "mes";
        ContentAccountVerifierException exception = new ContentAccountVerifierException(message);
        HttpStatus expected = HttpStatus.BAD_REQUEST;
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);

        ResponseEntity<Object> response = underTest.handleContentAccountVerifierException(exception);
        assertEquals(expected, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof ApiError);
        ApiError body = (ApiError) response.getBody();
        assertEquals(expected, body.getStatus());
        long threshold = 1;
        long minutes = ChronoUnit.MINUTES.between(now, body.getTime());
        assertTrue(threshold > minutes);
        assertEquals(message, body.getMessage());
    }
}