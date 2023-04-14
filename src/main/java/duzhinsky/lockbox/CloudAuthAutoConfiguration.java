package duzhinsky.lockbox;

import java.nio.file.Paths;
import java.util.Optional;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yandex.cloud.sdk.auth.Auth;
import yandex.cloud.sdk.auth.jwt.ServiceAccountKey;
import yandex.cloud.sdk.auth.provider.CredentialProvider;
import yandex.cloud.sdk.auth.provider.OauthCredentialProvider;

@Configuration
@EnableConfigurationProperties(CloudAuthAutoConfiguration.CloudAuthProperties.class)
public class CloudAuthAutoConfiguration {

    @Data
    @ConfigurationProperties(prefix = "lockbox.auth")
    public static class CloudAuthProperties {

        Optional<String> iamToken = Optional.empty();

        Optional<String> oauthToken = Optional.empty();

        Optional<String> oauthTokenPath = Optional.empty();

        Optional<ServiceAccountKey> apiKey = Optional.empty();

        Optional<String> apiKeyPath = Optional.empty();
    }

    @Bean
    @ConditionalOnMissingBean
    public CredentialProvider credentialProvider(CloudAuthProperties properties) {
        return properties.getIamToken().map(
                iam -> Auth.iamTokenBuilder()
                .token(iam)
                .build()
            ).or(
                () -> properties.getOauthToken().map(oauth -> OauthCredentialProvider.builder()
                    .fromEnv(oauth)
                    .build())
            ).or(
                () -> properties.getOauthTokenPath().map(oauthPath -> OauthCredentialProvider.builder()
                    .fromFile(Paths.get(oauthPath))
                    .build())
            )
            .or(
                () -> properties.getApiKey().map(apiKey -> Auth.apiKeyBuilder()
                    .serviceAccountKey(apiKey).build())
            )
            .or(
                () -> properties.getApiKeyPath().map(apiKeyPath -> Auth.apiKeyBuilder()
                    .fromFile(Paths.get(apiKeyPath)).build())
            )
            .or(
                () -> properties.getIamToken().map(iam -> Auth.iamTokenBuilder()
                    .token(iam)
                    .build())
            )
            .orElseGet(() -> Auth.computeEngineBuilder().build());
    }
}
