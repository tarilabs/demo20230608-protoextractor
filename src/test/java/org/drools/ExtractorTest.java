package org.drools;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.drools.demo20230608_protoextractor.ExtractorParser;
import org.drools.demo20230608_protoextractor.ast.ExtractorNode;
import org.junit.jupiter.api.Test;

public class ExtractorTest {

    @Test
    public void testBasic() {
        String expression = "a.b[\"c\"].d[\"e/asd\"]['f'].g.h";
        ExtractorNode extractor = ExtractorParser.parse(expression);
        List<String> parts = extractor.getParts();
        assertThat(parts).containsExactly("a", "b", "c", "d", "e/asd", "f", "g", "h");
    }
}
