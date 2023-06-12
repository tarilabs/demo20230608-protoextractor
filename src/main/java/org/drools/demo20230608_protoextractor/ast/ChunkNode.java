package org.drools.demo20230608_protoextractor.ast;

import java.util.Objects;

import org.antlr.v4.runtime.ParserRuleContext;

public class ChunkNode extends AbstractNode implements TextValue {
    private final TextValue value;

    public ChunkNode(ParserRuleContext ctx, TextValue value) {
        super(ctx);
        Objects.requireNonNull(value);
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value.getValue();
    }

    @Override
    public String toString() {
        return "ChunkNode [value=" + value + "]";
    }
}
