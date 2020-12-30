package com.spartaglobal.samurah.day9;

import com.spartaglobal.samurah.utilities.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncodingErrorTest {
    private EncodingError encodingError;

    @BeforeEach
    void setUp() throws Exception {
        FileReader fileReader = new FileReader("src/test/java/com/spartaglobal/samurah/day8/EncodingErrorTestFile.txt");
        encodingError = new EncodingError(fileReader.getFileInput().stream().mapToLong(Long::parseLong).toArray(),5);
    }

    @Test
    void getFirstNotMatchingTheRule(){
        assertEquals(127,encodingError.getFirstNotMatchingTheRule());
    }

    @Test
    void getSumOfSmallestAndLargestInAContiguousSetThatSumsUpTheFirstOneNotMatchingTheRule(){
        assertEquals(62, encodingError.getSumOfSmallestAndLargestNumberInAContiguousSetThatSumUp(127));
    }
}