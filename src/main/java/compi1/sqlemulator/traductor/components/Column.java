
package compi1.sqlemulator.traductor.components;

import compi1.sqlemulator.lexer_parser.Token;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author yenni
 */
@Getter @Setter
public class Column {
    int position;
    Token token;

    public Column(Token token) {
        this.position = -1;
        this.token = token;
    }
    
    
    
}
