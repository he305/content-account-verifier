package com.github.he305.contentaccountverifier.application.factories;

import com.github.he305.contentaccountverifier.domain.exceptions.PlatformVerifierNotImplementedException;
import com.github.he305.contentaccountverifier.domain.factories.VerifierServiceFactory;
import com.github.he305.contentaccountverifier.domain.model.enums.Platform;
import com.github.he305.contentaccountverifier.domain.service.VerifierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class VerifierServiceFactoryImpl implements VerifierServiceFactory {
    private final List<VerifierService> services;
    private final Map<Platform, VerifierService> serviceMap = new EnumMap<>(Platform.class);

    @PostConstruct
    void initServiceMap() {
        services.forEach(service ->
                serviceMap.put(service.getType(), service));
    }

    @Override
    public VerifierService getService(Platform platform) {
        VerifierService service = serviceMap.get(platform);
        if (service == null) {
            throw new PlatformVerifierNotImplementedException(platform);
        }

        return service;
    }
}
