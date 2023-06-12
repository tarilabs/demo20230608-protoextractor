package org.drools.demo20230608_protoextractor;

import java.util.ArrayList;
import java.util.List;

import org.drools.demo20230608_protoextractor.ProtoextractorParser.ChunkContext;
import org.drools.demo20230608_protoextractor.ProtoextractorParser.ExtractorContext;
import org.drools.demo20230608_protoextractor.ProtoextractorParser.IdentifierContext;
import org.drools.demo20230608_protoextractor.ProtoextractorParser.SquaredAccessorContext;
import org.drools.demo20230608_protoextractor.ProtoextractorParser.StringLiteralContext;
import org.drools.demo20230608_protoextractor.ast.ASTBuilderFactory;
import org.drools.demo20230608_protoextractor.ast.ASTNode;
import org.drools.demo20230608_protoextractor.ast.IdentifierNode;
import org.drools.demo20230608_protoextractor.ast.StringLiteralNode;
import org.drools.demo20230608_protoextractor.ast.TextValue;

public class ASTProductionVisitor extends ProtoextractorBaseVisitor<ASTNode> {

    @Override
    public ASTNode visitStringLiteral(StringLiteralContext ctx) {
        StringLiteralNode node = ASTBuilderFactory.newStringLiteralNode(ctx);
        return node;
    }

    @Override
    public ASTNode visitIdentifier(IdentifierContext ctx) {
        IdentifierNode node = ASTBuilderFactory.newIdentifierNode(ctx);
        return node;
    }

    @Override
    public ASTNode visitSquaredAccessor(SquaredAccessorContext ctx) {
        return ctx.stringLiteral().accept(this);
    }

    @Override
    public ASTNode visitChunk(ChunkContext ctx) {
        TextValue value = null;
        if (ctx.identifier() != null) {
            value = (TextValue) ctx.identifier().accept(this);
        } else if (ctx.squaredAccessor() != null) {
            value = (TextValue) ctx.squaredAccessor().accept(this);
        }
        return ASTBuilderFactory.newChunkNode(ctx, value);
    }

    @Override
    public ASTNode visitExtractor(ExtractorContext ctx) {
        List<TextValue> values = new ArrayList<>();
        TextValue value0 = (TextValue) ctx.identifier().accept(this);
        values.add(value0);
        for (ChunkContext chunk : ctx.chunk()) {
            values.add((TextValue) chunk.accept(this));
        }
        return ASTBuilderFactory.newExtractorNode(ctx, values);
    }
}
