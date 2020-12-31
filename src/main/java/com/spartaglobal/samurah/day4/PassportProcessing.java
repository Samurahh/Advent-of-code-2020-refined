package com.spartaglobal.samurah.day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is the core solver of day 4 of Advent of Code 2020.
 * It's given a batch file, each passport is represented as a sequence of key:value pairs separated
 * by spaces or newlines. Passports are separated by blank lines.
 * ***************************************************************
 * FIRST PART-  count how many passports are valid [ countValidPassports(false, "cid"); ]
 * Count the number of valid passports - those that have all required fields. Treat cid as optional.
 * In your batch file, how many passports are valid?
 * ***************************************************************
 * SECOND PART - count how many passports are valid considering the data validation rules [ countValidPassports(true, "cid"); ]
 * Count the number of valid passports - those that have all required fields and valid values. Continue to treat cid as optional.
 * In your batch file, how many passports are valid?
 * **
 * DATA VALIDATION RULES (
 * byr (Birth Year) - four digits; at least 1920 and at most 2002.
 * iyr (Issue Year) - four digits; at least 2010 and at most 2020.
 * eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
 * hgt (Height) - a number followed by either cm or in:
 * If cm, the number must be at least 150 and at most 193.
 * If in, the number must be at least 59 and at most 76.
 * hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
 * ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
 * pid (Passport ID) - a nine-digit number, including leading zeroes.
 * cid (Country ID) - ignored, missing or not.
 */

public class PassportProcessing {

    private final Passport[] passports;

    public PassportProcessing(String[] inputArray){
        ArrayList<Passport> passportsList = new ArrayList<>();
        ArrayList<String> passportInput = new ArrayList<>();
        for (String input:inputArray) {
            if(input.isBlank()){
                passportsList.add(new Passport(passportInput.toArray(new String[0])));
                passportInput.clear();
            }else{
                passportInput.add(input);
            }
        }
        if(passportInput.size()>0){
            passportsList.add(new Passport(passportInput.toArray(new String[0])));
        }
        passports = passportsList.toArray(new Passport[0]);
    }

    public int countValidPassports(boolean enforceDataValidity, String... fieldExceptions){
        int count = 0;
        for (Passport passport: passports) {
            if(passport.isValid(enforceDataValidity, fieldExceptions)){
                count++;
            }
        }
        return count;
    }

    public static class Passport {
        private final HashMap<PassportField, String> fields;

        public Passport(String[] inputArray) {
            this.fields = new HashMap<>();
            Pattern pattern = Pattern.compile("((?<field>\\w+):(?<value>#?\\w+).*?)+");
            for (String input : inputArray) {
                String[] inputSplit = input.split("\\s");
                for (String string : inputSplit) {
                    Matcher matcher = pattern.matcher(string);
                    if (matcher.matches()) {
                        if (!fields.containsKey(PassportField.getField(matcher.group("field")))) {
                            fields.put(PassportField.getField(matcher.group("field")), matcher.group("value"));
                        }
                    }
                }
            }
        }

        public boolean isValid(boolean enforceDataValidity, String... exceptions){
            List<String> exceptionList = Arrays.asList(exceptions);
            for (PassportField passportField : PassportField.values()) {
                if(!exceptionList.contains(passportField.getField())){
                    if(!fields.containsKey(passportField)){
                        return false;
                    }else{
                        if(enforceDataValidity) {
                            if (!isDataValid(passportField, fields.get(passportField))) {
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }

        private boolean isDataValid(PassportField passportField, String value){
            switch (passportField){
                case BIRTH_YEAR:{
                    boolean matchPattern = value.matches("\\d{4}");
                    if (matchPattern) {
                        int intValue = Integer.parseInt(value);
                        return intValue >= 1920 && intValue <= 2002;
                    }
                    return false;
                }
                case ISSUE_YEAR:{
                    boolean matchPattern = value.matches("\\d{4}");
                    if (matchPattern) {
                        int intValue = Integer.parseInt(value);
                        return intValue >= 2010 && intValue <= 2020;
                    }
                    return false;
                }
                case EXPIRATION_YEAR:{
                    boolean matchPattern = value.matches("\\d{4}");
                    if (matchPattern) {
                        int intValue = Integer.parseInt(value);
                        return intValue >= 2020 && intValue <= 2030;
                    }
                    return false;
                }
                case HEIGHT:{
                    Pattern pattern = Pattern.compile("(?<integer>\\d*)\\s?(?<measurement>cm|in)");
                    Matcher matcher = pattern.matcher(value);
                    if (matcher.matches()) {
                        int integerValue = Integer.parseInt(matcher.group("integer"));
                        switch (matcher.group("measurement")) {
                            case "in": {
                                return integerValue >= 59 && integerValue <= 76;
                            }
                            case "cm": {
                                return integerValue >= 150 && integerValue <= 193;
                            }
                        }
                    }
                    return false;
                }
                case HAIR_COLOR:{
                    return value.matches("#[\\da-f]{6}");
                }
                case EYE_COLOR:{
                    return value.matches("(amb|blu|brn|gry|grn|hzl|oth)");
                }
                case PASSPORT_ID:{
                    return value.matches("\\d{9}");
                }
                case COUNTRY_ID:{
                    return true;
                }
                default: return true;
            }
        }

        public HashMap<PassportField, String> getFields(){
            return fields;
        }
    }
}
