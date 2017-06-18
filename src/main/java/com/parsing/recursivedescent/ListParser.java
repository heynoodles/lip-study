package com.parsing.recursivedescent;

import com.parsing.lexer.Lexer;
import com.parsing.lexer.ListLexer;
import com.parsing.lexer.TokenType;

/**
 * @author gaoxin.wei
 * LL(1)
 * 语法如下
 * list : '[' elements ']' ;
 * elements : element (',' element)* ;
 * element: NAME | list ;
 */
public class ListParser extends Parser {

    public ListParser(Lexer input) {
        super(input);
    }

    public void list() {
        match(TokenType.LBRACK);
        elements();
        match(TokenType.RBRACK);
    }

    private void elements() {
        element();
        while (lookhead.type == TokenType.COMMA) {
            match(TokenType.COMMA);
            element();
        }
    }

    private void element() {
        if (lookhead.type == TokenType.NAME) {
            match(TokenType.NAME);
        } else if (lookhead.type == TokenType.LBRACK) {
            list();
        }
    }

    public static void main(String[] args) {
        String input = "[a, [b, b, [c, c]], d]";
        ListLexer lexer = new ListLexer(input);
        ListParser parser = new ListParser(lexer);
        parser.list();
    }

}
