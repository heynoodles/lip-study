package com.gossip.ast;

import com.gossip.lexer.Token;
import com.gossip.visitor.GossipVisitor;

/**
 * @author gaoxin.wei
 */
public class IntNode extends HeteroAST {

    public IntNode(Token token) {
        super(token);
    }

    public void visit(GossipVisitor visitor) {
        visitor.visit(this);
    }
}
