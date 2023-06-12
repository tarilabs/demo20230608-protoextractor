package org.drools.demo20230608_protoextractor.ast;

import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

public class ASTBuilderFactory {
    private ASTBuilderFactory() {
        // only static utils methods.
    }
    public static StringLiteralNode newStringLiteralNode(ParserRuleContext ctx) {
        return new StringLiteralNode( ctx );
    }
    public static IdentifierNode newIdentifierNode(ParserRuleContext ctx) {
        return new IdentifierNode( ctx );
    }
    public static ChunkNode newChunkNode(ParserRuleContext ctx, TextValue value) {
        return new ChunkNode( ctx, value );
    }
    public static ExtractorNode newExtractorNode(ParserRuleContext ctx, List<TextValue> values) {
        return new ExtractorNode( ctx, values );
    }
}
