package org.drools;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.drools.demo20230608_protoextractor.ExtractorParser;
import org.drools.demo20230608_protoextractor.ast.ExtractorNode;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ExtractorTest {

    @Test
    public void testBasic() throws Exception {
        final String expression = "a.b[\"c\"].d[\"e/asd\"]['f'].g.h";

        ExtractorNode extractor = ExtractorParser.parse(expression);
        List<String> parts = extractor.getParts();
        assertThat(parts)
            .as("a series of string chunks which could be used for indexing")
            .containsExactly("a", "b", "c", "d", "e/asd", "f", "g", "h");

        final String JSON = Files.readString(Paths.get(ExtractorTest.class.getResource("/test1.json").toURI()));
        Map<?, ?> readValue = new ObjectMapper().readValue(JSON, Map.class);
        Object valueExtracted = extractor.extractFrom(readValue);
        assertThat(valueExtracted)
            .as("extractor can be used to extract value based on the path expression")
            .isEqualTo(47);
    }
}
