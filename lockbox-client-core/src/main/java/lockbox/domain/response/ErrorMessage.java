package lockbox.domain.response;

import java.util.List;

public interface ErrorMessage {

    int getCode();
    String getMessage();
    List<String> getDetails();
}
