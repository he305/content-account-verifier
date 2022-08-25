package com.github.he305.contentaccountverifier.domain.model.values;

import lombok.Value;

@Value
public class ContentAccountName {
    String name;

    @Override
    public String toString() {
        return name;
    }
}
