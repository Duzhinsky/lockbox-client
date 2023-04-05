package ru.duzhinsky.lockbox.spring;

import java.lang.reflect.Field;
import java.util.Optional;
import org.springframework.util.ReflectionUtils.FieldCallback;
import ru.duzhinsky.lockbox.SecretPayloadManager;
import ru.duzhinsky.lockbox.model.secret.SecretPayload;
import yandex.cloud.sdk.auth.provider.CredentialProvider;

public class SecretFieldCallback implements FieldCallback {

    private final static String ERROR_FIELD_TYPE =
        "@Secret annotation is applicable only for SecretPayload fields";

    private final SecretPayloadManager payloadManager;

    private final CredentialProvider credentialProvider;

    private final Object bean;

    public SecretFieldCallback(
        SecretPayloadManager payloadManager,
        CredentialProvider credentialProvider,
        Object bean
    ) {
        this.payloadManager = payloadManager;
        this.credentialProvider = credentialProvider;
        this.bean = bean;
    }

    @Override
    public void doWith(Field field) throws IllegalAccessException {
        if (!field.isAnnotationPresent(Secret.class)) {
            return;
        }
        if (!field.getType().isAssignableFrom(SecretPayload.class)) {
            throw new IllegalArgumentException(ERROR_FIELD_TYPE);
        }

        boolean accessible = field.canAccess(bean);
        field.setAccessible(true);
        field.set(bean, getSecret(field));
        field.setAccessible(accessible);
    }

    private SecretPayload getSecret(Field field) {
        String iamToken = credentialProvider.get().getToken();
        String secretId = field.getAnnotation(Secret.class).value();
        return Optional.ofNullable(field.getAnnotation(Secret.class).version())
            .filter(s -> !s.isBlank())
            .map(versionId -> payloadManager.getPayload(iamToken, secretId, versionId))
            .orElseGet(() -> payloadManager.getPayload(iamToken, secretId));
    }
}
