package com.parsing.recursivedescent;

import com.parsing.lexer.Lexer;
import com.parsing.lexer.Token;
import com.parsing.lexer.TokenType;

/**
 * @author gaoxin.wei
 * LL(1)语法解析器
 */
public abstract class Parser {


    private Lexer input;
    protected Token lookhead;

    public Parser(Lexer input) {
        this.input = input;
        consume();
    }

    protected void match(int x) {
        if (lookhead.type == x) {
            consume();
            return;
        }
        throw new RuntimeException("expecting " + TokenType.tokenNames[x] +
            " ; found " + lookhead.text);
    }

    protected void consume() {
        lookhead = input.nextToken();
        System.out.println(lookhead);
    }
}
