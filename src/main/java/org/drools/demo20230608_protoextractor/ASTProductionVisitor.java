package org.drools.demo20230608_protoextractor;

import java.util.ArrayList;
import java.util.List;

import org.drools.demo20230608_protoextractor.ProtoextractorParser.ChunkContext;
import org.drools.demo20230608_protoextractor.ProtoextractorParser.ExtractorContext;
import org.drools.demo20230608_protoextractor.ProtoextractorParser.IdentifierContext;
import org.drools.demo20230608_protoextractor.ProtoextractorParser.IndexAccessorContext;
import org.drools.demo20230608_protoextractor.ProtoextractorParser.IntegerLiteralContext;
import org.drools.demo20230608_protoextractor.ProtoextractorParser.SquaredAccessorContext;
import org.drools.demo20230608_protoextractor.ProtoextractorParser.StringLiteralContext;
import org.drools.demo20230608_protoextractor.ast.ASTBuilderFactory;
import org.drools.demo20230608_protoextractor.ast.ASTNode;
import org.drools.demo20230608_protoextractor.ast.IntegerLiteralNode;
import org.drools.demo20230608_protoextractor.ast.TextValue;

public class ASTProductionVisitor extends ProtoextractorBaseVisitor<ASTNode> {

    @Override
    public ASTNode visitStringLiteral(StringLiteralContext ctx) {
        return ASTBuilderFactory.newStringLiteralNode(ctx);
    }

    @Override
    public ASTNode visitIdentifier(IdentifierContext ctx) {
        return ASTBuilderFactory.newIdentifierNode(ctx);
    }

    @Override
    public ASTNode visitIntegerLiteral(IntegerLiteralContext ctx) {
        return ASTBuilderFactory.newIntegerLiteralNode(ctx);
    }

    @Override
    public ASTNode visitSquaredAccessor(SquaredAccessorContext ctx) {
        TextValue str = (TextValue) ctx.stringLiteral().accept(this);
        return ASTBuilderFactory.newSquaredAccessorNode(ctx, str);
    }

    @Override
    public ASTNode visitIndexAccessor(IndexAccessorContext ctx) {
        IntegerLiteralNode idx = (IntegerLiteralNode) ctx.integerLiteral().accept(this);
        return ASTBuilderFactory.newIndexAccessorNode(ctx, idx);
    }

    @Override
    public ASTNode visitChunk(ChunkContext ctx) {
        if (ctx.identifier() != null) {
            return ctx.identifier().accept(this);
        } else if (ctx.squaredAccessor() != null) {
            return ctx.squaredAccessor().accept(this);
        } else if (ctx.indexAccessor() != null) {
            return ctx.indexAccessor().accept(this);
        }
        throw new IllegalStateException("reached illegal state while parsing");
    }

    @Override
    public ASTNode visitExtractor(ExtractorContext ctx) {
        List<ASTNode> values = new ArrayList<>();
        ASTNode value0 = ctx.identifier().accept(this);
        values.add(value0);
        for (ChunkContext chunk : ctx.chunk()) {
            values.add(chunk.accept(this));
        }
        return ASTBuilderFactory.newExtractorNode(ctx, values);
    }
}
