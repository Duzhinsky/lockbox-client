package duzhinsky.lockbox.model;

import com.google.protobuf.ByteString;
import java.util.Optional;
import lombok.Value;
import yandex.cloud.api.lockbox.v1.PayloadOuterClass;

@Value
public class SecretPayloadEntry {

    String key;

    TextOrBinary value;

    @Value
    public static class TextOrBinary {

        Optional<String> text;

        Optional<ByteString> binary;

        @Override
        public String toString() {
            return text.or(() -> binary.map(ByteString::toStringUtf8)).orElse("");
        }
    }

    public static SecretPayloadEntry fromGrpc(PayloadOuterClass.Payload.Entry grpcEntry) {
        TextOrBinary value = null;
        if(grpcEntry.hasTextValue()) {
            value = new TextOrBinary(Optional.of(grpcEntry.getTextValue()), Optional.empty());
        } else {
            value = new TextOrBinary(Optional.empty(), Optional.of(grpcEntry.getBinaryValue()));
        }
        return new SecretPayloadEntry(grpcEntry.getKey(), value);
    }
}
