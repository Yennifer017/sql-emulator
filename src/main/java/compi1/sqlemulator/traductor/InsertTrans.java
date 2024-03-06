
package compi1.sqlemulator.traductor;

import com.opencsv.exceptions.CsvValidationException;
import compi1.sqlemulator.exceptions.DirectoryException;
import compi1.sqlemulator.exceptions.InvalidColumnException;
import compi1.sqlemulator.exceptions.InvalidDataException;
import compi1.sqlemulator.files.AdmiFiles;
import compi1.sqlemulator.lexer_parser.Token;
import compi1.sqlemulator.lexer_parser.sym;
import compi1.sqlemulator.traductor.components.PathProject;
import compi1.sqlemulator.traductor.models.AbsModel;
import compi1.sqlemulator.traductor.models.InsertModel;
import compi1.sqlemulator.traductor.util.Index;
import compi1.sqlemulator.traductor.util.SeparatorElements;
import java.io.IOException;
import java.util.List;
import javax.swing.text.BadLocationException;

/**
 *
 * @author yenni
 */
public class InsertTrans extends TranslatorStm{
    
    public InsertTrans(AdmiFiles admiFiles, List<String> semanticErrosList){
        super.semanticErrors = semanticErrosList;
        super.separator =  new SeparatorElements();
        super.admiFiles = admiFiles;
        super.errorMss = ERROR_MSS + "insert";
    }

    @Override
    public String translate(List<Token> tokens, Index index) {
        InsertModel model = (InsertModel) this.separateTkns(tokens, index);
        try {
            admiFiles.openFile(model.getPath().getPathWithDots());
            if(model.getColumns()==null){
                insertAll(model);
            }else{
                admiFiles.getCSVinterpretor().setPosColumns(
                        model.getColumns(), semanticErrors);
                return "Aun no implementado, actualizacion en la v 1.2";
            }
            return "Insercion exitosa";
        } catch (DirectoryException | IOException ex) {
            semanticErrors.add("El archivo " + model.getPath().getPathWithDots() + ", linea:"
                + model.getPath().getLine() + ", col:" + model.getPath().getCol() + " no existe");
            return errorMss;
        } catch (CsvValidationException ex){
            semanticErrors.add("No se pudo leer correctamente el archivo" + model.getPath().getPathWithDots() 
                    + ", asegurate que no este corrompido");
            return errorMss;
        } catch (InvalidDataException ex){
            semanticErrors.add(ex.getMessage());
            return errorMss;
        } catch (BadLocationException ex) {
            return "Error inesperado, intentalo otra vez :|";
        } catch (InvalidColumnException ex) {
            return errorMss;
        } catch (IndexOutOfBoundsException ex){
            semanticErrors.add("Ocurrio un error con los datos del archivo, " 
                    + model.getPath().getPathWithDots() + "asegurate de que este bien estructurado :/");
            return errorMss;
        }
    }
    
    private void insertAll(InsertModel model) 
            throws IOException, CsvValidationException, InvalidDataException, BadLocationException{
        if(admiFiles.getCSVinterpretor().getTotalColumns() == model.getValues().size()){
            String content = admiFiles.getCSVinterpretor().endsCorrectly() ? "" : "\n";
            for (int i = 0; i < model.getValues().size(); i++) {
                //para quitarle las comillas a los strings
                if(model.getValues().get(i).getType() == sym.CADENA){
                    content += model.getValues().get(i).getLexem().toString()
                            .replace("\"", "");
                }else{
                    content += model.getValues().get(i).getLexem().toString();
                }
                //para agregar coma o salto de linea
                if(i!= model.getValues().size() - 1){
                    content += ",";
                }else{
                    content += "\n";
                }
            }
            admiFiles.appendContent(content);
        }else{ //si no concuerda con las columnas
            throw new InvalidDataException("Las columnas no son suficientes para realizar la insercion");
        }
    }
    
    private void insertByColumns(InsertModel model) throws BadLocationException{
        String content = admiFiles.getCSVinterpretor().endsCorrectly() ? "" : "\n";
        
        admiFiles.appendContent(content);
    }

    @Override
    protected AbsModel separateTkns(List<Token> tokens, Index index) {
        index.increment(2); //para pasar de INSERTAR EN
        InsertModel model = new InsertModel();
        
        Token firstTkn = tokens.get(index.getNum());
        model.setPath(new PathProject(separator.separatePath(tokens, index),
                firstTkn.getColumn(), firstTkn.getLine()));
        if(tokens.get(index.getNum()).getType() == sym.PARENTESIS_L){
            index.increment(); //para pasar el parentesis
            model.setColumns(separator.separateColumns(tokens, index));
            index.increment(); //para pasar el otro parentesis
        }
        index.increment(2); //para pasar de VALORES (
        model.setValues(separator.separateValues(tokens, index));
        index.increment(2); //para pasar de );
        return model;
    }

}
