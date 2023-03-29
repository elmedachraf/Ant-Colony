/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BEColoniedeFourmis;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author mohammedachrafelkhamlichi
 */
public class City {
    String name;
    int X;
    int Y;
    double distance;
    boolean nid;
    boolean food;
    List<Path> paths;
    
    City(final int x, final int y) {
        this.set_coordinates(x, y);
        this.nid = false;
        this.food = false;
        this.paths = new ArrayList<Path>();
    }
    
    City(final int x, final int y, final boolean departureOrArrival) {
        this.set_coordinates(x, y);
        this.paths = new ArrayList<Path>();
        if (departureOrArrival) {
            this.nid = true;
            this.food = false;
        }
        else {
            this.food = true;
            this.nid = false;
        }
    }
    
    public void add_edge(final Path p) {
        this.paths.add(p);
    }
    
    public void set_coordinates(final int x, final int y) {
        this.X = x;
        this.Y = y;
    }
    
    public int[] get_coordinates() {
        final int[] A = { this.X, this.Y };
        return A;
    }     
}
