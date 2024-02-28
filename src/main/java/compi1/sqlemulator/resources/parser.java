
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package compi1.sqlemulator.parser;

import java_cup.runtime.*;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class parser extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return sym.class;
}

  /** Default constructor. */
  @Deprecated
  public parser() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\062\000\002\002\004\000\002\002\003\000\002\002" +
    "\003\000\002\002\003\000\002\002\003\000\002\002\003" +
    "\000\002\002\003\000\002\003\003\000\002\003\003\000" +
    "\002\003\003\000\002\005\003\000\002\005\003\000\002" +
    "\005\003\000\002\005\003\000\002\005\003\000\002\006" +
    "\010\000\002\007\013\000\002\010\011\000\002\011\007" +
    "\000\002\035\003\000\002\035\003\000\002\012\004\000" +
    "\002\017\004\000\002\017\002\000\002\013\004\000\002" +
    "\020\004\000\002\020\002\000\002\014\003\000\002\014" +
    "\002\000\002\021\004\000\002\022\004\000\002\024\003" +
    "\000\002\024\003\000\002\025\005\000\002\033\005\000" +
    "\002\033\002\000\002\026\005\000\002\034\005\000\002" +
    "\034\002\000\002\023\005\000\002\027\003\000\002\027" +
    "\003\000\002\030\004\000\002\031\004\000\002\031\002" +
    "\000\002\015\005\000\002\015\002\000\002\016\006\000" +
    "\002\032\003\000\002\032\002" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\132\000\014\003\013\004\012\006\010\007\006\011" +
    "\007\001\002\000\004\002\ufff5\001\002\000\004\002\ufff4" +
    "\001\002\000\004\012\122\001\002\000\004\012\116\001" +
    "\002\000\004\012\077\001\002\000\004\002\076\001\002" +
    "\000\006\014\016\030\021\001\002\000\004\002\ufff3\001" +
    "\002\000\004\002\ufff7\001\002\000\004\002\ufff6\001\002" +
    "\000\004\012\uffee\001\002\000\004\012\uffed\001\002\000" +
    "\004\012\025\001\002\000\010\012\uffea\016\uffea\032\022" +
    "\001\002\000\004\030\021\001\002\000\006\012\uffec\016" +
    "\uffec\001\002\000\006\012\uffeb\016\uffeb\001\002\000\004" +
    "\030\026\001\002\000\016\005\uffe7\010\uffe7\013\073\015" +
    "\uffe7\017\uffe7\031\uffe7\001\002\000\006\005\031\017\uffe5" +
    "\001\002\000\004\017\uffe6\001\002\000\004\030\034\001" +
    "\002\000\004\017\033\001\002\000\004\002\ufff2\001\002" +
    "\000\016\020\063\021\056\022\061\023\057\024\060\025" +
    "\064\001\002\000\004\017\uffe4\001\002\000\006\026\041" +
    "\027\043\001\002\000\004\017\uffe2\001\002\000\004\017" +
    "\uffe3\001\002\000\004\030\034\001\002\000\004\017\uffe1" +
    "\001\002\000\004\030\034\001\002\000\006\017\uffdb\027" +
    "\046\001\002\000\004\017\uffdd\001\002\000\004\030\034" +
    "\001\002\000\006\017\uffdb\027\046\001\002\000\004\017" +
    "\uffdc\001\002\000\006\017\uffde\026\052\001\002\000\004" +
    "\030\034\001\002\000\004\017\uffe0\001\002\000\006\017" +
    "\uffde\026\052\001\002\000\004\017\uffdf\001\002\000\012" +
    "\030\000\033\000\034\000\035\000\001\002\000\012\030" +
    "\ufffd\033\ufffd\034\ufffd\035\ufffd\001\002\000\012\030\ufffc" +
    "\033\ufffc\034\ufffc\035\ufffc\001\002\000\012\030\ufffe\033" +
    "\ufffe\034\ufffe\035\ufffe\001\002\000\012\030\067\033\072" +
    "\034\070\035\071\001\002\000\012\030\uffff\033\uffff\034" +
    "\uffff\035\uffff\001\002\000\012\030\ufffb\033\ufffb\034\ufffb" +
    "\035\ufffb\001\002\000\010\017\uffd9\026\uffd9\027\uffd9\001" +
    "\002\000\010\017\uffda\026\uffda\027\uffda\001\002\000\010" +
    "\017\uffd8\026\uffd8\027\uffd8\001\002\000\010\017\ufff8\026" +
    "\ufff8\027\ufff8\001\002\000\010\017\ufffa\026\ufffa\027\ufffa" +
    "\001\002\000\010\017\ufff9\026\ufff9\027\ufff9\001\002\000" +
    "\004\030\026\001\002\000\014\005\uffe9\010\uffe9\015\uffe9" +
    "\017\uffe9\031\uffe9\001\002\000\014\005\uffe8\010\uffe8\015" +
    "\uffe8\017\uffe8\031\uffe8\001\002\000\004\002\001\001\002" +
    "\000\004\030\026\001\002\000\006\015\101\031\uffd3\001" +
    "\002\000\004\030\021\001\002\000\004\031\103\001\002" +
    "\000\004\015\104\001\002\000\002\001\002\000\006\016" +
    "\uffd5\032\111\001\002\000\004\016\107\001\002\000\004" +
    "\017\110\001\002\000\004\002\ufff1\001\002\000\002\001" +
    "\002\000\004\016\uffd7\001\002\000\004\016\uffd6\001\002" +
    "\000\004\016\115\001\002\000\004\031\uffd4\001\002\000" +
    "\004\030\026\001\002\000\006\005\031\017\uffe5\001\002" +
    "\000\004\017\121\001\002\000\004\002\uffef\001\002\000" +
    "\004\030\026\001\002\000\004\010\124\001\002\000\004" +
    "\030\126\001\002\000\006\005\031\017\uffe5\001\002\000" +
    "\004\020\127\001\002\000\002\001\002\000\010\005\uffd0" +
    "\017\uffd0\030\126\001\002\000\006\005\uffd1\017\uffd1\001" +
    "\002\000\006\005\uffd2\017\uffd2\001\002\000\004\017\134" +
    "\001\002\000\004\002\ufff0\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\132\000\014\005\010\006\013\007\014\010\003\011" +
    "\004\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\006\012\016\035\017\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\004\017\022\001\001" +
    "\000\004\012\023\001\001\000\002\001\001\000\002\001" +
    "\001\000\004\013\026\001\001\000\004\020\073\001\001" +
    "\000\006\014\031\021\027\001\001\000\002\001\001\000" +
    "\006\022\034\023\035\001\001\000\002\001\001\000\002" +
    "\001\001\000\004\002\061\001\001\000\002\001\001\000" +
    "\010\024\037\025\036\026\041\001\001\000\002\001\001" +
    "\000\002\001\001\000\004\023\050\001\001\000\002\001" +
    "\001\000\004\023\043\001\001\000\004\034\044\001\001" +
    "\000\002\001\001\000\004\023\046\001\001\000\004\034" +
    "\047\001\001\000\002\001\001\000\004\033\052\001\001" +
    "\000\004\023\053\001\001\000\002\001\001\000\004\033" +
    "\054\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\006\003" +
    "\064\027\065\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\004" +
    "\013\074\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\004\013\077\001\001\000\004\015\101" +
    "\001\001\000\004\012\113\001\001\000\002\001\001\000" +
    "\002\001\001\000\006\004\104\030\105\001\001\000\004" +
    "\031\111\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\006\004\104\030\112\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\004\013\116\001\001\000\006\014\117\021\027" +
    "\001\001\000\002\001\001\000\002\001\001\000\004\013" +
    "\122\001\001\000\002\001\001\000\004\016\124\001\001" +
    "\000\006\014\132\021\027\001\001\000\002\001\001\000" +
    "\004\004\127\001\001\000\006\016\130\032\131\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}



    // Connect this parser to a scanner!
    public Parser(Lexer lex) {
	    super(lex);
	}

