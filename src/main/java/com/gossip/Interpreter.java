package com.gossip;

import com.gossip.lexer.GossipLexer;
import com.gossip.parser.GossipParser;

/**
 * @author gaoxin.wei
 */
public class Interpreter {

    public static void main(String[] args) {
        String input = "( + 1 2)";
        GossipLexer lexer = new GossipLexer(input);
        GossipParser parser = new GossipParser(lexer, 2);
//        parser.expr();
    }
}
