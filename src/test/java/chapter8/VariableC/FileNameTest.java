package chapter8.VariableC;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FileNameTest {
    @Test
    public void testSimpleMatch() {
        FileName fileNameChecker = new FileName("*.txt");
        assertTrue(fileNameChecker.matches("документ.txt"));
        assertTrue(fileNameChecker.matches(".txt"));
        assertTrue(fileNameChecker.matches("фа йл.txt"));
    }

    @Test
    public void testSimpleNonMatch() {
        FileName fileNameChecker = new FileName("*.txt");
        assertFalse(fileNameChecker.matches("doc.txtx"));
        assertFalse(fileNameChecker.matches("txt"));
        assertFalse(fileNameChecker.matches("документ.tx"));
    }

    @Test
    public void testSingleCharacterWildcard() {
        FileName fileNameChecker = new FileName("file?.txt");
        assertTrue(fileNameChecker.matches("file1.txt"));
        assertTrue(fileNameChecker.matches("fileA.txt"));
        assertFalse(fileNameChecker.matches("file12.txt"));
    }

    @Test
    public void testMixedWildcards() {
        FileName fileNameChecker = new FileName("d?c*.t?t");
        assertTrue(fileNameChecker.matches("doc123.txt"));
        assertTrue(fileNameChecker.matches("d-c.txt"));
        assertFalse(fileNameChecker.matches("dc.txtt"));
    }

    @Test
    public void testEmptyPattern() {
        FileName fileNameChecker = new FileName("");
        assertFalse(fileNameChecker.matches("abc"));
        assertTrue(fileNameChecker.matches(""));
    }

    @Test
    public void testExactMatch() {
        FileName fileNameChecker = new FileName("exact.doc");
        assertTrue(fileNameChecker.matches("exact.doc"));
        assertFalse(fileNameChecker.matches("Exact.doc"));
        assertFalse(fileNameChecker.matches("exact.do"));
    }
}
