package lockbox.model;

import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class SecretPayloadEntry {

    private final String key;

    private final Optional<String> textValue;

    private final Optional<String> binaryValue;

    public SecretPayloadEntry(
        String key,
        Optional<String> textValue,
        Optional<String> binaryValue
    ) {
        this.key = key;
        this.textValue = textValue;
        this.binaryValue = binaryValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SecretPayloadEntry that = (SecretPayloadEntry) o;
        return key.equals(that.key) && textValue.equals(that.textValue) && binaryValue.equals(
            that.binaryValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, textValue, binaryValue);
    }
}
