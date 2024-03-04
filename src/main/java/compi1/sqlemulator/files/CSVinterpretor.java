
package compi1.sqlemulator.files;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import compi1.sqlemulator.lexer_parser.Token;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import javax.swing.JTextPane;

/**
 *
 * @author yenni
 */
public class CSVinterpretor {
    private JTextPane displayContent;

    public CSVinterpretor(JTextPane displayContent) {
        this.displayContent = displayContent;
    }
    
    public String[] getFirstLine() throws IOException, CsvValidationException{
        String csvTxt = displayContent.getText().replace("\t", "");
        StringReader reader = new StringReader(csvTxt);
        CSVReader csvReader = new CSVReader(reader);
        String[] firstLine = csvReader.readNext();
        csvReader.close();
        return firstLine;
    }
    
    public int getTotalColumns() throws IOException, CsvValidationException{
        return this.getFirstLine().length;
    }
    
    public int[] getPosColums(List<Token> tokens, List<String> errorsList ){
        return null;
    }
    
}
