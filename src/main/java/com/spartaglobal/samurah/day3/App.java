package com.spartaglobal.samurah.day3;

import com.spartaglobal.samurah.utilities.FileReader;

import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) {
        long initialTime = System.nanoTime();
        try {
            FileReader fileReader = new FileReader("src/main/java/com/spartaglobal/samurah/day3/TobogganTrajectory.txt");
            TobogganTrajectory tobogganTrajectory = new TobogganTrajectory(fileReader.getFileInput().toArray(new String[0]));
            int[] startPositions = new int[]{0,0,0,0,0};
            int[] rightSteps = new int[]{1,3,5,7,1};
            int[] downSteps = new int[]{1,1,1,1,2};
            System.out.println(tobogganTrajectory.multiplyTreesOnTheWay(startPositions,rightSteps,downSteps));
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't find the file. Abort.");
            Thread.currentThread().interrupt();
        }
        long finalTime = System.nanoTime();
        System.err.println("Time taken: "+(finalTime-initialTime)/1_000_000 + " ms.");
    }
}
