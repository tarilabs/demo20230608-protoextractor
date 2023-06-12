package org.drools.demo20230608_protoextractor.ast;

import org.antlr.v4.runtime.ParserRuleContext;

public class IdentifierNode extends AbstractNode implements TextValue{

    private final String value;

    public IdentifierNode(ParserRuleContext ctx) {
        super(ctx);
        this.value = getOriginalText(ctx);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "IdentifierNode [value=" + value + "]";
    }
}
