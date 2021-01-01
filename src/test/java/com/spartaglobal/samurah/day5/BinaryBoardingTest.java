package com.spartaglobal.samurah.day5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryBoardingTest {

    @Test
    void boardingPassReadCorrectly(){
        String[] input = new String[]{"BFFFBBFRRR","FFFBBBFRRR", "BBFFBBFRLL"};
        int[] resultExpected = new int[]{567,119,820};
        int[] actualResult = new int[3];
        for (int i = 0; i < input.length ; i++) {
            actualResult[i] = new BinaryBoarding.BinaryAirplaneSeat(input[i]).getSeatId();
        }
        for (int i = 0; i < resultExpected.length ; i++) {
            Assertions.assertEquals(resultExpected[i],actualResult[i],"Test "+i);
        }
    }

}