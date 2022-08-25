package com.github.he305.contentaccountverifier.application.factories;

import com.github.he305.contentaccountverifier.domain.exceptions.PlatformVerifierNotImplementedException;
import com.github.he305.contentaccountverifier.domain.model.enums.Platform;
import com.github.he305.contentaccountverifier.domain.service.VerifierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VerifierServiceFactoryImplTest {

    @Mock
    private VerifierService service;
    private VerifierServiceFactoryImpl underTest;

    @BeforeEach
    void setUp() {
        when(service.getType()).thenReturn(Platform.TWITCH);
        List<VerifierService> serviceList = List.of(
                service
        );

        underTest = new VerifierServiceFactoryImpl(serviceList);
        underTest.initServiceMap();
    }

    @Test
    void getService_valid() {
        Platform platform = Platform.TWITCH;

        VerifierService actual = underTest.getService(platform);
        assertEquals(service, actual);
    }

    @Test
    void getService_notImplemented_shouldThrow() {
        Platform platform = Platform.GOODGAME;
        assertThrows(PlatformVerifierNotImplementedException.class, () ->
                underTest.getService(platform));
    }
}