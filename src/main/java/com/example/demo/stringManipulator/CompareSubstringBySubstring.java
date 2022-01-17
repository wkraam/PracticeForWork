package com.example.demo.stringManipulator;

import org.springframework.stereotype.Component;

@Component("Compare by substrings")
public class CompareSubstringBySubstring {
	public int compareSubstringBySubstring(String nameInDB, String blacklistedName) {
		// first find the first matching letter and go from there on til space
		int hit = 0;

		String[] nameInDBArr = nameInDB.toLowerCase().split(" |, ");
		String[] blacklistedNameArr = blacklistedName.toLowerCase().split(" |, ");

		for (String name : nameInDBArr) {
			for (String blackName : blacklistedNameArr) {
				if (name.equals(blackName)) hit += name.length();
			}
		}
		return hit;
	}
}
