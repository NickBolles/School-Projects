package com.nickbolles.IntroToAI.Project1;

/**
 * Created by Nicholas on 9/23/2015.
 * <p/>
 * An Exception to be thrown when a City cannot be found. Meant for use when a user inputs
 * a City name and it cannot be found.
 */
public class NoSuchCity extends Exception {
    private String name = "";

    /**
     * Constructor taking the name of the City that was attempted and customize the message
     *
     * @param name
     */
    public NoSuchCity(String name) {
        super("No city with name: " + name + " exists");
        this.name = name;
    }
}
