package com.github.he305.contentaccountverifier.application.web.controller;

import com.github.he305.contentaccountverifier.application.web.dto.ContentAccountDto;
import com.github.he305.contentaccountverifier.domain.service.ContentAccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class ControllerTest {

    @Mock
    private ContentAccountService contentAccountService;

    @InjectMocks
    private Controller underTest;

    @Test
    void verify() {
        ContentAccountDto dto = new ContentAccountDto("name", "TWITCH");
        assertDoesNotThrow(() -> underTest.verify(dto));
    }
}