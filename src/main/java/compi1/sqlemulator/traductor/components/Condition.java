
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
public class Condition {

    public Condition(Token nameColumnTkn, int codeRelationalOp, Token comparableTkn) {
        this.nameColumnTkn = nameColumnTkn;
        this.codeRelationalOp = codeRelationalOp;
        this.comparableTkn = comparableTkn;
    }
    
    Token nameColumnTkn;
    int codeRelationalOp;
    Token comparableTkn;
    int numberColumn;
}
