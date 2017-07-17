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
@JsonDeserialize(as = ImmutableUser.class)
@JsonSerialize(as = ImmutableUser.class)
public interface User {

    public int id();

    public String name();

    public String email();

    public String username();

    public Company company();

//  "address": {
//    "street": "Kulas Light",
//    "suite": "Apt. 556",
//    "city": "Gwenborough",
//    "zipcode": "92998-3874",
//    "geo": {
//      "lat": "-37.3159",
//      "lng": "81.1496"
//    }
//  },
//  "phone": "1-770-736-8031 x56442",
//  "website": "hildegard.org",
}
