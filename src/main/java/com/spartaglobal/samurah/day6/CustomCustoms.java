package com.spartaglobal.samurah.day6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Core solver of day 6 - Advent of Code 2020
 * The form asks 26 yes-or-no questions(marked a-z)
 * questions answered with yes are marked down
 * there are groups of answers (people answering the same questions)
 * delimited by a blank line in the input file.
 * - count how many questions were answered with yes(at least once) in a group.
 * - sum those counts. (part 1)
 */
public class CustomCustoms {
    private GroupAnswers[] groupAnswers;

    public CustomCustoms(String[] inputArray) {
        ArrayList<String> inputArrayList = new ArrayList<>();
        ArrayList<GroupAnswers> groupAnswers = new ArrayList<>();
        for (String input : inputArray) {
            if (!input.isBlank()) {
                inputArrayList.add(input);
            } else {
                groupAnswers.add(new GroupAnswers(inputArrayList.toArray(new String[0])));
                inputArrayList.clear();
            }
        }
        if (inputArrayList.size() > 0) {
            groupAnswers.add(new GroupAnswers(inputArrayList.toArray(new String[0])));
        }
        this.groupAnswers = groupAnswers.toArray(new GroupAnswers[0]);
    }

    public int countTotalDistinctPositiveAnswers() {
        int count = 0;
        for (GroupAnswers groupAnswer : groupAnswers) {
            count += groupAnswer.getTotalDistinctPositiveAnswers();
        }
        return count;
    }

    public int countTotalUnanimityPositiveAnswers() {
        int count = 0;
        for (GroupAnswers groupAnswer : groupAnswers) {
            count += groupAnswer.getTotalUnanimityPositiveAnswers();
        }
        return count;
    }


    public static class GroupAnswers {
        private String[] answers;

        public GroupAnswers(String[] answers) {
            this.answers = answers;
        }

        public int getTotalDistinctPositiveAnswers() {
            HashSet<Character> distinctPositiveAnswers = new HashSet<>();
            for (String answer : answers) {
                for (char c : answer.toCharArray()) {
                    distinctPositiveAnswers.add(c);
                }
            }
            return distinctPositiveAnswers.size();
        }

        public int getTotalUnanimityPositiveAnswers() {
            HashMap<Character, Integer> positiveAnswersCounter = new HashMap<>();
            for (String answer : answers) {
                for (char c : answer.toCharArray()) {
                    positiveAnswersCounter.putIfAbsent(c, 0);
                    positiveAnswersCounter.put(c,positiveAnswersCounter.get(c)+1);
                }
            }
            return (int) positiveAnswersCounter.values().stream().filter(integer -> integer == answers.length).count();
        }
    }
}
