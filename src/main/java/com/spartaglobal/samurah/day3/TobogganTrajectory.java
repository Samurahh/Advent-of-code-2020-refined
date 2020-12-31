package com.spartaglobal.samurah.day3;

/**
 * The problem input is a pattern of local geology.
 * The pattern repeats to the right many times.
 * - (.) open square
 * - (#) tree
 * -> count how many threes you encounter starting from startPosition,
 * -> going rightStep to right and downStep down until the end of the map.
 */

public class TobogganTrajectory {

    private final String[] pattern;

    public TobogganTrajectory(String[] pattern){
        this.pattern = pattern;
    }

    public double multiplyTreesOnTheWay(int[] startPositions, int[] rightSteps, int[] downSteps){
        if(startPositions.length != rightSteps.length || rightSteps.length != downSteps.length){
            throw new IllegalArgumentException("Incomplete sets. (1 set -> int[0] startPosition, int[0] rightStep, int[0] downStep)");
        }
        double product = countTreesOnTheWay(startPositions[0],rightSteps[0],downSteps[0]);
        for (int i = 1; i <startPositions.length ; i++) {
            product *= countTreesOnTheWay(startPositions[i],rightSteps[i],downSteps[i]);
        }
        return product;
    }

    public int countTreesOnTheWay(int startPosition, int rightStep, int downStep){
        int count = 0;
        int xPos = startPosition;
        int yPos = 0;
        while(yPos<pattern.length){
            if(getAtPosition(yPos,xPos) == '#'){
                count++;
            }
            yPos+=downStep;
            xPos+=rightStep;
        }
        return count;
    }

    private char getAtPosition(int row, int column){
        return pattern[row].charAt(Math.abs(column%pattern[row].length()));
    }

    public String[] getPattern() {
        return pattern;
    }
}
