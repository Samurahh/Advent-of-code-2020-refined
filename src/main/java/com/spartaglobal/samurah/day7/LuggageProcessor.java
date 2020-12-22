package com.spartaglobal.samurah.day7;

import com.spartaglobal.samurah.utilities.FileReader;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.regex.Pattern;

public class LuggageProcessor {
    private static HashMap<String, Bag> bags = new HashMap<>();

    public static int getHowManyBagColorsCanEventuallyContainAtLeastOne(String bagColor){
        int count = 0;

        return count;
    }

    public static void loadRulesFromFile(String filePath){
        try {
            FileReader fileReader = new FileReader(filePath);
            fileReader.getFileInput().stream().forEach(it->{

            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
