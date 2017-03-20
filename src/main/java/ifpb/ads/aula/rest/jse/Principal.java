package ifpb.ads.aula.rest.jse;

import com.thoughtworks.xstream.XStream;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/02/2017, 08:00:48
 */
public class Principal {

    public static void main(String[] args) {
        String recurso = "http://localhost:8080/aula-rest/api/produto";

//        get(recurso);
//        getParam(recurso);
//        post(recurso);
        postQuery(recurso);
    }

    private static void getParam(String recurso) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(recurso).path("{id}");

        Response get = target
                .resolveTemplate("id", 1)
                .request()
                .get();
        System.out.println(get.getStatus());
        System.out.println(get.readEntity(String.class));

    }

    private static void get(String recurso) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(recurso);
        Response get = target.request().get();
        String body = get.readEntity(String.class);
        /*
        <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
        <produtoes>
        <produto>
        <nome>tv</nome>
        </produto>
        <produto>
        <nome>caneta</nome>
        </produto>
        </produtoes>
         */

        XStream xStream = new XStream();
        xStream.alias("produto", Insumo.class);
        xStream.alias("produtoes", InsumoList.class);

        xStream.addImplicitCollection(InsumoList.class, "list");

        InsumoList fromXML = (InsumoList) xStream.fromXML(body);
        fromXML.todos().forEach(System.out::println);
        long count = fromXML.todos().parallelStream()
                .filter((t) -> {
                    return t.getNome().contains("");
                }).count();

    }

    private static void postQuery(String recurso) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(recurso);

        Form form = new Form("nomeProduto", "lapis");

        Response post = target.queryParam("id", 0)
                .request()
                .post(Entity.form(form));

        String readEntity = post.readEntity(String.class);

        parseTo(readEntity)
                .forEach(insumo -> System.out.println(
                insumo.getNome()));
    }

    private static void post(String recurso) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(recurso).path("{id}");
        Form form = new Form("nomeProduto", "lapis");

        Response post = target.resolveTemplate("id", 0)
                .request()
                .post(Entity.form(form));

        String readEntity = post.readEntity(String.class);

        parseTo(readEntity)
                .forEach(insumo -> System.out.println(insumo.getNome()));
    }

    private static List<Insumo> parseTo(String xml) {
        XStream xStream = new XStream();
        xStream.alias("produto", Insumo.class);
        xStream.alias("produtoes", InsumoList.class);

        xStream.addImplicitCollection(InsumoList.class, "list");

        InsumoList fromXML = (InsumoList) xStream.fromXML(xml);
        return fromXML.todos();

    }
}
