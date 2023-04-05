package ru.duzhinsky.lockbox.model.secret;

import java.util.List;
import lombok.Value;

@Value
public class SecretPayload {

   String versionId;

   List<SecretPayloadEntry> payload;
}
