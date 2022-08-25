package com.github.he305.contentaccountverifier.domain.model.values;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class ContentAccountNameTest {

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(ContentAccountName.class).verify();
    }
}