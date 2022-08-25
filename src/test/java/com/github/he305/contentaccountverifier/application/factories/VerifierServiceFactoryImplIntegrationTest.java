package com.github.he305.contentaccountverifier.application.factories;

import com.github.he305.contentaccountverifier.domain.model.enums.Platform;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class VerifierServiceFactoryImplIntegrationTest {
    @Autowired
    private VerifierServiceFactoryImpl underTest;

    @Test
    void testAllPlatformsSupport() {
        Arrays.stream(Platform.values()).forEach(platform ->
                assertDoesNotThrow(() -> underTest.getService(platform))
        );
    }
}
