/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BEColoniedeFourmis;

/**
 *
 * @author mohammedachrafelkhamlichi
 */
public class Path {
    float pheromone;
    double length;
    City begin;
    City end;
    
    Path(final City Begin, final City End, final float pheromone0) {
        this.set_departure_city(Begin);
        this.set_arrival_city(End);
        this.compute_length();
        this.pheromone = pheromone0;
    }
    
    public void set_departure_city(final City Begin) {
        this.begin = Begin;
    }
    
    public void set_arrival_city(final City End) {
        this.end = End;
    }
    
    public void compute_length() {
        this.length = Math.sqrt((this.begin.X - this.end.X) * (this.begin.X - this.end.X) + (this.begin.Y - this.end.Y) * (this.begin.Y - this.end.Y));
    }
    
    //public void evaporate_pheromone() {
    //}       
}
