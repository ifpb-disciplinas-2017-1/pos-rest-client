package ifpb.ads.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 17/07/2017, 10:10:20
 */
@Value.Immutable
@JsonDeserialize(as = ImmutablePost.class)
@JsonSerialize(as = ImmutablePost.class)
public interface Post {

    public int userId();

    public int id();

    public String title();

    public String body();

    public static Post fake() {
        return new Post() {
            @Override
            public int userId() {
                return -1;
            }

            @Override
            public int id() {
                return -1;
            }

            @Override
            public String title() {
                return "";
            }

            @Override
            public String body() {
                return "";
            }
        };
    }

}
