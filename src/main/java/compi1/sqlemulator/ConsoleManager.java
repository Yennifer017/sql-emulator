package compi1.sqlemulator;

import compi1.sqlemulator.files.AdmiFiles;
import compi1.sqlemulator.lexer_parser.Lexer;
import compi1.sqlemulator.lexer_parser.parser;
import java.io.StringReader;
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

    private JTextPane console;
    private AdmiFiles admiFiles;

    public ConsoleManager(JTextPane console, AdmiFiles admiFiles) {
        this.console = console;
        this.admiFiles = admiFiles;
    }

    public DefaultStyledDocument getNewDoc() {
        return new DefaultStyledDocument() {
            //se sobrescribe el metodo para ingresar un string, agregando algo de codigo extra
            @Override
            public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
                super.insertString(offset, str, a);
                //srt == caracter ingresado
                //offset == posicion de donde se ingresa el caracter, su inicio
                if ( str.contains("\n")&& (str.contains(";") || console.getText().contains(";")) ) {
                    System.out.println("se ejecuto el metodo de la consola");
                    StringReader reader = new StringReader(console.getText());
                    Lexer lexer = new Lexer(reader);
                    parser parser = new parser(lexer);

                    try {
                        parser.parse();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("manejo de exception");
                    } 

                }
            }

            @Override
            public void remove(int offs, int len) throws BadLocationException {
                super.remove(offs, len);
            }
        };
    }
    

}
