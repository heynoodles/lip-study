package com.parsing.lexer;

/**
 * @author gaoxin.wei
 */
public class TokenType {

    public static int NAME = 2;
    public static int COMMA = 3;
    public static int LBRACK = 4;
    public static int RBRACK = 5;
    public static int EQUALS = 6;

    public static String[] tokenNames = {
        "n/a", "<EOF>", "NAME", "COMMA", "LBRACK", "RBRACK", "EQUALS"
    };
}
