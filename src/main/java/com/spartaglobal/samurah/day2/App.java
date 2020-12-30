package com.spartaglobal.samurah.day2;

import com.spartaglobal.samurah.utilities.FileReader;

import java.io.FileNotFoundException;

public class App {

    public static void main(String[] args) {
        long initialTime = System.nanoTime();
        try {
            FileReader fileReader = new FileReader("src/main/java/com/spartaglobal/samurah/day2/PasswordPhilosophy.txt");
            PasswordPhilosophy passwordPhilosophy = new PasswordPhilosophy(fileReader.getFileInput().toArray(new String[0]));
            System.out.println(passwordPhilosophy.getTotalCompliantFirstPolicy());
            System.out.println(passwordPhilosophy.getTotalCompliantSecondPolicy());
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't find the file. Abort.");
            Thread.currentThread().interrupt();
        }
        long finalTime = System.nanoTime();
        System.err.println("Time taken: "+(finalTime-initialTime)/1_000_000 + " ms.");
    }
}
