package ifpb.ads.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 03/08/2017, 10:51:21
 */
@Value.Immutable
@JsonDeserialize(as = ImmutableLivro.class)
@JsonSerialize(as = ImmutableLivro.class)
public interface Livro {

    public String descricao();
    public String edicao();
    public String titulo();
    public int id();
//    @JsonProperty(required = false)
    public int codigo();
}
//{
//  "descricao": "Melhor livro Java",
//  "edicao": "primeira edicao",
//  "titulo": "Java como programar"
//}
