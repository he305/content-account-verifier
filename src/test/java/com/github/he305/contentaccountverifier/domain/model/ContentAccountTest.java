package com.github.he305.contentaccountverifier.domain.model;

import com.github.he305.contentaccountverifier.domain.exceptions.ContentAccountNameInvalidException;
import com.github.he305.contentaccountverifier.domain.exceptions.PlatformNotSupportedException;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContentAccountTest {

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(ContentAccount.class).verify();
    }

    @Test
    void constructor_valid() {
        String name = "name";
        String rawPlatform = "TWITCH";

        assertDoesNotThrow(() -> new ContentAccount(name, rawPlatform));
    }

    @Test
    void constructor_badName() {
        String name = "";
        String rawPlatform = "TWITCH";

        assertThrows(ContentAccountNameInvalidException.class, () -> new ContentAccount(name, rawPlatform));
    }

    @Test
    void constructor_bacPlatform() {
        String name = "name";
        String rawPlatform = "";

        assertThrows(PlatformNotSupportedException.class, () -> new ContentAccount(name, rawPlatform));
    }

    @Test
    void test_toString() {
        String name = "name";
        String rawPlatform = "TWITCH";
        String expected = "ContentAccount{name=name, platform=TWITCH}";

        ContentAccount contentAccount = new ContentAccount(name, rawPlatform);
        String actual = contentAccount.toString();
        assertEquals(expected, actual);
    }
}