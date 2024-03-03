
package compi1.sqlemulator.traductor.components;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author yenni
 */
@Getter @Setter @AllArgsConstructor
public class PathProject {
    private String pathWithDots;
    private int col;
    private int line;
}
