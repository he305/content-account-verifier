package com.github.he305.contentaccountverifier.domain.model;

import com.github.he305.contentaccountverifier.domain.model.enums.Platform;
import com.github.he305.contentaccountverifier.domain.model.values.ContentAccountName;
import com.github.he305.contentaccountverifier.domain.validators.ContentAccountNameValidator;
import com.github.he305.contentaccountverifier.domain.validators.PlatformValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ContentAccount {
    private final ContentAccountName name;
    private final Platform platform;

    public ContentAccount(String name, String strPlatform) {
        String rawContentAccountName = ContentAccountNameValidator.validate(name);
        String rawPlatform = PlatformValidator.validate(strPlatform);
        this.name = new ContentAccountName(rawContentAccountName);
        this.platform = Platform.valueOf(rawPlatform);
    }

    @Override
    public String toString() {
        return "ContentAccount{" +
                "name=" + name.toString() +
                ", platform=" + platform +
                '}';
    }
}
