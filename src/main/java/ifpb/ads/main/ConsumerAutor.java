package ifpb.ads.main;

import ifpb.ads.model.Autor;
import ifpb.ads.model.ImmutableAutor;
import ifpb.ads.model.json.Mapper;
import ifpb.ads.model.json.MapperException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 03/08/2017, 10:56:29
 */
public class ConsumerAutor {

    private final Mapper mapper = new Mapper();
    private final String uri = "http://localhost:8080/reservas/api/autor";
    private final Client client = ClientBuilder.newClient();
    private final WebTarget root = client.target(uri);

    public static void main(String[] args) {
        ConsumerAutor consumer = new ConsumerAutor();
//        consumer.listarAutorComId(101);
//        consumer.listarTodosOsAutores();
//        consumer.cadastrarNovoAutor();
    }

    private void listarAutorComId(int id) {
        try {
            WebTarget target = root.path("{id}").resolveTemplate("id", id);
            Response response = target.request().get();
            String json = response.readEntity(String.class);
            Autor autor = mapper.toObject(json, Autor.class);
            System.out.println("autor = " + autor);
        } catch (MapperException ex) {
            Logger.getLogger(ConsumerAutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarTodosOsAutores() {
        try {
            Response response = root.request().get();
            String json = response.readEntity(String.class);
            List<Autor> autores = mapper.toList(json, Autor.class);
            autores.stream().forEach(System.out::println);
        } catch (MapperException ex) {
            Logger.getLogger(ConsumerAutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cadastrarNovoAutor() {
        try {
            Autor autor = ImmutableAutor
                    .builder()
                    .abreviacao("J.R. K")
                    .email("jrk@com.com")
                    .id(0)
                    .nome("João Roça King")
                    .build();
            
            String json = mapper.toString(autor);
            Entity<String> entity = Entity.json(json);
            Response response = root.request().post(entity);

            Autor autorResposta = mapper.toObject(response.readEntity(String.class),
                    Autor.class);
            System.out.println("autorResposta = " + autorResposta);
            System.out.println(response.getLocation());
        } catch (MapperException ex) {
            Logger.getLogger(ConsumerAutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
