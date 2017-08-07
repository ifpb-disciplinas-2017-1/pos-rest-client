package ifpb.ads.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.annotation.Priority;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.Response;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 07/08/2017, 11:07:33
 */
@Priority(value = 1)
public class ClientResposeNotAuthorized implements ClientResponseFilter {

    @Override
    public void filter(ClientRequestContext request,
            ClientResponseContext response) throws IOException {
        if (Response.Status.UNAUTHORIZED.getStatusCode() == response.getStatus()) {
            readBuffer(response);
            request.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private void readBuffer(ClientResponseContext response) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntityStream()))) {
            reader.lines().forEach(System.out::println);
        }
    }

//    private void readBuffer(ClientResponseContext response) throws IOException {
//        InputStream inputStream = response.getEntityStream();
//        byte[] bytes = new byte[inputStream.available()];
//        inputStream.read(bytes);
//        String responseContent = new String(bytes);
//        System.out.println("responseContent = " + responseContent);
//    }

}
