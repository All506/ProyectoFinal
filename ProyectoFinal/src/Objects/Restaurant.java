/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
 *
 * @author Your Name <Sebastián Navarro Martínez>
 */
public class Restaurant {

    private int ID;
    private String name;
    private String location;

    public Restaurant(int ID, String name, String location) {
        this.ID = ID;
        this.name = name;
        this.location = location;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Restaurant{" + "ID:" + ID + ", name:" + name + ", location:" + location + '}';
    }

    public String[] dataName() {
        String[] dataName = {"id","name","location"};
        return dataName;
    }
    
    public String[] data(){
        String[] data = {String.valueOf(this.ID), this.name, this.location};
        return data;
    }

}
