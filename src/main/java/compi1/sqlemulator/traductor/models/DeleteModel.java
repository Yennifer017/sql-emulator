
package compi1.sqlemulator.traductor.models;

import compi1.sqlemulator.traductor.components.Filtro;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author yenni
 */
@Getter @Setter @NoArgsConstructor
public class DeleteModel extends AbsModel{
    private Filtro filtro;
    
}
