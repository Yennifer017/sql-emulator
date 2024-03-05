
package compi1.sqlemulator.files.models;

import java.io.File;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author yenni
 */
@Getter @Setter @AllArgsConstructor
public class FileProject{
    private File file; 
    private int identation; 
    
    @Override
    public String toString(){
        return this.file.getName();
    }
}
