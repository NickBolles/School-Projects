package com.nickbolles.IntroToAI.Project1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Nicholas on 9/23/2015.
 * <p/>
 * Class to find a path from one City to another City given a CityList
 */
public class PathFinder {
    private CityList list;
    private Queue<City> queue = new LinkedList<City>();

    /**
     * Constructor to create a PathFinder from a CityList
     *
     * @param list
     */
    PathFinder(CityList list) {
        this.list = list;
    }

    /**
     * utility method to output debug strings only when debug is true
     *
     * @param debug
     * @param message
     */
    public void debugMessage(boolean debug, String message) {
        if (debug) {
            System.out.println(message);
        }
    }

    /**
     * Entry to find a path from "depart" to "dest" This will convert the strings depart and dest
     * to City Instances and then call findPathHelper with the starting CityList and starting City
     * and destination City
     *
     * @param depart
     * @param dest
     * @param DF
     * @param debug
     * @return
     * @throws NoSuchCity
     */
    public ArrayList<City> findPath(String depart, String dest, boolean DF, boolean debug) throws NoSuchCity {
        City departure = this.list.getCity(depart);
        City destination = this.list.getCity(dest);
        if (departure == null) {
            throw new NoSuchCity(depart);
        }
        if (destination == null) {
            throw new NoSuchCity(dest);
        }
        long startTime = System.nanoTime();
        ArrayList<City> resultPath = findPathHelper(departure.getDestinations(), new ArrayList<City>(), departure, destination, DF, debug);
        long endTime = System.nanoTime() - startTime;
        System.out.print(((double) endTime / 1000000) + "ms");
        resultPath.add(0, departure);
        return resultPath;
    }

    /**
     * Helper function for findPath. This takes a CityList and City instances for depart and dest
     * opposed to strings.
     *
     * @param cl
     * @param path
     * @param depart
     * @param dest
     * @param DF
     * @param debug
     * @return
     */
    public ArrayList<City> findPathHelper(CityList cl, ArrayList<City> path, City depart, City dest, boolean DF, boolean debug) {
        for (City city : cl.getCities()) {
            debugMessage(debug, "Visiting City: " + city.getName());
            if (path.indexOf(city) != -1 || city.getDestinations().getNumCities() == 0) {
                //This city is already in the path or does not have any destinations, to avoid a loop skip this city
                continue;
            } else if (city.hasDestination(dest)) {
                //Done! we found the destination, return it
                path.add(city);
                path.add(dest);
                return path;
            } else {
                //Otherwise continue searching
                if (DF) {
                    //if we are doing a depth first search search this city right now
                    debugMessage(debug, "Doing Depth First Search, searching " + city.getName() + " now");
                    path.add(city);
                    ArrayList<City> resultPath = findPathHelper(city.getDestinations(), path, depart, dest, DF, debug);
                    if (resultPath != null) {
                        //A path was found. Add this city to the path and return it
                        return resultPath;
                    } else {
                        path.remove(city);
                    }
                } else {
                    //If we are doing a Breadth first search add this city to the queue to be dug into
                    debugMessage(debug, "Doing Breadth First Search, Adding " + city.getName() + " to queue");
                    queue.offer(city);
                }
            }
        }
        //search the queue for a path
        while (queue.peek() != null) {
            City city = queue.remove();
            ArrayList<City> resultPath = findPathHelper(city.getDestinations(), path, depart, dest, DF, debug);
            if (resultPath != null) {
                //A path was found. Add this city to the path and return it
                path.add(0, city);
                return path;
            }
        }
        return null;
    }

    public void findLongestPath(Boolean debug){
        ArrayList<City> minBFS = null,
                maxBFS = null,
                minDFS = null,
                maxDFS = null;
        for (City city: this.list){
            for (City dest: this.list){
                if (city.equals(dest)){
                    debugMessage(debug, city.getName() + " skipping destination " + dest.getName() + " It's the same");
                    continue;
                }
                debugMessage(debug, "Doing BFS from " + city.getName() + " to " + dest.getName());
                //do a BFS
                ArrayList<City> resultBF = findPathHelper(city.getDestinations(), new ArrayList<City>(), city, dest, false, false);
                if (resultBF != null){
                    if (minBFS == null){
                        minBFS = resultBF;
                    }
                    if (maxBFS == null){
                        maxBFS = resultBF;
                    }
                    if (minBFS.size() > resultBF.size()){
                        minBFS = resultBF;
                    }
                    if (maxBFS.size() < resultBF.size()){
                        maxBFS = resultBF;
                    }
                }else{
                    debugMessage(debug, "There is no BFS route from " + city.getName() + " to " + dest.getName() );
                }
                debugMessage(debug, "Doing DFS from " + city.getName() + " to " + dest.getName());
                //do a DFS
                ArrayList<City> resultDF = findPathHelper(city.getDestinations(), new ArrayList<City>(), city, dest, false, true);
                if (resultBF != null) {
                    if (minDFS == null) {
                        minDFS = resultDF;
                    }
                    if (maxDFS == null) {
                        maxDFS = resultDF;
                    }
                    if (minDFS.size() > resultDF.size()) {
                        minDFS = resultDF;
                    }
                    if (maxDFS.size() < resultDF.size()) {
                        maxDFS = resultDF;
                    }
                }else{
                    debugMessage(debug, "There is no DFS route from " + city.getName() + " to " + dest.getName() );
                }
            }
        }
        System.out.println("Breadth First Search results: ");
        System.out.println("Min Path is from  " + minBFS.get(0).getName() + " to " + minBFS.get(minBFS.size()-1).getName() + "   with " + minBFS.size() + " connections");
        System.out.println("Max Path is from  " + maxBFS.get(0).getName() + " to " + maxBFS.get(maxBFS.size()-1).getName() + "   with " + maxBFS.size() + " connections");
        System.out.println("Depth First Search results: ");
        System.out.println("Min Path is from  " + minDFS.get(0).getName() + " to " + minDFS.get(minDFS.size()-1).getName() + "   with " + minDFS.size() + " connections");
        System.out.println("Max Path is from  " + maxDFS.get(0).getName() + " to " + maxDFS.get(maxDFS.size()-1).getName() + "   with " + maxDFS.size() + " connections");
    }
}
