package com.parsing.backtrack;


import com.parsing.lexer.Lexer;
import com.parsing.lexer.ListLexer;
import com.parsing.lexer.TokenType;

/**
 * @author gaoxin.wei
 * 回溯解析器
 * 语法如下:
 * -------------------------------------
 * stat : list EOF | assign EOF ;
 * assign : list '=' list ;
 * list : '[' elements ']' ;
 * elements : element (',' element)* ;
 * element : NAME '=' NAME | NAME | list ;
 * -------------------------------------
 * list 和 assign 必须通过回溯才能推演
 */
public class BackTrackParser extends Parser {

    public BackTrackParser(Lexer lexer) {
        super(lexer);
    }


    public void stat() {
        if (speculate_stat_list()) {
            list();
            match(ListLexer.EOF_TYPE);
        } else if (speculate_stat_assign()) {
            assign();
            match(ListLexer.EOF_TYPE);
        } else
            throw new Error("parse stat error");
    }

    private boolean speculate_stat_assign() {
        boolean success = true;
        mark();
        try {
            assign();
            match(ListLexer.EOF_TYPE);
        } catch (Error error) {
            success = false;
        }
        release();
        return success;
    }

    private boolean speculate_stat_list() {
        boolean success = true;
        mark();
        try {
            list();
            match(ListLexer.EOF_TYPE);
        } catch (Error error) {
            success = false;
        }
        release();
        return success;
    }

    public void assign() {
        list();
        match(TokenType.EQUALS);
        list();
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
        String input = "[a, b]";
        Lexer lexer = new ListLexer(input);
        BackTrackParser parser = new BackTrackParser(lexer);
        parser.stat();
    }
}