private Lexer lexer = new Lexer();

public Lexer getLexer() {
    return lexer;
}

public void syntax_error(Symbol cur_token) {
            System.out.println("Simbolo con error:" + symbl_name_from_id(cur_token.sym));
            System.out.println("Linea " + cur_token.left);
            System.out.println("Columna " + cur_token.right);
            if (expected_token_ids().isEmpty()) {
                System.out.println("ya no se esperaba ningun simbolo");
            }
        }
/*
public void unrecovered_syntax_error(Symbol cur_token) {
            System.out.println("Error irrecuperable sobrecargado");
        }*/


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$parser$actions {
  private final parser parser;

  /** Constructor */
  CUP$parser$actions(parser parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$parser$do_action_part00000000(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$parser$result;

      /* select the action based on the action number */
      switch (CUP$parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= instruccion EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		RESULT = start_val;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$parser$parser.done_parsing();
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // relational_operator ::= MAYOR_QUE 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("relational_operator",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // relational_operator ::= IGUAL 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("relational_operator",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // relational_operator ::= MENOR_QUE 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("relational_operator",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // relational_operator ::= MAYOR_IGUAL_QUE 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("relational_operator",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // relational_operator ::= MENOR_IGUAL_QUE 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("relational_operator",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // relational_operator ::= DIFERENTE 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("relational_operator",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // values ::= CADENA 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("values",1, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // values ::= ENTERO 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("values",1, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // values ::= DECIMAL 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("values",1, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // instruccion ::= select_stm 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("instruccion",3, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // instruccion ::= insert_stm 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("instruccion",3, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // instruccion ::= update_stm 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("instruccion",3, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // instruccion ::= delete_stm 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("instruccion",3, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // instruccion ::= error 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("instruccion",3, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // select_stm ::= SELECCIONAR selects EN path filtros_opt FIN_INSTRUCCION 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("select_stm",4, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-5)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // insert_stm ::= INSERTAR EN path columns_opt VALORES PARENTESIS_L insertables PARENTESIS_R FIN_INSTRUCCION 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("insert_stm",5, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-8)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // update_stm ::= ACTUALIZAR EN path ASIGNAR asignations filtros_opt FIN_INSTRUCCION 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("update_stm",6, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-6)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // delete_stm ::= ELIMINAR EN path filtros_opt FIN_INSTRUCCION 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("delete_stm",7, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // selects ::= ASTERISCO 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("selects",27, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // selects ::= columns 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("selects",27, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // columns ::= IDENTIFICADOR more_columns 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("columns",8, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // more_columns ::= COMA columns 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("more_columns",13, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // more_columns ::= 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("more_columns",13, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // path ::= IDENTIFICADOR carpetas 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("path",9, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25: // carpetas ::= PUNTO path 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("carpetas",14, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 26: // carpetas ::= 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("carpetas",14, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 27: // filtros_opt ::= filtros 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("filtros_opt",10, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 28: // filtros_opt ::= 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("filtros_opt",10, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 29: // filtros ::= FILTRAR conditions 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("filtros",15, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 30: // conditions ::= condition logic 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("conditions",16, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 31: // logic ::= and_stm 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("logic",18, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 32: // logic ::= or_stm 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("logic",18, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 33: // and_stm ::= AND condition and_stm2 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("and_stm",19, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 34: // and_stm2 ::= AND condition and_stm2 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("and_stm2",25, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 35: // and_stm2 ::= 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("and_stm2",25, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 36: // or_stm ::= OR condition or_stm2 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("or_stm",20, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 37: // or_stm2 ::= OR condition or_stm2 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("or_stm2",26, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 38: // or_stm2 ::= 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("or_stm2",26, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 39: // condition ::= IDENTIFICADOR relational_operator comparable 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("condition",17, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 40: // comparable ::= values 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("comparable",21, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 41: // comparable ::= IDENTIFICADOR 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("comparable",21, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 42: // insertables ::= value more_insertables 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("insertables",22, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 43: // more_insertables ::= COMA insertables 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("more_insertables",23, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 44: // more_insertables ::= 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("more_insertables",23, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 45: // columns_opt ::= PARENTESIS_L columns PARENTESIS_R 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("columns_opt",11, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 46: // columns_opt ::= 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("columns_opt",11, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 47: // asignations ::= IDENTIFICADOR IGUAL value more_asignations 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("asignations",12, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 48: // more_asignations ::= asignations 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("more_asignations",24, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 49: // more_asignations ::= 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("more_asignations",24, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$parser$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$parser$do_action(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
              return CUP$parser$do_action_part00000000(
                               CUP$parser$act_num,
                               CUP$parser$parser,
                               CUP$parser$stack,
                               CUP$parser$top);
    }
}

}
