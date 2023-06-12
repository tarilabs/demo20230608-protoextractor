package org.drools.demo20230608_protoextractor.ast;

public interface Visitor<T> {
    T visit(ASTNode n);
}