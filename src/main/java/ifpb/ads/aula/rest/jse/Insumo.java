package ifpb.ads.aula.rest.jse;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/02/2017, 08:09:59
 */
@XmlRootElement
public class Insumo  implements Serializable{

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Insumo(String nome) {
        this.nome = nome;
    }

    public Insumo() {
    }

    @Override
    public String toString() {
        return this.nome;
    }

}
