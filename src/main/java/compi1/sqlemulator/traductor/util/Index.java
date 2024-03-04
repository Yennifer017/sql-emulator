
package compi1.sqlemulator.traductor.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author yenni
 */
@Getter @Setter @AllArgsConstructor
public class Index {
    private int num;
    
    public void increment(){
        num++;
    }
    public void restart(){
        num = 0;
    }
    public void increment(int num){
        this.num += num;
    }
}
