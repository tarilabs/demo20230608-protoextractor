package org.drools.demo20230608_protoextractor.prototype;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import org.drools.core.facttemplates.Fact;
import org.drools.demo20230608_protoextractor.ast.ExtractorNode;
import org.drools.model.Prototype;
import org.drools.model.PrototypeExpression;
import org.drools.model.PrototypeFact;
import org.drools.model.functions.Function1;

public class ExtractorPrototypeExpression implements PrototypeExpression {
    private final ExtractorNode extractorNode;

    ExtractorPrototypeExpression(ExtractorNode extractorNode) {
        this.extractorNode = extractorNode;
    }

    @Override
    public Function1<PrototypeFact, Object> asFunction(Prototype prototype) {
        return pf -> {
            Map<String, Object> asMap = ((Fact) pf).asMap();
            Object value = extractorNode.extractFrom(asMap);
            return value != null ? value : Prototype.UNDEFINED_VALUE;
        };
    }

    // TODO used for indexing, normalizing chunks.
    public String getFieldName() {
        return extractorNode.getParts().stream().collect(Collectors.joining());
    }

    @Override
    public String toString() {
        return "ExtractorPrototypeExpression{" + extractorNode + "}";
    }

    @Override
    public Collection<String> getImpactedFields() {
        return Collections.singletonList(extractorNode.getParts().get(0));
    }
}
