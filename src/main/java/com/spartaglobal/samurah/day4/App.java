package com.spartaglobal.samurah.day4;

import com.spartaglobal.samurah.utilities.FileReader;

import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) {
        long initialTime = System.nanoTime();
        try {
            FileReader fileReader = new FileReader("src/main/java/com/spartaglobal/samurah/day4/PassportProcessing.txt");
            PassportProcessing passportProcessing = new PassportProcessing(fileReader.getRawFileInput().toArray(new String[0]));
            System.out.println(passportProcessing.countValidPassports(false,"cid"));
            System.out.println(passportProcessing.countValidPassports(true,"cid"));
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't find the file. Abort.");
            Thread.currentThread().interrupt();
        }
        long finalTime = System.nanoTime();
        System.err.println("Time taken: "+(finalTime-initialTime)/1_000_000 + " ms.");
    }
}
