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
@JsonDeserialize(as = ImmutableComment.class)
@JsonSerialize(as = ImmutableComment.class)
public interface Comment {

    public int postId();

    public int id();

    public String name();

    public String email();

    public String body();

}
