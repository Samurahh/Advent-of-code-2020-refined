package com.spartaglobal.samurah.day9;


public class EncodingError {
    /**
     * This class is the core of day 9 problem. Create an instance of this class, giving a long array and the considered
     * previous numbers. This class is tested. Any modification of the code should be validated by testing it using
     * the suit of tests in "test" directory.
     */

    private final long[] encodingValues; // problem input
    private final int consideredPrevious; //the rule says that the (n+1)th element should be the sum of any two of the (n) immediately previous numbers

    public EncodingError(long[] encodingValues, int consideredPrevious){
        if(encodingValues.length <= consideredPrevious) {
            throw new IllegalArgumentException("Array length must be greater than number of previous elements considered.");
        }
        this.encodingValues = encodingValues;
        this.consideredPrevious = consideredPrevious;
    }

    private boolean isMatchingTheRule(int indexOfNumber) {
        int startPosition = indexOfNumber-consideredPrevious;
        long number = encodingValues[indexOfNumber];

        for (int i = startPosition ; i < indexOfNumber-1; i++) {
            for (int j = i+1; j < indexOfNumber; j++) {
                if (encodingValues[i] + encodingValues[j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    public long getFirstNotMatchingTheRule() {
        for (int i = consideredPrevious; i < encodingValues.length ; i++) {
            if(!isMatchingTheRule(i)) {
                return encodingValues[i];
            }
        }
        return 0;
    }//returns first number that is not a sum of two elements from the last consideredPrevious elements.

    private long sumInRange(int indexLeft, int indexRight){
        long sum = 0;
        for (int i = indexLeft; i <= indexRight ; i++) {
            sum+=encodingValues[i];
        }
        return sum;
    }//return the sum of elements between indexLeft and indexRight, including the elements of indexLeft and indexRight

    public long getSumOfSmallestAndLargestNumberInAContiguousSetThatSumUp(long number) {
        int indexLeft = 0;
        int indexRight = indexLeft + 1;
        long sumInRange = sumInRange(indexLeft,indexRight);
        while(indexLeft<indexRight && sumInRange!= number){
            if(sumInRange(indexLeft,indexRight) > number){
                indexLeft++;
            }else{
                indexRight++;
            }
            sumInRange = sumInRange(indexLeft,indexRight);
        }
        if(sumInRange == number){
            return getSumOfSmallestAndLargest(indexLeft,indexRight);
        }
        return 0;
    }

    private long getSumOfSmallestAndLargest(int indexLeft, int indexRight){
        long max = encodingValues[indexLeft];
        long min = encodingValues[indexLeft];
        for (int i = indexLeft+1; i <= indexRight ; i++) {
            if(encodingValues[i] > max){
                max = encodingValues[i];
            }
            if(encodingValues[i] < min){
                min = encodingValues[i];
            }
        }
        return max+min;
    }
}
