package strings.boyerMooreSearch;

import java.util.HashMap;
import java.util.Map;

public class BoyerMooreSearch {
    private Map<Character, Integer> shiftTable;
    private String text;
    private String pattern;

    public BoyerMooreSearch(String text, String pattern) {
        this.text = text;
        this.pattern = pattern;
        this.shiftTable = new HashMap<>();
    }

    private void precompute() {
        int patternLength = pattern.length();
        for (int i = 0; i < patternLength; i++) {
            char currentChar = pattern.charAt(i);
            shiftTable.put(currentChar, rightShiftNumber(patternLength, i));
        }
    }

    private int rightShiftNumber(int patternLength, int index) {
        return Math.max(1, patternLength - index - 1);
    }

    public int search() {
        precompute();

        int patternLength = pattern.length();
        int textLength = text.length();
        int numOfSkips;

        int matches = 0;

        for (int i = 0; i <= textLength - patternLength; i += numOfSkips) {
            numOfSkips = 0;

            for (int j = patternLength - 1; j >= 0; j--) {
                if (pattern.charAt(j) != text.charAt(j + i)) {
                    if (shiftTable.get(text.charAt(i + j)) != null) {
                        numOfSkips = shiftTable.get(text.charAt(i + j));
                        break;
                    } else {
                        numOfSkips = patternLength;
                        break;
                    }
                }
            }

            if (numOfSkips == 0) {
                matches++;
                numOfSkips += patternLength;
            }
        }

        return matches;
    }


    //    public int search(String text, String pattern) {
//
//        int result = 0;
//
//        for (int i = 0; i < pattern.length(); i++) {
//            int index = Math.max(1, pattern.length() - i - 1);
//            char ch = pattern.charAt(i);
//            System.out.println("char: " + ch + " index: " + index);
//            table.put(ch, index);
//        }
//
//
//        int textIndex = pattern.length() - 1;
//        while (textIndex < text.length()) {
//            char currentChar = text.charAt(textIndex);
//            if (hasChar(currentChar)) {
//                int patternIndex = pattern.length() - 1;
//                if (currentChar != pattern.charAt(patternIndex)) {
//                    textIndex += table.get(currentChar);
//                } else {
//                    for (int j = textIndex; patternIndex >= 0; j--) {
//                        if (text.charAt(j) == pattern.charAt(patternIndex)) {
//                            patternIndex--;
//                        } else {
//                            if (hasChar(text.charAt(j))) {
//                                textIndex += table.get(text.charAt(textIndex));
//                            } else {
//                                break;
//                            }
//                        }
//                    }
//
//                    if (patternIndex == -1) {
//                        result++;
//                        textIndex += pattern.length();
//                    }
//                }
//            } else {
//                textIndex += pattern.length();
//            }
//        }
//
//
//        return result;
//    }
//
//    private boolean hasChar(char ch) {
//        return table.containsKey(ch);
//    }
}
