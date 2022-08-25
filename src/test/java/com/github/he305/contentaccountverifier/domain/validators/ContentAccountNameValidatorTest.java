package com.github.he305.contentaccountverifier.domain.validators;

import com.github.he305.contentaccountverifier.domain.exceptions.ContentAccountNameInvalidException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ContentAccountNameValidatorTest {

    @Test
    void validate_valid() {
        String expected = "some name";
        String actual = ContentAccountNameValidator.validate(expected);
        assertEquals(expected, actual);
    }

    @Test
    void validate_notValid_shouldThrow() {
        List<String> invalidStrings = List.of(
                "",
                "   ",
                " "
        );

        invalidStrings.forEach(invalidString ->
                assertThrows(ContentAccountNameInvalidException.class, () -> ContentAccountNameValidator.validate(invalidString))
        );
    }
}