package com.example.demo.stringManipulator;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component("CompareStrings")
public class CompareStrings implements Comparator{

    @Override
    public String[] getResults(String[] Comparable, String Comparator) {
        float percentage = 0.0f;
        float[] percentages = new float[Comparable.length];
        int matchedChars;
        int index = 0;
        for (String name:Comparable) {
            if (name.equals(Comparator)) { // finds if the name is an exact match
                percentages[index] = 1.0f;
                index++;
                continue;
            }

            if (findSubString(name, Comparator)) { // if the name isn't an exact match, go and find the matching percentage
                int nameLength = name.length();

                matchedChars = compareSubstringBySubstring(name, Comparator);

                percentage = (float) matchedChars / (float) nameLength;
                percentages[index] = percentage;

            }
            index++;
        }
        return results(percentages, Comparable, 0.10f);
    }


    private String[] results(float[] percentages, String[] names, float thresholdPercentage){
        ArrayList<String> finalNames = new ArrayList<>();
        int i = 0;
        for(float percent: percentages){
            if(percent >= thresholdPercentage){
                finalNames.add(names[i]);
            }
            i++;
        }
        return new String[]{Arrays.toString(finalNames.toArray(new String[finalNames.size()]))};
    }


    private int compareSubstringBySubstring(String nameInDB, String blacklistedName){
        // first find the first matching letter and go from there on til space
        int hit = 0;

        String[] nameInDBArr = nameInDB.toLowerCase().split(" |, ");
        String[] blacklistedNameArr = blacklistedName.toLowerCase().split(" |, ");

        for(String name: nameInDBArr){
            for(String blackName: blacklistedNameArr){
                if (name.equals(blackName)) hit+= name.length();
            }
        }


        /* --------totally useless-------
        for(String blackName: blacklistedNameArr){
            if (findSubString(nameInDB.toLowerCase(), blackName.toLowerCase())) {
                hit+=blackName.length();
            } else {
                for (int i = 0; i < blackName.length(); i++) {
                    if (blackName.charAt(0) == blacklistedName.charAt(i)){
                        hit++;
                        try {
                            for (int c = 1; c < blackName.length(); c++) {
                                if (blackName.charAt(c) == blacklistedName.charAt(c)) hit++;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            break;
                        }
                    }
                }
            }
        }*/

        /*for (int i = 0; i < shortestStringLength; i++) {
            char c1 = Character.toLowerCase(nameInDB.charAt(i));
            char c2 = Character.toLowerCase(blacklistedName.charAt(i));
            if (c1 == c2) match += 1;
        }*/
        return hit;
    }

    private float getPercentage(float[] percentages){
        int n = percentages.length;
        int countOfAverages = 0;
        float result = 0;

        for (int i = 0; i < n; i++) {
            if (percentages[i] > 0.0f){
                result += percentages[i];
                countOfAverages++;
            }
        }
        return result / countOfAverages;
    }

    private boolean findSubString(String nameToCompareAgainst, String nameToFind){
        String[] ComparatorArray = nameToFind.toLowerCase().split(" |, ");
        for(String substring: ComparatorArray){
            if (nameToCompareAgainst.toLowerCase().contains(substring)) return true;
        }

        return false;
    }
}
