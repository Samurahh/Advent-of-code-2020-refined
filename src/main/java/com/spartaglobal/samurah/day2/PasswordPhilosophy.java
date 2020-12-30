package com.spartaglobal.samurah.day2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordPhilosophy {
    private final PasswordPolicy[] passwordPolicies;

    public PasswordPhilosophy(String[] inputArray) {
        passwordPolicies = new PasswordPolicy[inputArray.length];
        for (int i = 0; i < passwordPolicies.length; i++) {
            passwordPolicies[i] = new PasswordPolicy(inputArray[i]);
        }
    }

    public int getTotalCompliantFirstPolicy(){
        int count = 0;
        for (PasswordPolicy passwordPolicy : passwordPolicies) {
            if(passwordPolicy.isCompliantFirstPolicy()){
                count++;
            }
        }
        return count;
    }

    public int getTotalCompliantSecondPolicy(){
        int count = 0;
        for (PasswordPolicy passwordPolicy : passwordPolicies) {
            if(passwordPolicy.isCompliantSecondPolicy()){
                count++;
            }
        }
        return count;
    }

    public PasswordPolicy[] getPasswordPolicies(){
        return passwordPolicies;
    }

    private static class PasswordPolicy {
        private final int firstDigits;
        private final int secondDigits;
        private final char charTarget;
        private final String password;

        public PasswordPolicy(String input) {
            Pattern pattern = Pattern.compile("(?<firstDigits>\\d+).*?(?<secondDigits>\\d+).*?(?<charTarget>[a-zA-z]):.*?(?<password>[a-zA-z]+)");
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                this.firstDigits = Integer.parseInt(matcher.group("firstDigits"));
                this.secondDigits = Integer.parseInt(matcher.group("secondDigits"));
                this.charTarget = matcher.group("charTarget").charAt(0);
                this.password = matcher.group("password");
            } else {
                throw new IllegalArgumentException("The input: " + input + " does not match the format expected!");
            }
        }

        /*
         * Compliant for the First Policy (Part 1 of the problem) means that the password
         * must contain charTarget at least firstDigits times and the most secondDigits times.
         */
        public boolean isCompliantFirstPolicy() {
            int count = 0;
            for (char o : password.toCharArray()) {
                if (o == charTarget) {
                    count++;
                }
                if (count > secondDigits) {
                    return false;
                } //cycles saver
            }
            return firstDigits <= count && secondDigits >= count;
        }

        /*
         * Compliant for the Second Policy (Part 2 of the problem) means that the password
         * must contain charTarget at position firstDigits OR secondDigits!
         */
        public boolean isCompliantSecondPolicy() {
            char[] passwordChars = password.toCharArray();
            int occurrence = 0;
            if(passwordChars[firstDigits-1] == charTarget){
                occurrence++;
            }
            if(passwordChars[secondDigits-1] == charTarget){
                occurrence++;
            }
            return occurrence == 1;
        }

        public int getFirstDigits() {
            return firstDigits;
        }

        public int getSecondDigits() {
            return secondDigits;
        }

        public char getCharTarget() {
            return charTarget;
        }

        public String getPassword() {
            return password;
        }
    }
}
