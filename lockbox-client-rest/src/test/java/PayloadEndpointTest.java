import org.junit.jupiter.api.Test;
import ru.duzhinsky.lockbox.rest.payload.RestSecretPayloadManager;

public class PayloadEndpointTest {

    @Test
    public void test() {
        System.out.println(new RestSecretPayloadManager().getPayload(
            "",
            "e6qfbkgddldlckkmpdch",
            "e6qf2pc9obbofu2nad1j"
        ));
    }
}
