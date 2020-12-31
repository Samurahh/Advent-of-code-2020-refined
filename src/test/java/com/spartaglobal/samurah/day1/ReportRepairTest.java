package com.spartaglobal.samurah.day1;

import com.spartaglobal.samurah.utilities.FileReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ReportRepairTest {

    static ReportRepair reportRepair;

    @BeforeAll
    static void init(){
        try {
            FileReader fileReader = new FileReader("src/test/java/com/spartaglobal/samurah/day1/ReportRepairTest.txt");
            reportRepair = new ReportRepair(fileReader.getFileInput().stream().mapToInt(Integer::parseInt).toArray());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void multiplyFirstTwoEntriesThatSum(){
        Assertions.assertEquals(1013211,reportRepair.multiplyEntriesThatSum(2020,2));
    }

    @Test
    void multiplyFirstThreeEntriesThatSum(){
        Assertions.assertEquals(13891280,reportRepair.multiplyEntriesThatSum(2020,3));
    }

/*
 * The current algorithm will fail certain test scenarios, where the sum can be formed with numbers that will have lower multiply result
 * for example: numberOfEntries = 3, entries expected: [53, 49, 102] ,sum = 204, resultExpected = 264_894
 * the algorithm would find entries: [2, 39, 163], sum = 204, resultActual = 12_714
 * FILE_INPUT [2, 200, 39, 163, 321, 53, 49, 102] -> PROCESSED_INPUT [2, 39, 49, 53, 102, 163, 200, 321]
 */
//    @Test
//    void multiplyRandomEntries(){
//        Random random = new Random();
//        int numberOfEntries = 2+(random.nextInt(2));
//        int[] entries = IntStream.range(0,50).map(i->random.nextInt(reportRepair.getInputArray().length)).distinct().limit(numberOfEntries).map(i-> reportRepair.getInputArray()[i]).toArray();
//        int sum = Arrays.stream(entries).sum();
//        int result = entries[0];
//        for (int i = 1; i < numberOfEntries; i++) {
//            result*=entries[i];
//        }
//        String message = String.format("%d entries, sum = %d, entries: %s ",numberOfEntries,sum,Arrays.toString(entries));
//        Assertions.assertEquals(result,reportRepair.multiplyEntriesThatSum(sum,numberOfEntries),message);
//    }
//
//    @Test
//    void repeatTestMultiplyRandomEntries(){
//        int times = 100;
//        for (int i = 0; i < times; i++) {
//            multiplyRandomEntries();
//        }
//    }
}