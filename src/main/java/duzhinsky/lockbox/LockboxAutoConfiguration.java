package duzhinsky.lockbox;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yandex.cloud.sdk.auth.provider.CredentialProvider;

@Configuration
public class LockboxAutoConfiguration {

    @Bean("lockbox")
    @ConditionalOnMissingBean
    public PayloadService payloadService(
        CredentialProvider credentialProvider,
        @Value("${lockbox.duration}") Duration timeout
    ) {
        return new PayloadService(credentialProvider, timeout);
    }
}
