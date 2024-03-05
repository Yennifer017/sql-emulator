package compi1.sqlemulator.traductor;

import compi1.sqlemulator.exceptions.InvalidDataException;
import compi1.sqlemulator.lexer_parser.sym;
import compi1.sqlemulator.traductor.components.Condition;
import compi1.sqlemulator.traductor.components.Filtro;

/**
 *
 * @author yenni
 */
public class FilterTraductor {

    public static final String EMPTY_COLUM = "null";

    private boolean validate(String value, Condition condition) throws InvalidDataException {
        if (value.isBlank() || value.isEmpty() || value.equals(EMPTY_COLUM)) {
            return false;
        } else {
            switch (condition.getCodeRelationalOp()) {
                case sym.IGUAL, sym.DIFERENTE:
                    String comparable = condition.getComparableTkn().getLexem().toString();
                    if (condition.getComparableTkn().getType() == sym.CADENA) {
                        comparable = comparable.replace("\"", "");
                    }
                    return this.validateCompleateType(value, comparable,
                            condition.getCodeRelationalOp());
                case sym.MAYOR_IGUAL_QUE, sym.MENOR_QUE, sym.MENOR_IGUAL_QUE, sym.MAYOR_QUE:
                    try {
                    float valueNumber = Float.parseFloat(value);
                    float comparableNumber = Float.parseFloat(
                            condition.getComparableTkn().getLexem().toString());
                    return validateNumberType(valueNumber, comparableNumber,
                            condition.getCodeRelationalOp());
                } catch (NumberFormatException e) {
                    throw new InvalidDataException("Los tipos de datos de comparacion las "
                            + "columnas no concuerdan con los del archivo");
                }
            }
            return false;
        }
    }

    private boolean validateNumberType(float value, float comparable, int codeRelational) {
        return switch (codeRelational) {
            case sym.MAYOR_IGUAL_QUE ->
                value >= comparable;
            case sym.MAYOR_QUE ->
                value > comparable;
            case sym.MENOR_IGUAL_QUE ->
                value <= comparable;
            case sym.MENOR_QUE ->
                value < comparable;
            default ->
                false;
        };
    }

    private boolean validateCompleateType(String value, String comparable, int codeRelational) {
        return switch (codeRelational) {
            case sym.IGUAL ->
                value.equals(comparable);
            case sym.DIFERENTE ->
                !value.equals(comparable);
            default ->
                false;
        };
    }

    public boolean validateFilters(Filtro filter, String[] currentLine) throws InvalidDataException {
        boolean result = false;
        switch (filter.getCodeLogicRelational()) {
            case sym.AND -> {
                for (int i = 0; i < filter.getConditions().size(); i++) {
                    result = this.validate(
                            currentLine[filter.getConditions().get(i).getNumberColumn()],
                            filter.getConditions().get(i));
                    if (result == false) {
                        break;
                    }
                }
            }
            default -> {//sym.Or //no relational operator
                for (int i = 0; i < filter.getConditions().size(); i++) {
                    result = this.validate(
                            currentLine[filter.getConditions().get(i).getNumberColumn()],
                            filter.getConditions().get(i));
                    if (result == true) {
                        break;
                    }
                }
            }
        }
        return result;
    }

}
