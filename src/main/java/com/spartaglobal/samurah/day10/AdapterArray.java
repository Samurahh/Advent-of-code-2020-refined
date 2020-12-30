package com.spartaglobal.samurah.day10;

import java.util.Arrays;

public class AdapterArray {
    private final int outletInput; //the outlet value in Joltage
    private final int[] adapters; //the array of adapters
    private final int deviceHigherAllowance; //rule says that the device Joltage equals with (deviceHigherAllowance) more than max adapter.

    public AdapterArray(int outletInput, int[] adapters, int deviceHigherAllowance) {
        this.outletInput = outletInput;
        this.adapters = adapters;
        this.deviceHigherAllowance = deviceHigherAllowance;
        Arrays.sort(this.adapters);
    }

    public void printAdapters() {
        System.out.println(Arrays.toString(adapters));
    }

    private int countDifferenceOf(int difference) {
        int count = 0;
        if (adapters[0] - outletInput == difference) {
            count++;
        }
        for (int i = 0; i < adapters.length - 1; i++) {
            if (adapters[i + 1] - adapters[i] == difference) {
                count++;
            }
        }
        if (deviceHigherAllowance == difference) {
            count++;
        }
        return count;
    }

    public int multiplyNumberOfDifferences(int differenceOne, int differenceTwo) {
        return countDifferenceOf(differenceOne) * countDifferenceOf(differenceTwo);
    }

}
