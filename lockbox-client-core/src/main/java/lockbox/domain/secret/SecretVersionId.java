package lockbox.domain.secret;

public interface SecretVersionId {

    public String getId();

    public String getSecretId();

    public String getCreatedAt();

    public String getDestroyAt();

    public String getDescription();

    public String getStatus();
}
