
package compi1.sqlemulator.traductor.models;

import compi1.sqlemulator.lexer_parser.Token;
import compi1.sqlemulator.traductor.components.Filtro;
import java.util.List;
import lombok.Getter;

/**
 *
 * @author yenni
 */
@Getter
public class SelectModel extends AbsModel{
    private List<Token> columns;
    private Filtro filtro;
    
    public void setColumns(List<Token> columns){
        this.columns = columns;
    }
    
    public void setFiltro(Filtro filtro){
        this.filtro = filtro;
    }
    
}
