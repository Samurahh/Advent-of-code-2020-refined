package com.spartaglobal.samurah.day11;

public class SeatingSystem {
    private String[][] seatMap;

    public SeatingSystem(String[] seatMap) {
        this.seatMap = new String[seatMap.length][seatMap[0].length()];

    }

    public String[][] getSeatMap() {
        return seatMap;
    }
}
