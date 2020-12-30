package com.spartaglobal.samurah.day10;

import com.spartaglobal.samurah.utilities.FileReader;

import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) {
        long initialTime = System.nanoTime();
        try {
            FileReader fileReader = new FileReader("src/main/java/com/spartaglobal/samurah/day10/AdapterArray.txt");
            AdapterArray adapterArray = new AdapterArray(0, fileReader.getFileInput().stream().mapToInt(Integer::parseInt).toArray(), 3);
            adapterArray.printAdapters();
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't find the file. Abort.");
            Thread.currentThread().interrupt();
        }
        long finalTime = System.nanoTime();
        System.err.println("Time taken: "+(finalTime-initialTime)/1_000_000 + " ms.");
    }
}
