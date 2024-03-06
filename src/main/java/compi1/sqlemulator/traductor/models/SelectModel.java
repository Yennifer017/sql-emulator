
package compi1.sqlemulator.traductor.models;

import compi1.sqlemulator.traductor.components.Column;
import compi1.sqlemulator.traductor.components.Filtro;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author yenni
 */
@Getter @Setter
public class SelectModel extends AbsModel{
    private List<Column> columns;
    private Filtro filtro;
}
