
package compi1.sqlemulator.traductor.models;

import compi1.sqlemulator.traductor.components.Asignation;
import compi1.sqlemulator.traductor.components.Filtro;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author yenni
 */
@Getter @Setter @NoArgsConstructor
public class UpdateModel extends AbsModel{
    private Filtro filtro;
    private List<Asignation> ColumsAsignations;
}
