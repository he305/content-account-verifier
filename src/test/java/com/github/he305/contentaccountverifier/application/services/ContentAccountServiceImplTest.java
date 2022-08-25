package com.github.he305.contentaccountverifier.application.services;

import com.github.he305.contentaccountverifier.domain.exceptions.ContentAccountInvalidException;
import com.github.he305.contentaccountverifier.domain.exceptions.VerifierServiceException;
import com.github.he305.contentaccountverifier.domain.factories.VerifierServiceFactory;
import com.github.he305.contentaccountverifier.domain.model.enums.Platform;
import com.github.he305.contentaccountverifier.domain.model.values.ContentAccountName;
import com.github.he305.contentaccountverifier.domain.service.VerifierService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContentAccountServiceImplTest {

    @Mock
    private VerifierServiceFactory verifierServiceFactory;

    @InjectMocks
    private ContentAccountServiceImpl underTest;

    @Test
    void verify_valid() {
        String dataName = "name";
        String dataPlatform = "TWITCH";
        Platform platform = Platform.TWITCH;

        VerifierService service = mock(VerifierService.class);
        when(verifierServiceFactory.getService(platform)).thenReturn(service);
        assertDoesNotThrow(() -> underTest.verify(dataName, dataPlatform));
    }

    @Test
    void verify_verifierServiceException_shouldThrow() {
        String dataName = "name";
        String dataPlatform = "TWITCH";
        Platform platform = Platform.TWITCH;

        ContentAccountName contentAccountName = new ContentAccountName(dataName);
        VerifierService service = mock(VerifierService.class);
        doThrow(VerifierServiceException.class).when(service).verify(contentAccountName);
        when(verifierServiceFactory.getService(platform)).thenReturn(service);

        assertThrows(ContentAccountInvalidException.class, () ->
                underTest.verify(dataName, dataPlatform));
    }
}