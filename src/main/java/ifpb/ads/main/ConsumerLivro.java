package ifpb.ads.main;

import ifpb.ads.model.Autor;
import ifpb.ads.model.ImmutableAutor;
import ifpb.ads.model.ImmutableLivro;
import ifpb.ads.model.Livro;
import ifpb.ads.model.json.Mapper;
import ifpb.ads.model.json.MapperException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 03/08/2017, 10:56:29
 */
public class ConsumerLivro {

    private final Mapper mapper = new Mapper();
    private final String uri = "http://localhost:8080/reservas/api/livro";
    private final Client client = ClientBuilder.newClient();
    private final WebTarget root = client.target(uri);

    public static void main(String[] args) {
        ConsumerLivro consumer = new ConsumerLivro();
//        consumer.listarLivroComId(101);
//        consumer.listarTodosOsAutores();
        consumer.cadastrarNovoLivro();
    }

    private void listarLivroComId(int id) {
//        try {
//            WebTarget target = root.path("{id}").resolveTemplate("id", id);
//            Response response = target.request().get();
//            String json = response.readEntity(String.class);
//            Autor autor = mapper.toObject(json, Autor.class);
//            System.out.println("autor = " + autor);
//        } catch (MapperException ex) {
//            Logger.getLogger(ConsumerLivro.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private void listarTodosOsLivros() {
//        try {
//            Response response = root.request().get();
//            String json = response.readEntity(String.class);
//            List<Autor> autores = mapper.toList(json, Autor.class);
//            autores.stream().forEach(System.out::println);
//        } catch (MapperException ex) {
//            Logger.getLogger(ConsumerLivro.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private void cadastrarNovoLivro() {
//        try {
//            Livro livro = ImmutableLivro
//                    .builder()
//                    .codigo(0)
//                    .descricao("Novo Livro")
//                    .edicao("primeira")
//                    .id(0)
//                    .titulo("Que livro massa!")
//                    .build();
//            String json = mapper.toString(livro);            
            
            Form form = new Form()
                    .param("codigo", "0")
                    .param("descricao", "Novo Livro")
                    .param("edicao", "primeira")
                    .param("id", "0")
                    .param("titulo", "Que livro massa!");
                    

            Entity<Form> entity = Entity.form(form);
            Response response = root.request().post(entity);
            String jsonResposta = response.readEntity(String.class);
//            Livro livroResposta = mapper.toObject(jsonResposta,Livro.class);
//            System.out.println("livroResposta = " + livroResposta);
            System.out.println(response.getLocation());
//        } catch (MapperException ex) {
//            Logger.getLogger(ConsumerLivro.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
