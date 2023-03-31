package lockbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class SecretPayload {

    @JsonProperty("versionId")
    private final String version;

    @JsonProperty("entries")
    private final List<SecretPayloadEntry> payload;

    public SecretPayload(String version, List<SecretPayloadEntry> payload) {
        this.version = version;
        this.payload = payload;
    }

    public String getVersion() {
        return version;
    }

    public List<SecretPayloadEntry> getPayload() {
        return payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SecretPayload that = (SecretPayload) o;
        return version.equals(that.version) && payload.equals(that.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, payload);
    }
}
