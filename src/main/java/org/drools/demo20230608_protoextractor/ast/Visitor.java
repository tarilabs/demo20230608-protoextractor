package org.drools.demo20230608_protoextractor.ast;

public interface Visitor<T> {
    T visit(ASTNode n);
    T visit(IdentifierNode n);
    T visit(SquaredAccessorNode n);
    T visit(IndexAccessorNode n);
    T visit(ExtractorNode n);
}