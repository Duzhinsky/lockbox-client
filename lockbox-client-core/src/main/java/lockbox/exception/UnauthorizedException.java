package lockbox.exception;

public class UnauthorizedException extends LockboxException {

    private final String iamToken;

    public UnauthorizedException(String iamToken) {
        super(String.format("IAM token %s was not authorized", iamToken));
        this.iamToken = iamToken;
    }

    public String getIamToken() {
        return iamToken;
    }
}
