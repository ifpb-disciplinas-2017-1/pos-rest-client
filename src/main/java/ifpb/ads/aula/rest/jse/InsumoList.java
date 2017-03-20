package ifpb.ads.aula.rest.jse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/02/2017, 08:25:56
 */
class InsumoList {

    private List<Insumo> list;

    public InsumoList() {
        list = new ArrayList<Insumo>();
    }

    public void add(Insumo p) {
        list.add(p);
    }
    public List<Insumo> todos(){
        return Collections.unmodifiableList(list);
    }
}
