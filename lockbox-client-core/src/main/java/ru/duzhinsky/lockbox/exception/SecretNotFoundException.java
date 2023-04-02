package ru.duzhinsky.lockbox.exception;

import java.util.Optional;

public class SecretNotFoundException extends LockboxException {

    private final String secretId;

    private final Optional<String> versionId;

    public SecretNotFoundException(String secretId, String versionId) {
        super(String.format("Secret %s was not found", secretId));
        this.versionId = Optional.ofNullable(versionId);
        this.secretId = secretId;
    }

    public SecretNotFoundException(String secretId) {
        this(secretId, null);
    }

    public String getSecretId() {
        return secretId;
    }

    public Optional<String> getVersionId() {
        return versionId;
    }
}
