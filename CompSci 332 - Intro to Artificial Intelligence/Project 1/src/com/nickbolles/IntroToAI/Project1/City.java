package com.nickbolles.IntroToAI.Project1;

import java.util.ArrayList;

/**
 * Created by Nicholas on 9/23/2015.
 * <p/>
 * This class creates a City with a name and list of destinations
 *
 * @author Nicholas Bolles
 */
public class City {
    /**
     * Name for the City. This will always be lowercase
     */
    private String name;
    /**
     * List of destinations for the City
     */
    private CityList destinations;

    /**
     * Class Constructor requiring a name argument at least
     *
     * @param name
     */
    City(String name) {
        this.name = name.toLowerCase();
        this.destinations = new CityList();
    }

    /**
     * Class Constructor with a name and an Arraylist<{@link City}> of destinations
     *
     * @param name
     * @param destinations
     */
    City(String name, ArrayList<City> destinations) {
        this.name = name.toLowerCase();
        this.destinations = new CityList(destinations);
    }

    /**
     * Class Constructor with a name and a CityList of destinations
     *
     * @param name
     * @param destinations
     */
    City(String name, CityList destinations) {
        this.name = name.toLowerCase();
        this.destinations = destinations;
    }


    /**
     * Get the name of this City
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the City
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the CityList of destinations.
     *
     * @return Value of destinations.
     */
    public CityList getDestinations() {
        return destinations;
    }

    /**
     * Sets new the new destinations.
     *
     * @param destinations New value of destinations.
     */
    public void setDestinations(ArrayList<City> destinations) {
        this.destinations = new CityList(destinations);
    }

    /**
     * Add an ArrayList of destinations to the City
     *
     * @param destinations
     */
    public void addDestinations(ArrayList<City> destinations) {
        //Make sure that we aren't trying to make a destination of ourselves
        int idx = destinations.indexOf(this);
        if (idx != -1) {
            //that doesn't make sense silly! Remove it from the destinations
            destinations.remove(idx);
        }
        this.destinations.addCities(destinations);
    }

    /**
     * Add a single City as a destination from this City
     *
     * @param destination
     */
    public void addDestination(City destination) {
        if (!this.hasDestination(destination)) {
            this.destinations.addCity(destination);
        }
    }

    /**
     * Check to see if this City has a destination of the City
     *
     * @param destination
     * @return
     */
    public boolean hasDestination(City destination) {
        if (this.destinations.hasCity(destination.getName())) {
            return true;
        }
        return false;
    }

    /**
     * Check to see if this City has a destination of the city name
     *
     * @param destination
     * @return
     */
    public boolean hasDestination(String destination) {
        if (this.destinations.hasCity(destination)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;
        return this.name.equals(city.name);
    }

    @Override
    public String toString() {
        String str = this.name + "\r\nDestinations:\r\n";
        String pre = "  ";
        for (City dest : this.destinations) {
            str += pre + dest.toString();
        }
        return str;
    }

    /**
     * Create a string that is indented for a treeview
     *
     * @param indent
     * @return
     */
    public String toStringIndent(String indent) {
        String pre = indent + "    ";
        String str = indent + this.name + "  -  ";
        str += this.destinations.getNumCities() + " Destinations";
        str += ":\r\n";
        for (City dest : this.destinations) {
            str += indent + "  " + dest.getName() + "\r\n";
        }
        return str;
    }


}
