/* codigo de usuario */
package compi1.sqlemulator.lexer;

import java_cup.runtime.*;

%% //separador de area

/* opciones y declaraciones de jflex */

%class Lexer
%cup
%line
%column

LineTerminator = \r|\n|\r\n

WhiteSpace = {LineTerminator} | [ \t\f]

/* constants */
DecIntegerLiteral = 0 | [1-9][0-9]*
Identifier = [:jletter:] [:jletterdigit:]*
StringLiteral = '\"'.'\"';
FloatLiteral = DecIntegerLiteral '.' DecIntegerLiteral;
%{
  
  private Symbol symbol(int type) {
    return new Symbol(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline+1, yycolumn+1, value);
  }


  private void error(String message) {
    System.out.println("Error en linea line "+(yyline+1)+", columna "+(yycolumn+1)+" : "+message);
  }
%}

%% // separador de areas

/* reglas lexicas */

  /*palabras reservadas*/
	  "SELECCIONAR"   { return symbol(sym.SELECCIONAR); }
    "FILTRAR"       { return symbol(sym.FILTRAR); }
    "INSERTAR"      { return symbol(sym.INSERTAR); }
    "ACTUALIZAR"    { return symbol(sym.ACTUALIZAR); }
    "ASIGNAR"       { return symbol(sym.ASIGNAR); }
    "ELIMINAR"      { return symbol(sym.ELIMINAR); }
    "EN"            { return symbol(sym.EN); }
    "VALORES"       { return symbol(sym.VALORES); }

  /*simbolos reservados*/
    "."             { return symbol(sym.PUNTO); }
    "*"             { return symbol(sym.ASTERISCO); }
    "("             { return symbol(sym.PARENTESIS_L); }
    ")"             { return symbol(sym.PARENTESIS_R); }
    ","             { return symbol(sym.COMA); }
    ";"             { return symbol(sym.FIN_INSTRUCCION); }

  /*Operadores relacionales*/
    "="             { return symbol(sym.IGUAL); }
    ">"             { return symbol(sym.MAYOR_QUE); }
    "<"             { return symbol(sym.MENOR_QUE); }
    ">="            { return symbol(sym.MAYOR_IGUAL_QUE); }
    "<="            { return symbol(sym.MENOR_IGUAL_QUE); }
    "<>"            { return symbol(sym.DIFERENTE); }

  /*Operadores logicos*/
    "AND"           { return symbol(sym.AND); }
    "OR"            { return symbol(sym.OR); }

	/*otras variables*/
    {DecIntegerLiteral}	{ return symbol(sym.ENTERO, new Integer(yytext()));}
    {FloatLiteral}	    { return symbol(sym.DECIMAL, new Float(yytext()));}
    {StringLiteral}	    { return symbol(sym.CADENA, new String(yytext()));}
    {Identifier}	      { return symbol(sym.IDENTIFICADOR);}
    
  /*lo ignorado*/
	  {WhiteSpace} 	      {/* ignoramos */}

  /* error fallback */
    [^]  {error("Simbolo invalido <"+ yytext()+">");}
    <<EOF>>   { return symbol(sym.EOF); }
