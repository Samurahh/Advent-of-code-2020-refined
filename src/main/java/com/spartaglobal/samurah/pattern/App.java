package com.spartaglobal.samurah.pattern;

import com.spartaglobal.samurah.utilities.FileReader;

import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) {
        long initialTime = System.nanoTime();
        try {
            FileReader fileReader = new FileReader("");
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't find the file. Abort.");
            Thread.currentThread().interrupt();
        }
        long finalTime = System.nanoTime();
        System.err.println("Time taken: "+(finalTime-initialTime)/1_000_000 + " ms.");
    }
}
