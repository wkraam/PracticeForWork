package com.example.demo.stringManipulator;

import org.springframework.stereotype.Component;

@Component("CompareStrings")
public class CompareStrings implements Comparator{

    @Override
    public float CompareStrings(String[] Comparable, String Comparator) {
        float percentage = 0.0f;
        float[] percentages = new float[Comparable.length];
        int matchedChars;
        int index = 0;
        for (String name:Comparable) {

            int nameLength = name.length();
            int comparableNameLenght = Comparator.length();

            if (nameLength == comparableNameLenght){
                matchedChars = compareCharByChar(name, Comparator, nameLength);
            } else if (nameLength < comparableNameLenght){
                matchedChars = compareCharByChar(name, Comparator, nameLength);
            } else {
                matchedChars = compareCharByChar(name, Comparator, comparableNameLenght);
            }
            percentage = (float)matchedChars / (float)nameLength;
            percentages[index] = percentage;
            index++;
        }
        return getPercentage(percentages);
    }

    private int compareCharByChar(String word1, String word2, int shortestStringLength){
        int match = 0;
        for (int i = 0; i < shortestStringLength-1; i++) {
            char c1 = Character.toLowerCase(word1.charAt(i));
            char c2 = Character.toLowerCase(word2.charAt(i));
            if (c1 == c2) match += 1;
        }
        return match;
    }

    private float getPercentage(float[] percentages){
        int n = percentages.length;
        float tulem = 0.0f;
        for (int i = 0; i < n; i++) {
            tulem += percentages[i];
        }
        return tulem = tulem / n;
    }
}
