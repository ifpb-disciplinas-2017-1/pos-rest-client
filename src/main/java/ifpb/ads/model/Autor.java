package ifpb.ads.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 03/08/2017, 10:51:21
 */
@Value.Immutable
@JsonDeserialize(as = ImmutableAutor.class)
@JsonSerialize(as = ImmutableAutor.class)
public interface Autor {

    public String abreviacao();
    public String email();
    public String nome();
    public int id();
}
//{
//  "abreviacao": "M.M",
//  "email": "madruga@org.com",
//  "id": 51,
//  "nome": "Seu Madruga"
//}
