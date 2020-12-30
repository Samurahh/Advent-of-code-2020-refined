package com.spartaglobal.samurah.day1;

import com.spartaglobal.samurah.utilities.FileReader;

import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) {
        long initialTime = System.nanoTime();
        try {
            FileReader fileReader = new FileReader("src/main/java/com/spartaglobal/samurah/day1/ReportRepair.txt");
            ReportRepair reportRepair = new ReportRepair(fileReader.getFileInput().stream().mapToInt(Integer::parseInt).toArray());
            System.out.println(reportRepair.multiplyEntriesThatSum(2020, 2));
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't find the file. Abort.");
            Thread.currentThread().interrupt();
        }
        long finalTime = System.nanoTime();
        System.err.println("Time taken: "+(finalTime-initialTime)/1_000_000 + " ms.");
    }
}
