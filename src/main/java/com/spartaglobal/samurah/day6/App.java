package com.spartaglobal.samurah.day6;

import com.spartaglobal.samurah.utilities.FileReader;

import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) {
        long initialTime = System.nanoTime();
        try {
            FileReader fileReader = new FileReader("src/main/java/com/spartaglobal/samurah/day6/CustomCustoms.txt");
            CustomCustoms customCustoms = new CustomCustoms(fileReader.getRawFileInput().toArray(new String[0]));
            System.out.println(customCustoms.countTotalDistinctPositiveAnswers());
            System.out.println(customCustoms.countTotalUnanimityPositiveAnswers());
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't find the file. Abort.");
            Thread.currentThread().interrupt();
        }
        long finalTime = System.nanoTime();
        System.err.println("Time taken: "+(finalTime-initialTime)/1_000_000 + " ms.");
    }
}
