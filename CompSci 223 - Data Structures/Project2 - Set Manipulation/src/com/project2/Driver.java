package com.project2;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.Scanner;

public class Driver {
    public static Set<Set> sets = new Set<Set>("Sets");
    //Define the help strings
    public static String[][] help = {
            {"add        ","Required Format: <action (add> <SetName>:<Set (\"{1,2,3}\")>      ", "Adds a Set to the Environment" },
            {"list       ","Required Format: <action (list)> <SetName (optional)              ", "Prints String representation of all of the Sets, or the specified Set name"},
            {"remove     ","Required Format: <action (remove)> <SetName> <index (optional)    ", "Removes a Set, or an item at the index of the Set"},
            {"tremove    ","Required Format: <action (tremove)> <SetName> <item (3)>          ", "Removes an element in the Set"},
            {"equal      ","Required Format: <action (equal)> <SetName> <SetName>...          ", "Enter any amount of Set Names to determine if they are all equal"},
            {"find       ","Required Format: <action (find)> <SetName> <item>...              ", "Find the index of the item in the specified Set"},
            {"intersect  ","Required Format: <action (intersect)> <SetName> <SetName>         ", "Finds and returns the common elements in two Sets"},
            {"union      ","Required Format: <action (union)> <SetName> <SetName>             ", "Returns a Set with all of the elements in Set1 and Set2"},
            {"subtract   ","Required Format: <action (subtract)> <SetName> <SetName>          ", "Returns a Set Elements in Set1, that are not in Set2"},
            {"difference ","Required Format: <action (difference)> <SetName> <SetName>        ", "Returns a Set with all of the elements that are not in both Sets"},
            {"test       ","Runs tests of each action. Mostly for Development Purposes, but also for demonstration purposes.", ""},
            {"help       ","Your looking at it! :)                                            ", ""},
            {"exit       ","Exits the application                                             ", ""}
    };
    //Define the tests
    public static String[][] testSubstrs = {
            {"add","testset1:{1,1}"},
            {"add","testset2:{1,2,2,3}"},
            {"add","testset3:{1,3,4}"},
            {"add","testset4:{1}"},
            {"add","testset5:{1}"},
            {"add","testset6:{1,2,3,4}"},
            {"add","testset7:{3,4,5,6}"},
            {"list"},
            {"remove","testset3", "2"},
            {"list","testset3"},
            {"tremove","testset3","3"},
            {"list","testset3"},
            {"remove","testset3"},
            {"list",""},
            {"equal","testset1","testset4", "testset5"},
            {"find","testset2","2"},
            {"intersect","testset6", "testset7", "y"},
            {"list",""},
            {"union","testset6", "testset7", "y"},
            {"list",""},
            {"subtract","testset6", "testset7", "y"},
            {"list",""},
            {"subtract","testset7", "testset6", "y"},
            {"list",""},
            {"difference","testset6", "testset7", "y"},
            {"list",""},
            {"reset","y"},
            {"list",""}
    };

