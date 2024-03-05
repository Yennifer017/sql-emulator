
package compi1.sqlemulator.traductor.components;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 *
 * @author yenni
 */
@Getter
public class Filtro {
    public static int NO_LOGIC_RELATIONAL = -10;
    int codeLogicRelational;
    List<Condition>  conditions;
    
    public Filtro(){
        codeLogicRelational = NO_LOGIC_RELATIONAL;
        conditions = new ArrayList<>();
    }
    
    public void addConditon(Condition condition){
        conditions.add(condition);
    }
    
    public void setCodeLogicRelational(int code){
        codeLogicRelational = code;
    }
    
}
