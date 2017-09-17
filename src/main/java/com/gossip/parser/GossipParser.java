package com.gossip.parser;


import com.gossip.ast.AddNode;
import com.gossip.ast.HeteroAST;
import com.gossip.ast.IntNode;
import com.gossip.lexer.GossipLexer;
import com.gossip.lexer.Lexer;
import com.gossip.lexer.Token;
import com.gossip.lexer.TokenType;
import com.gossip.visitor.PrintVisitor;

/**
 * @author gaoxin.wei
 * 语法如下：
 * -------------------------------------------
 * expr : '(' '+' expr expr ')'
 *          | INT
 *  ------------------------------------------
 */
public class GossipParser extends Parser {

    public GossipParser(Lexer lexer, int k) {
        super(lexer, k);
    }

    public HeteroAST expr() {
        if (LT(1).type == TokenType.INT) {
            HeteroAST intNode = new IntNode(LT(1));
            match(TokenType.INT);
            return intNode;
        } else if (LT(1).type == TokenType.PAREN_BEGIN) {
            match(TokenType.PAREN_BEGIN);
            match(TokenType.ADD);
            HeteroAST left = expr();
            HeteroAST right = expr();
            match(TokenType.PAREN_END);
            return new AddNode(new Token(TokenType.ADD, "+"), left, right);
        } else
            throw new Error("parse element error");
    }

    public HeteroAST parse() {
        return expr();
    }

    public static void main(String[] args) {
        String input = "( + (+ 1 3) 2)";
        GossipLexer lexer = new GossipLexer(input);
        GossipParser parser = new GossipParser(lexer, 2);
        HeteroAST expr = parser.expr();
        PrintVisitor printVisitor = new PrintVisitor();
        expr.visit(printVisitor);
    }

}
