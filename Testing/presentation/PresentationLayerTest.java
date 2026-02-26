package presentation;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import pl.EditorUtils;

public class PresentationLayerTest {

    @Test
    @DisplayName("Test Word Count Logic")
    void testCalculateWordCount() {
        assertEquals(0, EditorUtils.calculateWordCount(null));
        assertEquals(0, EditorUtils.calculateWordCount("   "));
        assertEquals(4, EditorUtils.calculateWordCount("بسم الله الرحمن الرحيم"));
        assertEquals(2, EditorUtils.calculateWordCount("Hello World"));
    }

    @Test
    @DisplayName("Test Line Count Logic")
    void testCalculateLineCount() {
        assertEquals(0, EditorUtils.calculateLineCount(null));
        assertEquals(1, EditorUtils.calculateLineCount("Single line"));
        assertEquals(3, EditorUtils.calculateLineCount("Line 1\nLine 2\nLine 3"));
        assertEquals(2, EditorUtils.calculateLineCount("Line 1\r\nLine 2"));
    }

    @Test
    @DisplayName("Test Avg Word Length Logic")
    void testCalculateAvgWordLength() {
        assertEquals(0.0, EditorUtils.calculateAvgWordLength(""), 0.001);
        assertEquals(0.0, EditorUtils.calculateAvgWordLength(null), 0.001);
        // "Hello" is 5 chars
        assertEquals(5.0, EditorUtils.calculateAvgWordLength("Hello"), 0.001);
        // "Hi To" -> (2+2)/2 = 2.0
        assertEquals(2.0, EditorUtils.calculateAvgWordLength("Hi To"), 0.001);
    }
}
