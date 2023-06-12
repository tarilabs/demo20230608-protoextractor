package org.drools.demo20230608_protoextractor;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.drools.demo20230608_protoextractor.ast.ExtractorNode;

public class ExtractorParser {
    
    public static ExtractorNode parse(String expression) {
        CodePointCharStream charStream = CharStreams.fromString(expression);
        ProtoextractorLexer lexer = new ProtoextractorLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ProtoextractorParser parser = new ProtoextractorParser(tokens);
        ParseTree tree = parser.extractor();
        ASTProductionVisitor visitor = new ASTProductionVisitor();
        ExtractorNode extractor = (ExtractorNode) visitor.visit(tree);
        return extractor;
    }
}