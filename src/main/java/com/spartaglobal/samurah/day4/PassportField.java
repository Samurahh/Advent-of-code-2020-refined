package com.spartaglobal.samurah.day4;

public enum PassportField {
    BIRTH_YEAR("byr"),
    ISSUE_YEAR("iyr"),
    EXPIRATION_YEAR("eyr"),
    HEIGHT("hgt"),
    HAIR_COLOR("hcl"),
    EYE_COLOR("ecl"),
    PASSPORT_ID("pid"),
    COUNTRY_ID("cid");

    public final String field;

    PassportField(String field) {
        this.field = field;
    }

    public String getField(){
        return this.field;
    }

    public static PassportField getField(String field) {
        for (PassportField passportField: PassportField.values()) {
            if(passportField.getField().equals(field)){
                return passportField;
            }
        }
        return null;
    }

}
