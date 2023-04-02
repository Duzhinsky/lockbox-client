package ru.duzhinsky.lockbox.domain.secret;

import java.util.List;

public interface SecretPayload {

   String getVersionId();

   List<? extends SecretPayloadEntry> getPayload();
}
