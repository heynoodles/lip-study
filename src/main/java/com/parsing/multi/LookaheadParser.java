package com.parsing.multi;

import com.parsing.lexer.Lexer;
import com.parsing.lexer.ListLexer;
import com.parsing.lexer.TokenType;

/**
 * @author gaoxin.wei
 * LL(2)语法解析器
 * 语法如下：
 * -------------------------------------------
 * list : '[' elements ']' ;
 * elements : element (',' element)* ;
 * element: NAME '=' NAME
 *          | NAME
 *          | list
 *  ------------------------------------------
 *  NAME '=' NAME 与 NAME 的判断需要向前看2个Token
 */
public class LookaheadParser extends Parser {

    public LookaheadParser(Lexer lexer, int k) {
        super(lexer, k);
    }

    public void list() {
        match(TokenType.LBRACK);
        elements();
        match(TokenType.RBRACK);
    }

    private void elements() {
        element();
        while (LT(1).type == TokenType.COMMA) {
            match(TokenType.COMMA);
            element();
        }
    }

    private void element() {
        if (LT(1).type == TokenType.NAME && LT(2).type == TokenType.EQUALS) {
            match(TokenType.NAME);
            match(TokenType.EQUALS);
            match(TokenType.NAME);
        } else if (LT(1).type == TokenType.NAME) {
            match(TokenType.NAME);
        } else if (LT(1).type == TokenType.LBRACK) {
            list();
        } else
            throw new Error("parse element error");
    }

    public static void main(String[] args) {
        String input = "[a, b=c, d, [e,f]]";
        Lexer lexer = new ListLexer(input);
        LookaheadParser parser = new LookaheadParser(lexer, 2);
        parser.list();
    }

}
