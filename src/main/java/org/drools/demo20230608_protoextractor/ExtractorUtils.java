package org.drools.demo20230608_protoextractor;

import java.util.List;

import org.drools.demo20230608_protoextractor.ast.ExtractorNode;
import org.drools.demo20230608_protoextractor.prototype.NormalizedFieldRepresentationVisitor;
import org.drools.demo20230608_protoextractor.prototype.ValueExtractionVisitor;

public class ExtractorUtils {
    private ExtractorUtils() {
        // only static methods.
    }
    
    public static List<String> getParts(ExtractorNode extractorNode) {
        return new NormalizedFieldRepresentationVisitor().visit(extractorNode);
    }

    public static Object getValueFrom(ExtractorNode extractorNode, Object readValue) {
        return new ValueExtractionVisitor(readValue).visit(extractorNode);
    }
}
