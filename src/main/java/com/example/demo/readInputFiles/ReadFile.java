package com.example.demo.readInputFiles;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Component("ReadFiles")
public class ReadFile {

    public String[] read(String filename){
        String name = "";
        File file = new File(filename);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()){
                name += sc.nextLine() + "///";
            }
            String[] names = name.split("///");

            sc.close();
            return names;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
