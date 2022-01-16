package com.example.demo.blacklist;

import com.example.demo.readInputFiles.ReadFile;
import com.example.demo.stringManipulator.CompareStrings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Blacklist implements CommandLineRunner {

    private String name="";
    private String blacklistFileName;
    private String noiseFileName;

    @Override
    public void run(String... args){
        if (args.length == 3) {
            this.name = args[0];
            this.blacklistFileName = args[1];
            this.noiseFileName = args[2];
        } else {
            this.blacklistFileName = args[args.length-2];
            this.noiseFileName = args[args.length-1];
            for (int i = 0; i < args.length-2; i++) {
                this.name += args[i] + " ";
            }
        }
        System.out.println(name);
        System.out.println(blacklistFileName);
        System.out.println(noiseFileName);
    }

    @Autowired
    private ReadFile read;
    String[] blacklistedNames = read.read(blacklistFileName);
    String[] noisenames = read.read(noiseFileName);

    @Autowired
    private CompareStrings compareStrings;
    float finalpercentage;

    public void main(String[] args) {
        System.out.println(blacklistedNames);

    }
}