    /**
     * Utility method to get a Set by its name
     * @param   name      Name of the string to get
     * @return            The set that was found, or null if no set was found
     */
    public static Set<Integer> getSetByName(String name){
        name = name.toLowerCase();
        for(int i=0;i<sets.size();i++){
            if (sets.get(i).getName().equals(name)){
                return sets.get(i);
            }
        }
        return null;
    }
    /**
     * Utility method to get a Sets index by its name
     * @param   name      Name of the string to get
     * @return            The index of the Set that was found, or -1 if no set was found
     */
    public static Integer getSetIndex(String name){
        name = name.toLowerCase();
        for(int i=0;i<sets.size();i++){
            if (sets.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }
    /**
     * Parse a string and create a Set<Integer> from it
     *
     * @param   text        String to parse and create Set from
     * @return              Set<Integer> created from the String
     */
    public static Set<Integer> parse(String text){
        String[] substrs = text.split(":");
        String name = "";
        //if there are more then 1 substrs then name and set are included
        if (substrs.length > 1){
            name = substrs[0].toLowerCase();
            Set<Integer> set = parseSetText(substrs[1]);
            set.setName(name);
            return set;
        }else{
            //There is only one, figure out if its the name or the Set
            if (substrs[0].matches("\\{([0-9]+(,|\\}))*")){
                //This is a set so parse it
                return parseSetText(substrs[0]);
            }else{
                return new Set<Integer>(substrs[0]);
            }
        }
    }

    /**
     * Utility function to create a set from set text
     * @param text      The Set text e.g. {1,2,3,4}
     * @return          The Set that is created
     */
    public static Set<Integer> parseSetText(String text){
        Set<Integer> set = new Set<Integer>();
        String[] substrs = text.replaceAll("[\\[\\]\\{\\}]", "").split(",");
        try{
            for(int i=0;i<substrs.length;i++){
                set.add(Integer.parseInt(substrs[i].trim()));
            }
        }catch(NumberFormatException e){
            //TODO: Show Error message create parse exception
            System.out.println("Error Parsing Integer ");
        }
        return set;
    }

    /**
     * Add a Set to the environment
     *
     * @param   set         The Set to add
     * @return              True/False, Whether the add was successful or not
     */
    public static boolean addSet(Set<Integer> set){
        if (sets.add(set) == 1){
            return true;
        }
        return false;
    }

    /**
     * Remove a Set from the Environment
     *
     * @param   setName     Name of the Set to remove
     * @return              True/False, Whether the remove was successful or not
     */
    public static boolean removeSet(String setName){
        Integer index = getSetIndex(setName);
        if (index != -1){
            sets.remove(index);
            return true;
        }
        return false;
    }
    /**
     * Remove an Element from a Set
     *
     * @param   setName     Name of the Set to remove the element from
     * @param   index       The index of the element to remove
     * @return              True/False, Whether the remove was successful or not
     */
    public static boolean removeItem(String setName, int index){
        Set<Integer> set = getSetByName(setName);
        if (set != null){
            set.remove(index);
            return true;
        }
        return false;
    }
    /**
     * Remove an Element from a Set
     *
     * @param   setName     Name of the Set to remove the element from
     * @param   target      The item to remove from the set
     * @return              True/False, Whether the remove was successful or not
     */
    public static boolean tRemove(String setName, Object target){
        Set<Integer> set = getSetByName(setName);
        if (set != null && target instanceof Integer){
            set.remove((Integer) target);
            return true;
        }
        return false;
    }
    /**
     * List the Set Specified
     * @param   setName     Name of the Set to list out
     * @return              String representation of the Set
     */
    public static String list(String setName){
        Set<Integer> s = getSetByName(setName);
        if (s != null){
            return s.toString();
        }
        return "Set with name " + setName + " Not Found!";
    }
    /**
     * List all of the Sets in the environment
     *
     * @return              String representation of all of the Sets
     */
    public static String list(){
        return sets.toString();
    }
    /**
     * Intersect two Sets, returning a Set containing common elements between the two
     *
     * @param setName1      The name of the first Set to intersect
     * @param setName2      The name of the second Set to intersect
     * @return              A Set containing the common elements of the two Sets specified
     */
    public static Set intersect(String setName1, String setName2){
        Set<Integer> s1 = getSetByName(setName1);
        Set<Integer> s2 = getSetByName(setName2);
        if (s1 != null && s2 != null){
            return s1.intersect(s2);
        }
        return null;
    }
    /**
     * Join the two Sets, returning a Set containing all the elements in the two Sets
     *
     * @param setName1      The name of the first Set to join
     * @param setName2      The name of the second Set to join
     * @return              A Set containing all the elements in the two specified Sets
     */
    public static Set union(String setName1, String setName2){
        Set<Integer> s1 = getSetByName(setName1);
        Set<Integer> s2 = getSetByName(setName2);
        if (s1 != null && s2 != null){
            return s1.union(s2);
        }
        return null;
    }
    /**
     * Subtract the two Sets, returning a Set containing the elements
     *  that are not in the second set, but are in the first
     *
     * @param setName1      The name of the first Set to be subtracted from
     * @param setName2      The name of the second Set subtract from the first
     * @return              A Set containing all elements that are in the first but not the second Set
     */
    public static Set subtract(String setName1, String setName2){
        Set<Integer> s1 = getSetByName(setName1);
        Set<Integer> s2 = getSetByName(setName2);
        if (s1 != null && s2 != null){
            return s1.subtract(s2);
        }
        return null;
    }
    /**
     * Difference the two Sets, returning a Set containing the elements
     *  that are not in both Sets
     *
     * @param setName1      The name of the first Set to difference
     * @param setName2      The name of the second Set  to difference
     * @return              A Set containing the elements that are not in both Sets
     */
    public static Set difference(String setName1, String setName2){
        Set<Integer> s1 = getSetByName(setName1);
        Set<Integer> s2 = getSetByName(setName2);
        if (s1 != null && s2 != null){
            return s1.difference(s2);
        }
        return null;
    }
    /**
     * Find the target item in the set
     *
     * @param setName      The name of the first Set to difference
     * @param target       The item to find in the set
     * @return             The index of the item, or -1 if the item is not found
     */
    public static int find(String setName, Object target){
        Set<Integer> s = getSetByName(setName);
        if (s != null && target instanceof Integer){
            return s.indexOf((Integer) target);
        }
        return -1;
    }
    /**
     * Compare two Sets to see if they are equal
     *
     * @param    names      The names of the Sets to compare
     * @return              True if the two Sets are equal, or False if they are not
     */
    public static boolean equal(String[] names){
        ArrayList<Set> compsets = new ArrayList<Set>();
        Set<Integer> c;
        //First get all of the sets we want to compare and add them to the compsets ArrayList
        for(int i=0; i <names.length-1;i++){
            c = getSetByName(names[i+1]);
            if (c != null){
                compsets.add(c);
            }
        }
        c = compsets.get(0);
        //Go through the compsets ArrayList and compare it to the last Set
        for(int i=1; i <compsets.size();i++){
            Set<Integer> a = c;
            c = compsets.get(i);
            if (a.equals(c)){
                //If this set is not equal to the last one then they are not all equal, return false
                return false;
            }
        }
        return true;
    }
    /**
     * Save the environment to file
     *
     * @return              True if the save was successful
     */
    public static boolean saveSetsToFile(){
        try{
            PrintWriter out = new PrintWriter(new FileWriter("setsave"));
            Iterator si = sets.iterator();
            while(si.hasNext()){
                Set<Integer> s = (Set<Integer>) si.next();
                out.println(s.toString());
                System.out.println("Successfully Saved Set " + s.getName());
            }
            out.close();
        }catch (IOException e){
            System.out.println("Failed To Save Sets. IOException Occured");
        }
        return false;
    }
    /**
     * Load the environment from a file
     *
     * @return              True if the load was successful
     */
    public static boolean loadSetsFromFile(){
        try{
            BufferedReader in = new BufferedReader(new FileReader("setsave"));
            String line = in.readLine();
            while(line != null){
                Set<Integer> n = parse(line);
                if (n !=null){
                    if (addSet(n)){
                        System.out.println("Successfully Restored Set " + n.getName());
                    }else{
                        System.out.println("Failed to Restore Set " + n.getName());
                    }
                }
                line = in.readLine();
            }
            in.close();
        }catch (FileNotFoundException e){
            System.out.println("Failed to Restore. Save File Not Found");
        }catch(IOException e){
            System.out.println("Failed to Restore. IOException Occurred");
        }
        return false;
    }
    public static boolean saveFileExists(){
        File f = new File("setsave");
        if(f.exists() && !f.isDirectory()) {
            return true;
        }
        return false;
    }

    /**
     * Get Input from the user and handle it
     * @return          Boolean, true to exit the application
     */
    public static boolean getUserInput(){
        System.out.print("Enter a command > ");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine().toLowerCase();
        String[] substrs = text.split(" ");
        return handleUserInput(substrs);
    }

    /**
     * Handle the action
     * @param substrs
     * @return
     */
    public static boolean handleUserInput(String[] substrs){
        String action = substrs[0].toLowerCase();
        Set<Integer> set = new Set<Integer>();
        if (substrs.length >1 ){
            set = parse(substrs[1]);
        }
        if(action.equals("add")){
            if (substrs.length >1 ){
                System.out.println("Adding- " + set.toString() + "...");
                if(addSet(set)){
                    System.out.println("Set " + set.toString() + " Added to Environment!");
                }else{
                    System.out.println("Set " + set.toString() + " Not Added. Duplicate Name!");
                }
            }else{
                System.out.println("Invalid Arguments for Add action. Required Format: <action (add)> <SetName>:<Set ({1,2,3)>" );
            }

        }else if(action.equals("remove")){
            if (substrs.length >1 ){
                if (substrs.length >2){
                    //An Index is included
                    try{
                        int index = Integer.parseInt(substrs[2]);
                        if(removeItem(set.getName(),index)){
                            System.out.println("Item at Index " + index + " in Set " + set.getName() + " Removed!");
                        }else{
                            System.out.println("Item at Index " + index + " in Set " + set.getName() + " could not be removed!");
                        }
                    }catch(NumberFormatException e){
                        System.out.println("Error Parsing index of the item! Required Format: <Action (remove)> <SetName> <Index of element to remove(Optional)> ");
                    }
                }else{
                    //There is no Index, Remove the set
                    System.out.println("Removing- " + set.getName() + "...");
                    if(removeSet(set.getName())){
                        System.out.println("Set " + set.getName() + " Removed from Environment!");
                    }else{
                        System.out.println("Set " + set.toString() + " Not Removed. Couldn't find Set!");
                    }
                }
            }else{
                System.out.println("Invalid Arguments for Remove action. Required: <action (remove)> <SetName> <Index of element to remove(Optional)>" );
            }
        }else if(action.equals("tremove")){
            if (substrs.length >1 && substrs.length >2){
                //An Index is included
                try{
                    int target = Integer.parseInt(substrs[2]);
                    if(tRemove(set.getName(), target)){
                        System.out.println("Item " + target + " in Set " + set.getName() + " Removed!");
                    }else{
                        System.out.println("Item " + target + " in Set " + set.getName() + " could not be Removed!");
                    }
                }catch(NumberFormatException e){
                    System.out.println("Error Parsing index of the item! Required Format: <Action (remove)> <SetName> <Index of element to remove(Optional)> ");
                }
            }else{
                System.out.println("Invalid Arguments for TRemove action. Required: <action (tremove)> <SetName> <Target to remove (3)>" );
            }
        }else if(action.equals("list")){
            if (substrs.length >1 && !substrs[1].equals("")){
                System.out.println("Listing Set " + substrs[1]);
                System.out.println(list(substrs[1]));
            }else{
                System.out.println("Listing All Sets:");
                System.out.println(list());
            }
        }else if(action.equals("intersect")){
            if (substrs.length >2 ){
                System.out.println("Intersecting- " + substrs[1] + " and " + substrs[2] + "...");
                Set<Integer> s = intersect(substrs[1],substrs[2]);
                if(s != null){
                    System.out.println("Result of Intersect..." + s.toString());
                    System.out.println("Would you like to add Set " + s.getName() + " To the environment? : y/n");
                    String text = "";
                    if (substrs.length >3 && substrs[3].equals("y")){
                        text = "y";
                    }else{
                        boolean input = true;
                        while(input){
                            Scanner scanner = new Scanner(System.in);
                            try{
                                text = scanner.nextLine().toLowerCase();
                                input = false;
                            }catch(Exception e){
                                System.out.println("Error reading input, Please Try again");
                            }
                        }
                    }
                    if (text.equals("y")){
                        if(addSet(s)){
                            System.out.println("Set " + s.toString() + " Added to Environment!");
                        }else{
                            System.out.println("Set " + s.toString() + " Not Added. Duplicate Name!");
                        }
                    }
                }else{
                    System.out.println("Intersection Failed!");
                }
            }else{
                System.out.println("Invalid Arguments for Intersect action. Required Format: <action (intersect)> <SetName> <SetName>" );
            }
        }else if(action.equals("union")){
            if (substrs.length >2 ){
                System.out.println("Union- " + substrs[1] + " and " + substrs[2] + "...");
                Set<Integer> s = union(substrs[1], substrs[2]);
                if(s != null){
                    System.out.println("Result of Union..." + s.toString());
                    System.out.println("Would you like to add Set " + s.getName() + " To the environment? : y/n");
                    String text = "";
                    if (substrs.length >3 && substrs[3].equals("y")){
                        text = "y";
                    }else{
                        boolean input = true;
                        while(input){
                            Scanner scanner = new Scanner(System.in);
                            try{
                                text = scanner.nextLine().toLowerCase();
                                input = false;
                            }catch(Exception e){
                                System.out.println("Error reading input, Please Try again");
                            }
                        }
                    }
                    if (text.equals("y")){
                        if(addSet(s)){
                            System.out.println("Set " + s.toString() + " Added to Environment!");
                        }else{
                            System.out.println("Set " + s.toString() + " Not Added. Duplicate Name!");
                        }
                    }
                }else{
                    System.out.println("Union Failed!");
                }
            }else{
                System.out.println("Invalid Arguments for Union action. Required Format: <action (union)> <SetName> <SetName>" );
            }
        }else if(action.equals("subtract")){
            if (substrs.length >2 ){
                System.out.println("Subtracting- " + substrs[1] + " and " + substrs[2] + "...");
                Set<Integer> s = subtract(substrs[1], substrs[2]);
                if(s != null){
                    System.out.println("Result of Subtraction..." + s.toString());
                    System.out.println("Would you like to add Set " + s.getName() + " To the environment? : y/n");
                    String text = "";
                    if (substrs.length >3 && substrs[3].equals("y")){
                        text = "y";
                    }else{
                        boolean input = true;
                        while(input){
                            Scanner scanner = new Scanner(System.in);
                            try{
                                text = scanner.nextLine().toLowerCase();
                                input = false;
                            }catch(Exception e){
                                System.out.println("Error reading input, Please Try again");
                            }
                        }
                    }
                    if (text.equals("y")){
                        if(addSet(s)){
                            System.out.println("Set " + s.toString() + " Added to Environment!");
                        }else{
                            System.out.println("Set " + s.toString() + " Not Added. Duplicate Name!");
                        }
                    }
                }else{
                    System.out.println("Subtraction Failed!");
                }
            }else{
                System.out.println("Invalid Arguments for Subtraction action. Required Format: <action (subtract)> <SetName> <SetName>" );
            }
        }else if(action.equals("difference")){
            if (substrs.length >2 ){
                System.out.println("Differencing- " + substrs[1] + " and " + substrs[2] + "...");
                Set<Integer> s = difference(substrs[1], substrs[2]);
                if(s != null){
                    System.out.println("Result of Difference..." + s.toString());
                    System.out.println("Would you like to add Set " + s.getName() + " To the environment? : y/n");
                    String text = "";
                    if (substrs.length >3 && substrs[3].equals("y")){
                        text = "y";
                    }else{
                        boolean input = true;
                        while(input){
                            Scanner scanner = new Scanner(System.in);
                            try{
                                text = scanner.nextLine().toLowerCase();
                                input = false;
                            }catch(Exception e){
                                System.out.println("Error reading input, Please Try again");
                            }
                        }
                    }
                    if (text.equals("y")){
                        if(addSet(s)){
                            System.out.println("Set " + s.toString() + " Added to Environment!");
                        }else{
                            System.out.println("Set " + s.toString() + " Not Added. Duplicate Name!");
                        }
                    }
                }else{
                    System.out.println("Difference Failed!");
                }
            }else{
                System.out.println("Invalid Arguments for Difference action. Required Format: <action (difference)> <SetName> <SetName>" );
            }
        }else if(action.equals("find")){
            if (substrs.length >2 ){
                //An Index is included
                try{
                    int target = Integer.parseInt(substrs[2]);
                    int index = find(set.getName(), target);
                    if( index != -1){
                        System.out.println("Item " + target + " in Set " + set.getName() + " is at index " + index);
                    }else{
                        System.out.println("Item " + target + " in Set " + set.getName() + " could not be found!");
                    }
                }catch(NumberFormatException e){
                    System.out.println("Error Parsing target item!  Required Format: <action (find)> <SetName> <Target to find (3)> ");
                }
            }else{
                System.out.println("Invalid Arguments for find action. Required Format: <action (find)> <SetName> <Target to find (3)>" );
            }
        }else if(action.equals("equal")){
            if (substrs.length >2){
                System.out.print("Sets ");
                for(int i=0; i <substrs.length-2;i++){
                    System.out.print(substrs[i + 1] + ", ");
                }
                System.out.print("and " + substrs[substrs.length-1]);
                if (equal(substrs)){
                    System.out.println(" Are all Equal!");
                }else{
                    System.out.println(" Are not Equal!");
                }
            }
        }else if(action.equals("test")){
            System.out.println("Executing Tests.........\n==================================================================================");
            for (int i=0;i<testSubstrs.length;i++){
                System.out.println("Test #" + (i + 1) + " out of " + (testSubstrs.length) + "   Testing: " + testSubstrs[i][0] + "\n===================================================================================");
                handleUserInput(testSubstrs[i]);
            }
        }else if(action.equals("reset")){
            String text = "";
            if(substrs[1].equals("y")){ //allow for testability
                System.out.println("Clearing All Sets From The Environment...");
                text ="y";
            }else{
                System.out.println("Are You Sure You Want To Clear All Sets From The Environment?: y/n");
                Scanner scanner = new Scanner(System.in);
                text = scanner.nextLine().toLowerCase();
            }
            if(text.equals("y")){
                sets.clear();
            }

        }else if(action.equals("help")){
            System.out.println("Help:\n==========================================================================================================");
            System.out.println("Command\t\tFormat                                                              Notes\n==========================================================================================================");
            for(int i=0; i <help.length;i++){
                System.out.println(help[i][0] + "\t" + help[i][1] +"\t" + help[i][2]);
            }
        }else if(action.equals("exit")){
            System.out.println("Would You Like to Save The Sets in the Environment to file? : y/n");
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine().toLowerCase();
            if(text.equals("y")){
                saveSetsToFile();
            }
            System.out.println("Exiting Application...");
            return true;
        }else{
            System.out.println(doubleVision("hello"));
            System.out.println(action + " is not a valid command");
        }
        return false;
    }
    public static String doubleVision(String text){
        if (text.length() == 1){
            return text+text;
        }else{
            String f = text.substring(0,1);
            f += text.substring(0,1);
            f += doubleVision(text.substring(1));
            return f;
        }
    }


    /**
     * Main Method
     * @param args          Command line args for the program
     */
    public static void main(String[] args) {
        //String[] substrs = {"test"};
        //handleUserInput("test", substrs);
        if (saveFileExists()){
            System.out.println("Would You Like to Load The Sets into the Environment from file? : y/n");
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine().toLowerCase();
            if(text.equals("y")){
                loadSetsFromFile();
            }
        }
        System.out.println("Use the Command \"test\" to see a demonstration of all commands");
        boolean quit = false;
        while(!quit){
            quit = getUserInput();
        }
        System.out.println("Made By Nick Bolles on 3/15/2015 for UW Whitewater Class CompSci 223 \"Data Structures\"");
    }

}
