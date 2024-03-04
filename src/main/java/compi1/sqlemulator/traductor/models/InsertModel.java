
package compi1.sqlemulator.traductor.models;

import compi1.sqlemulator.lexer_parser.Token;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author yenni
 */
@Getter @Setter @NoArgsConstructor
public class InsertModel extends AbsModel{
    private List<Token> columns;
    private List<Token> values;
}
