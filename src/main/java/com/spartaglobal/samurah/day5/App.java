package com.spartaglobal.samurah.day5;

import com.spartaglobal.samurah.utilities.FileReader;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        long initialTime = System.nanoTime();
        try {
            FileReader fileReader = new FileReader("src/main/java/com/spartaglobal/samurah/day5/BinaryBoarding.txt");
            BinaryBoarding binaryBoarding = new BinaryBoarding(fileReader.getFileInput().toArray(new String[0]));
            System.out.println(binaryBoarding.getHighestSeatId());
            System.out.println(Arrays.toString(binaryBoarding.getEmptySeatsId(true)));
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't find the file. Abort.");
            Thread.currentThread().interrupt();
        }
        long finalTime = System.nanoTime();
        System.err.println("Time taken: "+(finalTime-initialTime)/1_000_000 + " ms.");
    }
}
