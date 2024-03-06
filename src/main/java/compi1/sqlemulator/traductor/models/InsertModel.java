
package compi1.sqlemulator.traductor.models;

import compi1.sqlemulator.lexer_parser.Token;
import compi1.sqlemulator.traductor.components.Column;
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
    private List<Column> columns;
    private List<Token> values;
}
