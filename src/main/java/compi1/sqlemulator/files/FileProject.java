
package compi1.sqlemulator.files;

import java.awt.Button;
import java.io.File;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author yenni
 */
@Getter @Setter @AllArgsConstructor
public class FileProject extends Button{
    private File file; 
    private int identation; 
}
