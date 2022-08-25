package com.github.he305.contentaccountverifier.application.web.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class ContentAccountDtoTest {

    @Test
    void constructor_noArgs() {
        ContentAccountDto dto = new ContentAccountDto();
        assertNull(dto.getContentAccountName());
        assertNull(dto.getPlatform());
    }
}