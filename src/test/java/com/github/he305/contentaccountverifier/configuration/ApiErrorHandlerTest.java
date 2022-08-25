package com.github.he305.contentaccountverifier.configuration;

import com.github.he305.contentaccountverifier.domain.exceptions.PlatformVerifierNotImplementedException;
import com.github.he305.contentaccountverifier.domain.model.enums.Platform;
import com.github.he305.contentaccountverifier.shared.api.ApiError;
import com.github.he305.contentaccountverifier.shared.exceptions.ContentAccountVerifierException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
        ContentAccountVerifierException exception = new ContentAccountVerifierException("mes");
        HttpStatus expected = HttpStatus.BAD_REQUEST;

        ResponseEntity<Object> response = underTest.handleContentAccountVerifierException(exception);
        assertEquals(expected, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof ApiError);
        ApiError body = (ApiError) response.getBody();
        assertEquals(expected, body.getStatus());
    }
}