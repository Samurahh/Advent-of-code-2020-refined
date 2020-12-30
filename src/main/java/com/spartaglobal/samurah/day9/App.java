package com.spartaglobal.samurah.day9;

import com.spartaglobal.samurah.utilities.FileReader;

import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) {
        long initialTime = System.nanoTime();
        try {
            FileReader fileReader = new FileReader("src/main/java/com/spartaglobal/samurah/day9/EncodingError.txt");
            EncodingError encodingError = new EncodingError(fileReader.getFileInput().stream().mapToLong(Long::parseLong).toArray(),25);
            long firstNotMatchingTheRule = encodingError.getFirstNotMatchingTheRule();
            System.out.println(firstNotMatchingTheRule);
           System.out.println(encodingError.getSumOfSmallestAndLargestNumberInAContiguousSetThatSumUp(firstNotMatchingTheRule));
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't find the file. Abort.");
            Thread.currentThread().interrupt();
        }
        long finalTime = System.nanoTime();
        System.err.println("Time taken: "+(finalTime-initialTime)/1_000_000 + " ms.");
    }
}
