package ru.duzhinsky.lockbox.model.secret;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class SecretPayload {

   String versionId;

   List<SecretPayloadEntry> payload;
}
