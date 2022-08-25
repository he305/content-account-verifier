package com.github.he305.contentaccountverifier.application.services;

import com.github.he305.contentaccountverifier.domain.factories.VerifierServiceFactory;
import com.github.he305.contentaccountverifier.domain.model.enums.Platform;
import com.github.he305.contentaccountverifier.domain.service.VerifierService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContentAccountServiceImplTest {

    @Mock
    private VerifierServiceFactory verifierServiceFactory;

    @InjectMocks
    private ContentAccountServiceImpl underTest;

    @Test
    void verify() {
        String dataName = "name";
        String dataPlatform = "TWITCH";
        Platform platform = Platform.TWITCH;

        VerifierService service = mock(VerifierService.class);
        when(verifierServiceFactory.getService(platform)).thenReturn(service);
        assertDoesNotThrow(() -> underTest.verify(dataName, dataPlatform));
    }
}