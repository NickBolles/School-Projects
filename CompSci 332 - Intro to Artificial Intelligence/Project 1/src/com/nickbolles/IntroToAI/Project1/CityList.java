package com.nickbolles.IntroToAI.Project1;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Nicholas on 9/23/2015.
 * <p/>
 * This Class creates a list of City's and functions to manipulate the list
 *
 * @author Nicholas Bolles
 */
public class CityList implements Iterable<City> {
    private ArrayList<City> cities = new ArrayList<City>();

    /**
     * Default constructor
     */
    CityList() {
        super();
    }

    /**
     * Constructor with a starting ArrayList<{@link City}>
     *
     * @param cities
     */
    CityList(ArrayList<City> cities) {
        super();
        this.setCities(cities);
    }

    /**
     * Get the raw ArrayList<{@link City}> of cities
     *
     * @return cities, the raw ArrayList<{@link City}>
     */
    public ArrayList<City> getCities() {
        return this.cities;
    }

    /**
     * Set the raw ArrayList<{@link City}> of cities
     *
     * @param cities
     */
    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    /**
     * Get a City by name
     *
     * @param name String that we are searching for in this CityList
     * @return City that corresponds to the name
     */
    public City getCity(String name) {
        Iterator<City> iterator = cities.iterator();
        while (iterator.hasNext()) {
            City city = iterator.next();
            if (city.getName().toLowerCase().equals(name.toLowerCase())) {
                return city;
            }
        }
        return null;
    }

    /**
     * Add a single City to this CityList
     *
     * @param city The City to add to the list
     */
    public void addCity(City city) {
        cities.add(city);
    }

    /**
     * Add a collection of cities to the CityList. This will also ensure that there are no duplicates
     *
     * @param cities an Arraylist<{@link City}> of cities to add to this CityList
     */
    public void addCities(ArrayList<City> cities) {
        //Remove the duplicates
        checkCityListForDupes(cities);
        //Add it to the cities ArrayList
        this.cities.addAll(cities);
    }

    /**
     * Helper function for addCities to remove any duplicates
     *
     * @param cities
     */
    private void checkCityListForDupes(ArrayList<City> cities) {
        for (Iterator<City> it = cities.iterator(); it.hasNext(); ) {
            City dest = it.next();
            if (this.hasCity(dest.getName())) {
                it.remove(); //Calling cities.remove(dest) here causes a ConcurrentModificationException,
                // This can be avoided by explicitly using an iterator instead of a for-each loop and calling the
                // iterator.remove() function instead
            }
        }
    }

    /**
     * Get an existing City or create the City
     *
     * @param name Name of the City to create or get
     * @return The Existing, or created City
     */
    public City getOrAddCity(String name) {
        City dest = this.getCity(name);
        if (dest != null) {
            return dest;
        } else {
            City newCity = new City(name);
            this.addCity(newCity);
            return newCity;
        }
    }

    /**
     * Check to see if a city with the a name is in the CityList
     *
     * @param name
     * @return
     */
    public boolean hasCity(String name) {
        return this.getCity(name.toLowerCase()) != null;
    }

    /**
     * Get the number of cities in the CityList
     *
     * @return The number of cities in the CityList
     */
    public int getNumCities() {
        return this.cities.size();
    }

    /**
     * Print a list of city names that are in this CityList
     */
    public void PrintCityList() {
        String str = "Cities: ";
        for (City city : cities) {
            str += city.getName() + ", ";
        }
        str = str.substring(0, str.length() - 3);
        System.out.println(str);
    }

    @Override
    public String toString() {
        String str = "Cities:\r\n";
        String pre = "  ";
        for (City city : this.cities) {
            str += city.toStringIndent(pre);
        }
        return str;
    }

    @Override
    public Iterator<City> iterator() {
        return this.cities.iterator();
    }
}
