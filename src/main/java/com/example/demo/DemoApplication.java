package com.example.demo;

import com.example.demo.readInputFiles.ReadFile;
import com.example.demo.stringManipulator.Comparator;
import com.example.demo.stringManipulator.CompareStrings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class DemoApplication {
	private static String name="Putin";
	private static String blacklistFileName = "blacklist.txt";
	private static String noiseFileName = "noise.txt";

	@Autowired
	private static ReadFile read = new ReadFile();

	@Autowired
	private static Comparator compareStrings = new CompareStrings();


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		if (args.length == 3) {
			name = args[0];
			blacklistFileName = args[1];
			noiseFileName = args[2];
		} else if (args.length > 3){
			blacklistFileName = args[args.length-2];
			noiseFileName = args[args.length-1];
			for (int i = 0; i < args.length-2; i++) {
				name += args[i] + " ";
			}
		}
		//System.out.println(name);
		//System.out.println(blacklistFileName);
		//System.out.println(noiseFileName);

		String[] blaclistedNames = read.read(blacklistFileName);
		String[] noiseNames = read.read(noiseFileName);
		String[] resultedNames = compareStrings.getResults(blaclistedNames, name);
		System.out.println(Arrays.toString(resultedNames));
	}
}
