/* codigo de usuario */
package compi1.sqlemulator.lexer_parser;

import java_cup.runtime.*;
import java.util.*;

%% //separador de area

/* opciones y declaraciones de jflex */

%public
%unicode
%class Lexer
%cup
%line
%column

LineTerminator = \r|\n|\r\n 

WhiteSpace = {LineTerminator} | [ \t\f]

/* constants */
L=[a-zA-Z_]+
DecIntegerLiteral = 0 | [1-9][0-9]*
Identifier = [:jletter:] ( [:jletterdigit:] | "-" | "_" | "@" | "+" | "*" | "#" )*


%{
  /*--------------------------------------------
    CODIGO PARA EL MANEJO DE ERRORES
  ----------------------------------------------*/
    private List<String> errorsList;
    private List<Token> tokenList;

    public void init(){
        errorsList = new LinkedList<>();
        tokenList = new LinkedList<>();
    }

    public void reset(){
        errorsList.clear();
        tokenList.clear();
    }

    public List<String> getErrors(){
        return this.errorsList;
    }

    public List<Token> getTokens(){
        return this.tokenList;
    }

    /*--------------------------------------------
        CODIGO PARA EL PARSER
    ----------------------------------------------*/
    private Symbol symbol(int type) {
        tokenList.add(new Token(yyline+1, yycolumn+1, type));
        return new Symbol(type, yyline+1, yycolumn+1);
    }

    private Symbol symbol(int type, Object value) {
        tokenList.add(new Token(value, yyline+1, yycolumn+1, type));
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }

    private Symbol error(String message) {
        //System.out.println("Error en la linea: " + (yyline+1) + ", columna: " + (yycolumn+1) + " : "+message);
        errorsList.add("Error en la linea: " + (yyline+1) + ", columna: " + (yycolumn+1) + " : "+message);
        return new Symbol(sym.LEX_ERROR, yyline+1, yycolumn+1);
    }

%}

%% // separador de areas

/* reglas lexicas */

  /*palabras reservadas*/
    "SELECCIONAR"   { return symbol(sym.SELECCIONAR, yytext() ); }
    "FILTRAR"       { return symbol(sym.FILTRAR, yytext()); }
    "INSERTAR"      { return symbol(sym.INSERTAR, yytext()); }
    "ACTUALIZAR"    { return symbol(sym.ACTUALIZAR, yytext()); }
    "ASIGNAR"       { return symbol(sym.ASIGNAR, yytext()); }
    "ELIMINAR"      { return symbol(sym.ELIMINAR, yytext()); }
    "EN"            { return symbol(sym.EN, yytext()); }
    "VALORES"       { return symbol(sym.VALORES, yytext()); }

  /*simbolos reservados*/
    "."             { return symbol(sym.PUNTO, yytext()); }
    "*"             { return symbol(sym.ASTERISCO, yytext()); }
    "("             { return symbol(sym.PARENTESIS_L, yytext()); }
    ")"             { return symbol(sym.PARENTESIS_R, yytext()); }
    ","             { return symbol(sym.COMA, yytext()); }
    ";"             { return symbol(sym.FIN_INSTRUCCION, yytext()); }

  /*Operadores relacionales*/
    "="             { return symbol(sym.IGUAL, yytext()); }
    ">"             { return symbol(sym.MAYOR_QUE, yytext()); }
    "<"             { return symbol(sym.MENOR_QUE, yytext()); }
    ">="            { return symbol(sym.MAYOR_IGUAL_QUE, yytext()); }
    "<="            { return symbol(sym.MENOR_IGUAL_QUE, yytext()); }
    "<>"            { return symbol(sym.DIFERENTE, yytext()); }

  /*Operadores logicos*/
    "AND"           { return symbol(sym.AND, yytext()); }
    "OR"            { return symbol(sym.OR, yytext()); }

  /*otras variables*/
    {DecIntegerLiteral}                 { return symbol(sym.ENTERO, new Integer(yytext()));}
    {DecIntegerLiteral}"."{DecIntegerLiteral}   { return symbol(sym.DECIMAL, new Float(yytext()));}
    \"{L}({L}|{DecIntegerLiteral})*\"   { return symbol(sym.CADENA, new String(yytext()));}
    {Identifier}                        { return symbol(sym.IDENTIFICADOR, yytext() );}
     
  /*lo ignorado*/
    {WhiteSpace} 	      {/* ignoramos */}

  /* error fallback */
    .               { return error("Simbolo invalido <"+ yytext()+">");}
    <<EOF>>         { return symbol(sym.EOF); }
