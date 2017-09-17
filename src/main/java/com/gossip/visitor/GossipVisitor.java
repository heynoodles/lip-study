package com.gossip.visitor;

import com.gossip.ast.AddNode;
import com.gossip.ast.IntNode;

/**
 * @author gaoxin.wei
 */
public interface GossipVisitor {

    void visit(IntNode intNode);

    void visit(AddNode addNode);
}
