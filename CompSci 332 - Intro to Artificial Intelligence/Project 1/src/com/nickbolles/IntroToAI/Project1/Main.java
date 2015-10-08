package com.nickbolles.IntroToAI.Project1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static boolean debug = false;
    public static String lastCity;
    public static String firstCity;
    public static String mapFileName = "com/nickbolles/IntroToAI/Project1/nbMap.txt";

    public static void debugMessage(String message) {
        if (debug) {
            System.out.println(message);
        }
    }

    public static void main(String[] args) {
        boolean quit = false;

        CityList cityList = loadMap();
        System.out.println("==================================================");
        if (debug) {
            System.out.println("Printing City List");
            System.out.println(cityList.toString());
            System.out.println("DONE!");
        }
        cityList.PrintCityList();
        System.out.println(cityList.getNumCities() + " Cities Available");
        System.out.println("==================================================");

        System.out.println("Would you like to find the shortest and longest paths now? Enter n or N to skip");
        System.out.println("Warning this will fail if there are too many cities");
        Scanner s = new Scanner(System.in);
        String t = s.nextLine().toLowerCase();
        if (!t.equals("n") && !t.equals("N")) {
            PathFinder pf = new PathFinder(cityList);
            try{
                pf.findLongestPath(debug);
            }catch (StackOverflowError e){
                System.out.println("StackOverflowError! Too many cities to perform shortest/longest calculations");
            }
        }
        String depart = firstCity;
        String dest = lastCity;
        while (!quit) {
            String departPrompt = "Please Enter a City to start from, Or just press \"Enter\" to use \"" + depart + "\"";
            String destPrompt = "Please Enter a City to go to, Or just press \"Enter\" to use \"" + dest + "\"";
            depart = GetValidCityInput(departPrompt, cityList, depart);
            dest = GetValidCityInput(destPrompt, cityList, dest);
            System.out.println("==================================================");
            SearchForRoute(cityList, depart, dest);
            System.out.println("==================================================");
            System.out.println("Find Another Route? Enter n or N to exit");
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine().toLowerCase();
            if (text.equals("n") || text.equals("N")) {
                quit = true;
            }
        }
    }

    /**
     * Prompts the user for a map file name and create a city list from it. The map file should contain
     * entries in the "<From City> <To City>" format
     *
     * @return A new CityList that is created
     */
    public static CityList loadMap() {
        CityList cityList = new CityList();
        int totalConnections = 0;
        boolean loadingMap = true;
        while (loadingMap) {
            String tempMapFileName = "";
            System.out.println("Please Enter map file name, or click \"Enter\" to use " + mapFileName);
            Scanner scanner = new Scanner(System.in);
            tempMapFileName = scanner.nextLine().toLowerCase();
            if (tempMapFileName.equals("")) {
                debugMessage("Using default map of " + mapFileName);
                tempMapFileName = mapFileName;
            }
            try {
                BufferedReader in = new BufferedReader(new FileReader(tempMapFileName));
                String line = in.readLine();
                while (line != null) {
                    String[] words = line.split(" ");
                    //first get or create this city
                    City thisCity = cityList.getOrAddCity(words[0]);
                    if (firstCity == null) {
                        firstCity = thisCity.getName();
                    }
                    //copy the destinations to a new array
                    String[] dests = Arrays.copyOfRange(words, 1, words.length);
                    debugMessage("Reading line " + line);
                    ArrayList<City> cityDests = new ArrayList<City>();
                    //Create or get the City for each destination
                    for (String destName : dests) {
                        debugMessage("Adding Destination " + destName + " to " + thisCity.getName());
                        City dest = cityList.getOrAddCity(destName);
                        cityDests.add(dest);
                    }
                    thisCity.addDestinations(cityDests);
                    totalConnections += thisCity.getDestinations().getNumCities();
                    lastCity = dests[dests.length - 1];
                    line = in.readLine();
                }
                loadingMap = false;
                mapFileName = tempMapFileName;
                System.out.println("Map loaded!");
                System.out.println(totalConnections + " Connections loaded!");
            } catch (FileNotFoundException e) {
                System.out.println("Map File Not Found");
            } catch (IOException e) {
                System.out.println("IOExeption " + e.toString());
            }
        }
        return cityList;
    }

    /**
     * Searches for a route using Breadth First Search and Depth First Search using the PathFinder class.
     * The Supplied CityList and depart city name and dest(destination) city name are passed into the PathFinder
     *
     * @param cityList
     * @param depart
     * @param dest
     */
    public static void SearchForRoute(CityList cityList, String depart, String dest) {
        System.out.println("Searching for route from " + depart + " to " + dest);
        PathFinder pf = new PathFinder(cityList);
        System.out.print("BFS: ");
        try {
            ArrayList<City> result = pf.findPath(depart, dest, false, debug);
            System.out.println();
            if (result == null) {
                System.out.println("No path from " + depart + " to " + dest);
                System.out.println("Keep in mind that destinations are only one way");
            } else {
                System.out.print(result.size() + " Cities Visited: ");
                for (City city : result) {
                    System.out.print(city.getName() + "  ");
                }
            }
            System.out.println();
        } catch (NoSuchCity e) {
            System.out.println(e.toString());
        }
        System.out.print("DFS: ");
        try {
            ArrayList<City> result = pf.findPath(depart, dest, true, debug);
            System.out.println();
            if (result == null) {
                System.out.println("No path from " + depart + " to " + dest);
                System.out.println("Keep in mind that destinations are only one way");
            } else {
                System.out.print(result.size() + " Cities Visited: ");
                for (City city : result) {
                    System.out.print(city.getName() + "  ");
                }
            }
            System.out.println();
        } catch (NoSuchCity e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Utility function to easily validate a City name. The User is prompted with the message parameter
     * and the City is checked against the cityList Parameter to ensure that it is valid. The Default City
     * parameter is used in the case that the users input is blank.
     *
     * @param message     Message to prompt the user for input
     * @param cityList    The CityList to check the entered City Name against
     * @param defaultCity The Default value if the User Entry is ""
     * @return
     */
    public static String GetValidCityInput(String message, CityList cityList, String defaultCity) {
        boolean valid = false;
        while (!valid) {
            System.out.println(message);
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine().toLowerCase();
            if (text.equals("")) {
                text = defaultCity;
            }
            if (text.length() > 0 && cityList.hasCity(text)) {
                defaultCity = text;
                valid = true;
            }
        }
        return defaultCity;
    }


}
