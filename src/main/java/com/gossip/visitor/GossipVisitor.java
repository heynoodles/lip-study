package com.gossip.visitor;

import com.gossip.ast.AddNode;
import com.gossip.ast.HeteroAST;
import com.gossip.ast.IntNode;

/**
 * @author gaoxin.wei
 */
public interface GossipVisitor {

    Object visit(HeteroAST node);
}
