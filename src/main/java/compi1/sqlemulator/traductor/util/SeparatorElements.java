package compi1.sqlemulator.traductor.util;

import compi1.sqlemulator.lexer_parser.Token;
import compi1.sqlemulator.lexer_parser.sym;
import compi1.sqlemulator.traductor.components.Assignation;
import compi1.sqlemulator.traductor.components.Condition;
import compi1.sqlemulator.traductor.components.Filtro;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yenni
 */
public class SeparatorElements {

    public String separatePath(List<Token> tokens, Index currentPos) {
        String path = "";
        int typeTkn = tokens.get(currentPos.getNum()).getType();
        while (typeTkn == sym.IDENTIFICADOR || typeTkn == sym.PUNTO) {
            path += tokens.get(currentPos.getNum()).getLexem().toString();
            currentPos.increment();
            typeTkn = tokens.get(currentPos.getNum()).getType();
        }
        return path;
    }

    public List<Token> separateColumns(List<Token> tokens, Index currentPos) {
        List<Token> columns = new ArrayList<>();
        int typeTkn = tokens.get(currentPos.getNum()).getType();
        while (typeTkn == sym.IDENTIFICADOR || typeTkn == sym.COMA || typeTkn == sym.ASTERISCO) {
            if (typeTkn == sym.IDENTIFICADOR) {
                columns.add(tokens.get(currentPos.getNum()));
            }
            currentPos.increment();
            typeTkn = tokens.get(currentPos.getNum()).getType();
        }
        return columns;
    }
    
    public List<Token> separateValues(List<Token> tokens, Index currentPos){
        List<Token> values = new ArrayList<>();
        int typeTkn = tokens.get(currentPos.getNum()).getType();
        while (typeTkn == sym.CADENA || typeTkn == sym.COMA || typeTkn == sym.ENTERO 
                || typeTkn == sym.DECIMAL) {
            if (typeTkn != sym.COMA) {
                values.add(tokens.get(currentPos.getNum()));
            }
            currentPos.increment();
            typeTkn = tokens.get(currentPos.getNum()).getType();
        }
        return values;
    }

    public Filtro separateConditions(List<Token> tokens, Index currentPos) {
        Filtro filtro = new Filtro();
        int auxiliarIndex = 1;
        boolean firstTime = true;
        Condition currentCondition = new Condition();
        int typeTkn = tokens.get(currentPos.getNum()).getType();
        while (typeTkn != sym.FIN_INSTRUCCION) {
            if (auxiliarIndex == 4) {
                filtro.addConditon(currentCondition);
                currentCondition = new Condition();
                if (firstTime) {
                    firstTime = false;
                    filtro.setCodeLogicRelational(typeTkn);
                }
                auxiliarIndex = 1;
                currentPos.increment(); //para que se "reinicie el ciclo"
            }
            switch (auxiliarIndex) {
                case 1 ->
                    currentCondition.setNameColumnTkn(tokens.get(currentPos.getNum()));
                case 2 ->
                    currentCondition.setCodeRelationalOp(
                            tokens.get(currentPos.getNum()).getType());
                case 3 ->
                    currentCondition.setComparableTkn(tokens.get(currentPos.getNum()));
            }
            auxiliarIndex++;
            currentPos.increment();
            typeTkn = tokens.get(currentPos.getNum()).getType();
        }
        filtro.addConditon(currentCondition);
        return filtro;
    }
    
    
    public List<Assignation> separateColAsign(List<Token> tokens, Index currentPos) {
        List<Assignation> asignations = new ArrayList<>();
        int auxiliarIndex = 1;
        Assignation currentAsignation = new Assignation();
        int typeTkn = tokens.get(currentPos.getNum()).getType();
        while (typeTkn != sym.FIN_INSTRUCCION && typeTkn != sym.FILTRAR) {
            if (auxiliarIndex == 4) {
                asignations.add(currentAsignation);
                currentAsignation = new Assignation();
                auxiliarIndex = 1;
                currentPos.increment(); //para que se "reinicie el ciclo"
            }
            switch (auxiliarIndex) {
                case 1 ->
                    currentAsignation.setColumn(tokens.get(currentPos.getNum()));
                case 3 ->
                    currentAsignation.setValue(tokens.get(currentPos.getNum()));
            }
            auxiliarIndex++;
            currentPos.increment();
            typeTkn = tokens.get(currentPos.getNum()).getType();
        }
        asignations.add(currentAsignation);
        return asignations;
    }

}
