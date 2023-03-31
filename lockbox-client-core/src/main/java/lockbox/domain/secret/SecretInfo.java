package lockbox.domain.secret;

public interface SecretInfo {

    String getId();

    String getFolderId();

    String getCreatedAt();

    String getName();

    String getDescription();

    String getLabels();

    String getKmsKeyId();

    String getStatus();

    SecretVersionId getCurrentVersion();

    boolean isDeletionProtected();
}
