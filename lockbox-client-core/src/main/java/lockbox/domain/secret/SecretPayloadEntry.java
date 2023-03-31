package lockbox.domain.secret;

import java.util.Optional;

public interface SecretPayloadEntry {

    String getKey();

    Optional<String> getTextValue();

    Optional<String> getBinaryValue();
}
