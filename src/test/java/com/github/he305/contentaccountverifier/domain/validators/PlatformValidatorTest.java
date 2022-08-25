package com.github.he305.contentaccountverifier.domain.validators;

import com.github.he305.contentaccountverifier.domain.exceptions.PlatformNotSupportedException;
import com.github.he305.contentaccountverifier.domain.model.enums.Platform;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlatformValidatorTest {

    @Test
    void validate_valid() {
        List<String> validPlatforms = Arrays.stream(Platform.values())
                .map(Platform::name)
                .collect(Collectors.toList());

        validPlatforms.forEach(validPlatform -> {
                    String actual = PlatformValidator.validate(validPlatform);
                    assertEquals(validPlatform, actual);
                }
        );
    }

    @Test
    void validate_rawValid() {
        String rawString = "TWITCH";
        String actual = PlatformValidator.validate(rawString);
        assertEquals(rawString, actual);
    }

    @Test
    void validate_notValid_shouldThrow() {
        String platform = "Wrong platform";
        assertThrows(PlatformNotSupportedException.class, () -> PlatformValidator.validate(platform));
    }
}