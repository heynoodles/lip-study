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
    protected Token lookahead;

    public Parser(Lexer input) {
        this.input = input;
        consume();
    }

    protected void match(int x) {
        if (lookahead.type == x) {
            consume();
            return;
        }
        throw new RuntimeException("expecting " + TokenType.tokenNames[x] +
            " ; found " + lookahead.text);
    }

    protected void consume() {
        lookahead = input.nextToken();
    }
}
