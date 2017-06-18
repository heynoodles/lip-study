package com.parsing.lexer;

/**
 * @author gaoxin.wei
 * LL(1)词法解析器
 */
public class ListLexer extends Lexer {

    public ListLexer(String input) {
        super(input);
    }

    public Token nextToken() {
        while (c != EOF) {
            switch (c) {
                case ' ' :
                case '\t':
                case '\n':
                case '\r':
                    WS(); continue;
                case ',':
                    consume();
                    return new Token(TokenType.COMMA, ",");
                case '[':
                    consume();
                    return new Token(TokenType.LBRACK, "[");
                case ']':
                    consume();
                    return new Token(TokenType.RBRACK, "]");
                default:
                    if (isLETTER()) {
                        return NAME();
                    } else {
                        throw new Error("invalid character: " + c);
                    }
            }
        }
        return new Token(EOF_TYPE, "<EOF>");
    }

    private Token NAME() {
        StringBuilder buf = new StringBuilder();
        do {
            buf.append(c);
            consume();
        } while (isLETTER());
        return new Token(TokenType.NAME, buf.toString());
    }

    private void WS() {
        while (c == ' ' || c == '\t' || c == '\r') {
            consume();
        }
    }

    public String getTokenName(int tokenType) {
        return TokenType.tokenNames[tokenType];
    }

    boolean isLETTER() {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
}
