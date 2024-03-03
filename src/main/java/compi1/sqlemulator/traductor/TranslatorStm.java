
package compi1.sqlemulator.traductor;

import compi1.sqlemulator.files.AdmiFiles;
import compi1.sqlemulator.lexer_parser.Token;
import compi1.sqlemulator.traductor.models.AbsModel;
import compi1.sqlemulator.traductor.util.Index;
import compi1.sqlemulator.traductor.util.SeparatorElements;
import java.util.List;

/**
 *
 * @author yenni
 */
public abstract class TranslatorStm {
    protected List<String> semanticErrors;
    
    protected SeparatorElements separator;
    protected AdmiFiles admiFiles;

    public abstract String translate(List<Token> tokens, Index index);
    protected abstract AbsModel separateTkns(List<Token> tokens, Index index);
}
