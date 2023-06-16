package org.drools.demo20230608_protoextractor.prototype;

import java.util.List;
import java.util.Map;

import org.drools.demo20230608_protoextractor.ast.ASTNode;
import org.drools.demo20230608_protoextractor.ast.DefaultedVisitor;
import org.drools.demo20230608_protoextractor.ast.ExtractorNode;
import org.drools.demo20230608_protoextractor.ast.IdentifierNode;
import org.drools.demo20230608_protoextractor.ast.IndexAccessorNode;
import org.drools.demo20230608_protoextractor.ast.SquaredAccessorNode;

public class ValueExtractionVisitor extends DefaultedVisitor<Object> {
    private final Object original;
    private Object cur;

    public ValueExtractionVisitor(Object original) {
        this.original = original;
        this.cur = this.original;
    }

    @Override
    public Object defaultVisit(ASTNode n) {
        cur = null;
        return cur;
    }

    @Override
    public Object visit(IdentifierNode n) {
        cur = fromMap(cur, n.getValue());
        return cur;
    }

    private static Object fromMap(Object in, String key) {
        if (in instanceof Map) {
            return ((Map<?, ?>) in).get(key);
        } else {
            return null;
        }
    }

    @Override
    public Object visit(SquaredAccessorNode n) {
        cur = fromMap(cur, n.getValue());
        return cur;
    }

    @Override
    public Object visit(IndexAccessorNode n) {
        if (cur instanceof List) {
            List<?> theList = (List<?>) cur;
            int javaIdx = n.getValue() > 0 ? n.getValue() : theList.size() + n.getValue();
            if (javaIdx < theList.size()) { // avoid index out of bounds;
                cur = theList.get(javaIdx); 
            }
        }
        return cur;
    }

    @Override
    public Object visit(ExtractorNode n) {
        for (ASTNode chunk : n.getValues()) {
            if (this.cur == null) {
                break;
            }
            this.cur = chunk.accept(this);
        }
        return cur;
    }
}
