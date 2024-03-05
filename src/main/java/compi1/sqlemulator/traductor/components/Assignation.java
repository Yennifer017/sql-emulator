
package compi1.sqlemulator.traductor.components;

import compi1.sqlemulator.lexer_parser.Token;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author yenni
 */
@Getter @Setter @NoArgsConstructor
public class Assignation {
    private Token column;
    private Token value;
    private int positionColumn;
    public Assignation(Token column, Token value) {
        this.column = column;
        this.value = value;
    }
    
}
