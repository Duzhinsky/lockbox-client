package ru.duzhinsky.lockbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.duzhinsky.lockbox.rest.payload.RestSecretPayloadManager;
import yandex.cloud.sdk.auth.provider.CredentialProvider;

@Configuration
public class SecretAnnotationConfiguration {

    @Bean
    public SecretPayloadManager secretPayloadManager() {
        return new RestSecretPayloadManager();
    }

    @Bean
    public SecretAnnotationProcessor secretAnnotationProcessor(
        @Autowired SecretPayloadManager secretPayloadManager,
        @Autowired CredentialProvider credentialProvider
    ) {
        return new SecretAnnotationProcessor(secretPayloadManager, credentialProvider);
    }
}
