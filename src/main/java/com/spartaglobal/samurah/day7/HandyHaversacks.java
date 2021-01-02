package com.spartaglobal.samurah.day7;

import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is the day 7 of Advent of Code 2020.
 * The input is a list of rules of colorCoded bags which can contain other bags.
 * - find out how many distinct bags can contain at least one specified bag (Part 1)
 * - find out how many bags are required inside one specified bag (Part 2)
 * @see <a href="https://adventofcode.com/2020/day/7">Day 7 - Advent of Code 2020</a>
 */

public class HandyHaversacks {
    private final HashMap<String, HandyBag> bags;  //<bagColor,HandyBag>


    public HandyHaversacks(String[] inputArray) {
        this.bags = new HashMap<>();
        Pattern color = Pattern.compile("^(?<bagColor>\\w+\\s\\w+).+");
        Pattern content = Pattern.compile("^(?<bagColor>\\w+\\s\\w+)\\s+bags\\s+contain\\s+(?<content>(?!no\\sother\\sbags)[^.]*).*+");
        for (String input : inputArray) {
            Matcher matcherColor = color.matcher(input);
            Matcher matcherContent = content.matcher(input);
            if (matcherColor.matches()) {
                String bagContent = null;
                String bagColor = matcherColor.group("bagColor");
                if (matcherContent.matches()) {
                    bagContent = matcherContent.group("content");
                }
                this.bags.put(bagColor, new HandyBag(bagColor, bagContent));
            }
        }
    }

    private HashSet<String> getDistinctBagsThatContainAtLeastOne(String bagColorToLookFor) {
        HashSet<String> distinctBags = new HashSet<>();
        for (String colorCode : bags.keySet()) {
            if (!colorCode.equals(bagColorToLookFor)) {
                if (mayContain(bagColorToLookFor, bags.get(colorCode), distinctBags)) {
                    distinctBags.add(colorCode);
                }
            }
        }
        return distinctBags;
    }

    public int countHowManyBagsAreInsideOneBag(String bagColorToLookFor) {
        return getHowManyBagsContain(bagColorToLookFor);
    }

    private int getHowManyBagsContain(String bagColorToLookFor) {
        int count = 0;
        for (String colorCode : bags.get(bagColorToLookFor).getContent().keySet()) {
            count += bags.get(bagColorToLookFor).getContent().get(colorCode) * countHowManyBagsAreInside(colorCode);
        }
        return count;
    }

    private int countHowManyBagsAreInside(String bagColorToLookFor) {
        int counter = 1;
        if(bags.get(bagColorToLookFor).getContent() != null) {
            for (String colorCode : bags.get(bagColorToLookFor).getContent().keySet()) {
                counter += bags.get(bagColorToLookFor).getContent().get(colorCode) * countHowManyBagsAreInside(colorCode);
            }
        }
        return counter;
    }

    public int countHowManyDistinctBagsMayContainAtLeastOne(String bagColorToLookFor) {
        return getDistinctBagsThatContainAtLeastOne(bagColorToLookFor).size();
    }

    private boolean mayContain(String bagColorToLookFor, HandyBag bagToLookIn, HashSet<String> alreadyLookedUp) {
        if (bagToLookIn.getContent() != null) {
            if (alreadyLookedUp.contains(bagColorToLookFor)) {
                return true;
            }
            if (bagToLookIn.getBagColor().equals(bagColorToLookFor)) {
                return true;
            }
            for (String s : bagToLookIn.getContent().keySet()) {
                if (bagColorToLookFor.equals(s)) {
                    return true;
                } else {
                    if (mayContain(bagColorToLookFor, bags.get(s), alreadyLookedUp)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static class HandyBag {

        private final String bagColor;
        private final HashMap<String, Integer> content; //<bagColor,count>

        public HandyBag(String bagColor, String content) {
            this.bagColor = bagColor;
            if (content != null) {
                this.content = new HashMap<>();
                String[] contentSplit = content.split(",");
                Pattern individualContentPattern = Pattern.compile("^.*?(?<digits>\\d+)\\s+(?<bagColor>\\w+\\s\\w+).*+");
                for (String individualContent : contentSplit) {
                    Matcher matcher = individualContentPattern.matcher(individualContent);
                    if (matcher.matches()) {
                        String currentBagColor = matcher.group("bagColor");
                        int currentCount = Integer.parseInt(matcher.group("digits"));
                        this.content.put(currentBagColor, currentCount);
                    }
                }
            } else {
                this.content = null;
            }
        }

        @Override
        public String toString() {
            return "HandyBag{" +
                    "bagColor='" + bagColor + '\'' +
                    ", content=" + content +
                    '}';
        }

        public String getBagColor() {
            return bagColor;
        }

        public HashMap<String, Integer> getContent() {
            return content;
        }
    }

}
