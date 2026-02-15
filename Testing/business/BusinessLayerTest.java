package business;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import bll.SearchWord;
import dal.TFIDFCalculator;
import dto.Documents;
import dto.Pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BusinessLayerTest {

    private List<Documents> testDocs;

    @BeforeEach
    void setUp() {
        // Setup mock documents for testing
        List<Pages> pages1 = new ArrayList<>();
        pages1.add(new Pages(1, 1, 1, "بسم الله الرحمن الرحيم"));
        pages1.add(new Pages(2, 1, 2, "الحمد لله رب العالمين"));
        
        List<Pages> pages2 = new ArrayList<>();
        pages2.add(new Pages(3, 2, 1, "السلام عليكم ورحمة الله وبركاته"));
        
        testDocs = new ArrayList<>();
        testDocs.add(new Documents(1, "Doc1", "hash1", "2024-01-01", "2024-01-01", pages1));
        testDocs.add(new Documents(2, "Doc2", "hash2", "2024-01-01", "2024-01-01", pages2));
    }

    @Test
    @DisplayName("Test SearchWord: Positive Path (Exact Match)")
    void testSearchKeyword_Positive() {
        List<String> results = SearchWord.searchKeyword("الله", testDocs);
        assertFalse(results.isEmpty(), "Search should find results for 'الله'");
        assertTrue(results.get(0).contains("الله"), "Result should contain the keyword");
    }

    @Test
    @DisplayName("Test SearchWord: Negative Path (Invalid Keyword Length)")
    void testSearchKeyword_InvalidLength() {
        assertThrows(IllegalArgumentException.class, () -> {
            SearchWord.searchKeyword("ما", testDocs);
        }, "Should throw exception for keywords shorter than 3 characters");
    }

    @Test
    @DisplayName("Test SearchWord: Negative Path (No Match)")
    void testSearchKeyword_NoMatch() {
        List<String> results = SearchWord.searchKeyword("مرحبا", testDocs);
        assertTrue(results.isEmpty(), "Should return empty list for non-existent keyword");
    }

    @Test
    @DisplayName("Test TF-IDF Calculator: Positive Path")
    void testTFIDF_Positive() {
        TFIDFCalculator calculator = new TFIDFCalculator();
        calculator.addDocumentToCorpus("هذا نص اختباري");
        calculator.addDocumentToCorpus("نص آخر للتجربة");
        
        double score = calculator.calculateDocumentTfIdf("هذا نص");
        assertTrue(score >= 0, "TF-IDF score should be non-negative");
    }

    @Test
    @DisplayName("Test TF-IDF Calculator: Empty Document")
    void testTFIDF_EmptyDoc() {
        TFIDFCalculator calculator = new TFIDFCalculator();
        double score = calculator.calculateDocumentTfIdf("");
        assertEquals(0.0, score, 0.001, "Empty document should have 0 TF-IDF score");
    }
}
