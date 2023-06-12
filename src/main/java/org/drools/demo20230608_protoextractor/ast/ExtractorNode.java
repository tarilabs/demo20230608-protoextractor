package org.drools.demo20230608_protoextractor.ast;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ParserRuleContext;

public class ExtractorNode extends AbstractNode {
    private final List<TextValue> values;

    public ExtractorNode(ParserRuleContext ctx, List<TextValue> values) {
        super(ctx);
        Objects.requireNonNull(values);
        this.values = Collections.unmodifiableList(values);
    }

    public List<TextValue> getValues() {
        return values;
    }

    public List<String> getParts() {
        return values.stream().map(TextValue::getValue).collect(Collectors.toList());
    }

    public Object extractFrom(Map<String, Object> value) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public String toString() {
        return "ExtractorNode [values=" + values + "]";
    }
}
