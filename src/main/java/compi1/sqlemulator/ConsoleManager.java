package compi1.sqlemulator;

import compi1.sqlemulator.files.AdmiFiles;
import compi1.sqlemulator.lexer_parser.Lexer;
import compi1.sqlemulator.lexer_parser.parser;
import compi1.sqlemulator.traductor.Translator;
import compi1.sqlemulator.traductor.util.DisplayUtil;
import java.io.StringReader;
import java.util.List;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;

/**
 *
 * @author yenni
 */
public class ConsoleManager {

    //se almacena el estilo actual, el default
    private final StyleContext cont = StyleContext.getDefaultStyleContext();
    private final String MSS_FOLLOWING_ERRORS = "[borrar a partir de esta linea para que se vuelva a ejecutar]\n";

    private JTextPane console;
    private boolean runningByUser;
    private Translator translator;
    private DisplayUtil displayUtil;

    public ConsoleManager(JTextPane console, AdmiFiles admiFiles) {
        this.console = console;
        this.runningByUser = true;
        this.translator = new Translator(admiFiles);
        this.displayUtil = new DisplayUtil();
    }

    public DefaultStyledDocument getNewDoc() {
        return new DefaultStyledDocument() {
            //se sobrescribe el metodo para ingresar un string, agregando algo de codigo extra
            @Override
            public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
                super.insertString(offset, str, a);
                //srt == lo ingresado
                //offset == posicion de donde se ingresa el caracter, su inicio
                if (str.contains("\n") && (str.contains(";") || console.getText().contains(";"))
                        && (!console.getText().contains(MSS_FOLLOWING_ERRORS)) && runningByUser) {
                    translateSteps(str);
                }
            }
            @Override
            public void remove(int offs, int len) throws BadLocationException {
                super.remove(offs, len);
            }
        };
    }

    private void translateSteps(String str) {
        if (str.contains("\n") && (str.contains(";") || console.getText().contains(";"))
                && (!console.getText().contains(MSS_FOLLOWING_ERRORS)) && runningByUser) {
            StringReader reader = new StringReader(console.getText());
            Lexer lexer = new Lexer(reader);
            lexer.init();

            parser parser = new parser(lexer);

            try {
                parser.parse();
                if (!lexer.getErrors().isEmpty() || !parser.getSyntaxErrors().isEmpty()) {
                    runningByUser = false;
                    String content = "\n" + MSS_FOLLOWING_ERRORS;
                    content += showErrors("ERRORES LEXICOS", lexer.getErrors());
                    content += showErrors("ERRORES SINTACTICOS", parser.getSyntaxErrors());
                    displayUtil.appendText(console, content);
                    runningByUser = true;
                } else { //cuando se puede realizar alguna accion
                    runningByUser = false;
                    String mss = translator.translateCode(lexer.getTokens());
                    if(!translator.getSemanticErros().isEmpty()){
                        mss += "\n" + MSS_FOLLOWING_ERRORS;
                        mss += showErrors("ERRORES SEMANTICOS", translator.getSemanticErros());
                    }
                    runningByUser = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("manejo de exception");
            }
        }
    }

    private String showErrors(String mss, List<String> errors) {
        String content = "--------------------------------------------\n" + mss + "\n";
        if (errors.isEmpty()) {
            content += "     __ningun_error__\n";
        } else {
            for (int i = 0; i < errors.size(); i++) {
                content += errors.get(i);
                content += "\n";
            }
        }
        return content;
    }

}
