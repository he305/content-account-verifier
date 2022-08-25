package com.github.he305.contentaccountverifier.shared.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ApiError {
    private String message;
    private HttpStatus status;
    private LocalDateTime time;
}
