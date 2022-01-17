package com.example.demo.stringManipulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Arrays;

@Component("CompareStrings")
public class CompareStrings implements Comparator{

    @Autowired
    private CompareSubstringBySubstring compareSubstringBySubstring = new CompareSubstringBySubstring();

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

                matchedChars = compareSubstringBySubstring.compareSubstringBySubstring(name, Comparator);

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


    private boolean findSubString(String nameToCompareAgainst, String nameToFind){
        String[] ComparatorArray = nameToFind.toLowerCase().split(" |, ");
        for(String substring: ComparatorArray){
            if (nameToCompareAgainst.toLowerCase().contains(substring)) return true;
        }

        return false;
    }
}
