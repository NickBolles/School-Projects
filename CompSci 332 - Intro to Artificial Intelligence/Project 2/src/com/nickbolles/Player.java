package com.nickbolles;

/**
 * Created by Nicholas on 10/21/2015.
 */
public class Player {
    private int id = -1;
    private String name = "";

    Player(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Sets new id.
     *
     * @param id New value of id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets new name.
     *
     * @param name New value of name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets id.
     *
     * @return Value of id.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets name.
     *
     * @return Value of name.
     */
    public String getName() {
        return name;
    }
}
