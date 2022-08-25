package com.github.he305.contentaccountverifier.application.services;

import com.github.he305.contentaccountverifier.application.utls.NetworkUtils;
import com.github.he305.contentaccountverifier.domain.model.enums.Platform;
import com.github.he305.contentaccountverifier.domain.model.values.ContentAccountName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class YoutubeVerifierServiceImplTest {

    @Mock
    private NetworkUtils networkUtils;

    @InjectMocks
    private YoutubeVerifierServiceImpl underTest;

    @Test
    void verify_valid() {
        ContentAccountName name = new ContentAccountName("UCs9_O1tRPMQTHQ-N_L6FU2g");
        assertDoesNotThrow(() -> underTest.verify(name));
    }

    @Test
    void getType() {
        Platform expected = Platform.YOUTUBE;
        Platform actual = underTest.getType();
        assertEquals(expected, actual);
    }
}