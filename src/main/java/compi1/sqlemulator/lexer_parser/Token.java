
package compi1.sqlemulator.lexer_parser;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author yenni
 */
@Getter @Setter
public class Token {
    private Object lexem;
    private int line, column, type;

    public Token(Object lexem, int line, int column, int type) {
        this.lexem = lexem;
        this.line = line;
        this.column = column;
        this.type = type;
    }

    public Token(int line, int column, int type) {
        this.line = line;
        this.column = column;
        this.type = type;
    }
    
    
}
