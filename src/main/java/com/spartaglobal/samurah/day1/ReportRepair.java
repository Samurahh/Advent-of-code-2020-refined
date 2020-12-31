package com.spartaglobal.samurah.day1;

import java.util.Arrays;

/**
 * This class is core solver of day 1 of Advent of Calendar 2020.
 * - find numberOfEntries entries that sum sumValue.
 * - multiply those entries together and return the value.
 * NOTE: There may be various possible combinations for sumValue formed by
 * numberOfEntries values!
 ******* The algorithm will find the first combination which will return
 ******* the lowest product.
 */

public class ReportRepair {
    private final int[] inputArray;

    public ReportRepair(int[] inputArray) {
        this.inputArray = inputArray;
        Arrays.sort(inputArray);
    }

    public int[] getInputArray(){
        return inputArray;
    }

    public int multiplyEntriesThatSum(int sumValue, int numberOfEntries) {
        int[] entries = getEntriesThatSum(sumValue,numberOfEntries);
        if(entries.length>0) {
            int result = entries[0];
            for (int i = 1; i < entries.length; i++) {
                result *= entries[i];
            }
            return result;
        }
        return 0;
    }

    private int[] getEntriesThatSum(int sumValue, int numberOfEntries) {
        if (numberOfEntries > inputArray.length) {
            throw new IllegalArgumentException("Number of entries requested exceeds the total length of input.");
        }
        int[] positions = generatePositions(numberOfEntries);
        int currentSum = getSumOfPositions(positions);
        int startIndex = 0;
        while (currentSum!=sumValue && startIndex < inputArray.length-numberOfEntries){
            if(positions[positions.length-1]<inputArray.length-positions.length-1) {
                if(currentSum>sumValue){
                    positions[0] = inputArray.length-1;
                }
                nextPosition(positions);
            }else{
                positions = generatePositions(++startIndex,numberOfEntries);
            }
            currentSum = getSumOfPositions(positions);
        }
        if(currentSum == sumValue){
            return getEntriesOnPositions(positions);
        }
        return new int[0];
    }

    private void nextPosition(int[] positions){
        for (int i = 0; i < positions.length; i++) {
            if(positions[i] < inputArray.length - i - 1){
                positions[i]++;
                if(i>0){
                    positions[i-1] = positions[i]+1;
                }
                return;
            }
        }
    }

    private int[] getEntriesOnPositions(int[] positions){
        int[] entries = new int[positions.length];
        for (int i = 0; i < positions.length; i++) {
            entries[i] = inputArray[positions[i]];
        }
        return entries;
    }

    private int[] generatePositions(int indexOfStart, int size) {
        int[] indexPositions = new int[size];
        for (int i = 0; i < size; i++) {
            indexPositions[i] = indexOfStart+ size - i - 1;
        }
        return indexPositions;
    }

    private int[] generatePositions(int size) {
        return generatePositions(0, size);
    }

    private int getSumOfPositions(int[] positions){
        int sum = 0;
        for (int position : positions) {
            sum += inputArray[position];
        }
        return sum;
    }

}
