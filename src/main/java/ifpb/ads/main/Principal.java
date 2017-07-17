package ifpb.ads.main;

import ifpb.ads.model.Comment;
import ifpb.ads.model.ImmutablePost;
import ifpb.ads.model.Post;
import ifpb.ads.model.User;
import ifpb.ads.model.json.Mapper;
import ifpb.ads.model.json.MapperException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 17/07/2017, 08:00:48
 */
public class Principal {

    private final Mapper mapper = new Mapper();

    public static void main(String[] args) {

        Principal principal = new Principal();
        principal.createPost();
        principal.getPosts();
        principal.getComments();
        principal.getUserOne();
        principal.getCommentsByPostId();
        principal.postToPosts();
        principal.putToPosts();
        principal.patchToPosts();

    }

    private void createPost() {
        Post post = ImmutablePost
                .builder()
                .body("afljasjfdkla")
                .id(1)
                .title("fssf")
                .userId(2)
                .build();

        System.out.println(post);

        Post empty = Post.fake();
    }

    public void postToPosts() {
        try {
            String recurso = "https://jsonplaceholder.typicode.com/posts";
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(recurso);

            Post body = ImmutablePost
                    .builder()
                    .body("afljasjfdkla")
                    .id(0)
                    .title("fssf")
                    .userId(2)
                    .build();

            Entity<String> json = Entity.json(mapper.toString(body));

            Response response = target.request().post(json);

            System.out.println("response.getStatus: " + response.getStatus());
            System.out.println("body: " + response.readEntity(String.class));
            response.getHeaders().forEach((t, u) -> {
                System.out.println(t + "-" + u);
            });
            //Location: "https://jsonplaceholder.typicode.com/posts/101";
        } catch (MapperException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void putToPosts() {
        try {
            String recurso = "https://jsonplaceholder.typicode.com/posts";
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(recurso).path("{id}");

            int id = 1;
            Post body = ImmutablePost
                    .builder()
                    .body("afljasjfdkla")
                    .id(id)
                    .title("fssf")
                    .userId(2)
                    .build();

            Entity<String> json = Entity.json(mapper.toString(body));

            Response response = target.resolveTemplate("id", id).request().put(json);

            System.out.println("response.getStatus: " + response.getStatus());
            System.out.println("body: " + response.readEntity(String.class));
            response.getHeaders().forEach((t, u) -> {
                System.out.println(t + "-" + u);
            });

            //Location: "https://jsonplaceholder.typicode.com/posts/101";
        } catch (MapperException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void patchToPosts() {
        String recurso = "https://jsonplaceholder.typicode.com/posts";
        //https://stackoverflow.com/questions/22355235/patch-request-using-jersey-client
        Client client = ClientBuilder.newClient()
                .property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);

        WebTarget target = client.target(recurso).path("{id}");
        int id = 1;
        Entity<String> json = Entity.json("{\"userId\": 21,\"id\": 1}");
        Response response = target.resolveTemplate("id", id).request().build("PATCH", json).invoke();
        System.out.println("response.getStatus: " + response.getStatus());
        System.out.println("body: " + response.readEntity(String.class));
    }

    public void getPosts() {
        try {
//            https://jsonplaceholder.typicode.com/posts/{id}
//            https://jsonplaceholder.typicode.com/posts/{id}/comments
            String recurso = "https://jsonplaceholder.typicode.com/posts";
            Client client = ClientBuilder.newClient();
            WebTarget target = client
                    .target(recurso); // "https://jsonplaceholder.typicode.com/posts";
//                .path("{id}") //  "https://jsonplaceholder.typicode.com/posts/{id}";
//                .resolveTemplate("id", 1);
            Response get = target.request().get();
            String json = get.readEntity(String.class);
            Logger.getLogger(Principal.class.getName()).log(Level.INFO, mapper.toList(json).toString());
        } catch (MapperException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getComments() {
        try {
            String recurso = "https://jsonplaceholder.typicode.com/comments";
            Client client = ClientBuilder.newClient();
            WebTarget target = client
                    .target(recurso)
                    .path("{id}")
                    .resolveTemplate("id", 1);
            Response get = target.request().get();
            String json = get.readEntity(String.class);
            Logger.getLogger(Principal.class.getName()).log(Level.INFO, mapper.toObject(json, Comment.class).toString());
        } catch (MapperException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getUserOne() {
        try {
            String recurso = "https://jsonplaceholder.typicode.com/users";
            Client client = ClientBuilder.newClient();
            WebTarget target = client
                    .target(recurso)
                    .path("{id}")
                    .resolveTemplate("id", 1);
            Response get = target.request().get();
            String json = get.readEntity(String.class);
            Logger.getLogger(Principal.class.getName()).log(Level.INFO, mapper.toObject(json, User.class).toString());
        } catch (MapperException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getCommentsByPostId() {
//        String recurso = "https://jsonplaceholder.typicode.com/comments?postId={postId}";
        try {
            String recurso = "https://jsonplaceholder.typicode.com";
            Client client = ClientBuilder.newClient();
            WebTarget target = client
                    .target(recurso)
                    .path("comments")
                    .queryParam("postId", 1);
            Response get = target.request().get();
            String json = get.readEntity(String.class);
            Logger.getLogger(Principal.class.getName()).log(Level.INFO, mapper.toList(json).toString());
        } catch (MapperException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
