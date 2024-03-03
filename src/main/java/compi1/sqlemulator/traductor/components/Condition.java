
package compi1.sqlemulator.traductor.components;

import compi1.sqlemulator.lexer_parser.Token;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author yenni
 */
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Condition {
    Token nameColumnTkn;
    int codeRelationalOp;
    Token comparableTkn;
}
