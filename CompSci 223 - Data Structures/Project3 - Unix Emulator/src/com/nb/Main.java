package com.nb;

import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static String SAVE_FILE_NAME = "fsSave";
    /**
     * Check to see if the File System save file exists
     *
     * @return              True if the save file exists, false if it does not
     */
    public static boolean saveFileExists(String fileName){
        java.io.File f = new java.io.File(fileName);
        if(f.exists() && !f.isDirectory()) {
            return true;
        }
        return false;
    }

    /**
     * Main Method
     * @param args          Command line args for the program
     */
    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        CommandParser cp = new CommandParser(fs);
        if (saveFileExists(SAVE_FILE_NAME)){
            System.out.println("Would You Like to Load The File System From the Save File? : y/n");
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine().toLowerCase();
            if(text.equals("y")){
                fs.loadFromFile(SAVE_FILE_NAME);
            }
        }
        fs.printPrompt();
        while (cp.getAndExecuteCommand()){
            fs.printPrompt();
        }
        System.out.println("Made By Nick Bolles on 4/18/2015-4/30/2015 for UW Whitewater Class CompSci 223 \"Data Structures\"");
    }
}
