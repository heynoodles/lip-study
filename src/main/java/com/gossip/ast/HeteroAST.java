package com.gossip.ast;

import com.gossip.visitor.GossipVisitor;
import com.gossip.lexer.Token;

/**
 * @author gaoxin.wei
 */
public abstract class HeteroAST {

    Token token;

    public HeteroAST() {}

    public HeteroAST(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public abstract Object visit(GossipVisitor visitor);
}
