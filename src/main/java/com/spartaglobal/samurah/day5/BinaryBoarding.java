package com.spartaglobal.samurah.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is the core solver of day 5 of Advent of Code 2020.
 * The problem's input is a "binary space partitioning" that is used to specify a seat.
 * INPUT EXAMPLE: FBFBBFFRLR => AFTER PROCESSING WE FIND OUT: row 44, column 5 = seat 357 (row*8+column)
 * F - front, B - back, R - right, L - left
 * The first 7 characters will either be F or B; these specify exactly one of the 128 rows
 * on the plane (numbered 0 through 127). Each letter tells you which half of a region the
 * given seat is in. Start with the whole list of rows; the first letter indicates whether
 * the seat is in the front (0 through 63) or the back (64 through 127). The next letter
 * indicates which half of that region the seat is in, and so on until you're left with
 * exactly one row.
 * The last three characters will be either L or R; these specify exactly one of the 8 columns
 * of seats on the plane (numbered 0 through 7). The same process as above proceeds again,
 * this time with only three steps. L means to keep the lower half, while R means to keep
 * the upper half.
 **/

public class BinaryBoarding {

    public static final int MAX_ROW = 128;
    public static final int MAX_COLUMN = 8;

    private final HashMap<Integer,BinaryAirplaneSeat> binaryAirplaneSeats; // <seatId, seatObject>

    public BinaryBoarding(String[] inputArray) {
        binaryAirplaneSeats = new HashMap<>();
        for (String s : inputArray) {
            BinaryAirplaneSeat binaryAirplaneSeat = new BinaryAirplaneSeat(s);
            this.binaryAirplaneSeats.put(binaryAirplaneSeat.getSeatId(), binaryAirplaneSeat);
        }
    }

    public int getHighestSeatId() {
        int highestId = 0;
        for (int seatId : binaryAirplaneSeats.keySet()) {
            if (seatId > highestId) {
                highestId = seatId;
            }
        }
        return highestId;
    }
    public int getLowestSeatId() {
        int lowestId = MAX_ROW-1;
        for (int seatId : binaryAirplaneSeats.keySet()) {
            if (seatId < lowestId) {
                lowestId = seatId;
            }
        }
        return lowestId;
    }

    private int[] getBoundaries(){
        int frontRow = getLowestSeatId()/MAX_COLUMN;
        int backRow = getHighestSeatId()/MAX_COLUMN;
        return new int[]{frontRow,backRow};
    }

    public int[] getOccupiedSeatsId(){
        return binaryAirplaneSeats.keySet().stream().mapToInt(i->i).toArray();
    }

    public int[] getEmptySeatsIdInRange(int fromRowInclusive, int toRowInclusive){
        ArrayList<Integer> emptySeatsId = new ArrayList<>();
        for (int i = fromRowInclusive; i <= toRowInclusive; i++) {
            for (int j = 0; j < MAX_COLUMN; j++) {
                int currentSeatIdChecked = BinaryAirplaneSeat.getSeatId(i,j);
                if(!binaryAirplaneSeats.containsKey(currentSeatIdChecked)){
                    emptySeatsId.add(currentSeatIdChecked);
                }
            }
        }
        return emptySeatsId.stream().mapToInt(i->i).toArray();
    }

    public int[] getEmptySeatsId(){
        return getEmptySeatsIdInRange(0, MAX_ROW-1);
    }

    public int[] getEmptySeatsId(boolean flightFull){
        if(flightFull){
            int[] boundaries = getBoundaries();
            return getEmptySeatsIdInRange(boundaries[0],boundaries[1]);
        }else{
            return getEmptySeatsId();
        }
    }

    public static class BinaryAirplaneSeat {
        private final int row;
        private final int column;

        public BinaryAirplaneSeat(String input) {
            Pattern pattern = Pattern.compile("(?<row>[FB]{7})(?<column>[LR]{3})");
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                row = getRow(matcher.group("row"));
                column = getColumn(matcher.group("column"));
            } else {
                throw new IllegalArgumentException("Pattern doesn't match!");
            }
            if (row == -1 || column == -1) {
                throw new IllegalArgumentException("Couldn't find your seat!");
            }
        }

        private static int getRow(String input) {
            int backSeat = MAX_ROW - 1;
            int frontSeat = 0;
            char[] rowCharArray = input.toCharArray();
            for (int i = 0; i < rowCharArray.length - 1; i++) {
                switch (rowCharArray[i]) {
                    case 'F': {
                        backSeat = (backSeat + frontSeat) / 2;
                        break;
                    }
                    case 'B': {
                        frontSeat = (backSeat + frontSeat) / 2 + 1;
                    }
                }
            }
            switch (rowCharArray[rowCharArray.length - 1]) {
                case 'F': {
                    return frontSeat;
                }
                case 'B': {
                    return backSeat;
                }
            }
            return -1;
        }

        private static int getColumn(String input) {
            int leftSeat = 0;
            int rightSeat = MAX_COLUMN - 1;
            char[] rowCharArray = input.toCharArray();
            for (int i = 0; i < rowCharArray.length - 1; i++) {
                switch (rowCharArray[i]) {
                    case 'R': {
                        leftSeat = (leftSeat + rightSeat) / 2 + 1;
                        break;
                    }
                    case 'L': {
                        rightSeat = (leftSeat + rightSeat) / 2;
                    }
                }
            }
            switch (rowCharArray[rowCharArray.length - 1]) {
                case 'R': {
                    return rightSeat;
                }
                case 'L': {
                    return leftSeat;
                }
            }
            return -1;
        }

        public int getRow() {
            return this.row;
        }

        public int getColumn() {
            return this.column;
        }

        public int getSeatId() {
            return getSeatId(row, column);
        }

        public static int getSeatId(int row, int column){
            return (row * 8) + column;
        }
    }
}
