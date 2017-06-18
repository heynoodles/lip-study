package com.parsing.lexer;

/**
 * @author gaoxin.wei
 */
public class ListLexerTest {

    public static void main(String[] args) {
        String input = "[hello, kitty ]";
        ListLexer listLexer = new ListLexer(input);

        Token t = listLexer.nextToken();
        while (t.type != Lexer.EOF_TYPE) {
            System.out.println(t);
            t = listLexer.nextToken();
        }
        System.out.println(t);
    }
}
