package ifpb.ads.main;

import java.io.IOException;
import java.util.Base64;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 07/08/2017, 10:54:04
 */
public class ClientFilterAuthorized implements ClientRequestFilter {

    private final String user;
    private final String pass;

    public ClientFilterAuthorized(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    @Override
    public void filter(ClientRequestContext crc) throws IOException {
        crc.getHeaders().putSingle("Authorization", headers());
    }

    private String headers() {
        return "Basic " + encode();
    }

    private String encode() {
        String token = user.concat(":").concat(pass);
        return Base64.getEncoder().encodeToString(token.getBytes());
    }

}
