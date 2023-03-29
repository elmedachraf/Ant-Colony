/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BEColoniedeFourmis;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
/**
 *
 * @author mohammedachrafelkhamlichi
 */
public class Ant {
    double alpha;
    double beta;
    double tournee;
    double distance;
    boolean porte_food;
    boolean marche;
    Path path;
    City previous_city;
    City next_city;
    int x;
    int y;
    double vitesse;
    List<City> memoire;
    List<Path> paths;
    float q0;
    float Q;
    float M;
    int exploratrice;
    int travailleuse;
    Random random;
    
    Ant(final City c_nid, final float Q, final float M) {
        this.alpha = Math.random() * 3.0 + 1.0;
        this.beta = Math.random() * 3.0 + 1.0;
        this.vitesse = Math.random() * 3.0 + 3.0;
        this.next_city = c_nid;
        this.q0 = (float)Math.random();
        this.x = c_nid.X;
        this.y = c_nid.Y;
        this.path = null;
        System.out.println("Creation de la fourmi au point : " + this.x + " , " + this.y + " - Vitesse : " + this.vitesse + " - Parametres : " + this.alpha + " " + this.beta);
        this.previous_city = null;
        this.marche = false;
        this.paths = new ArrayList<Path>();
        this.tournee = 0.0;
        this.exploratrice = 0;
        this.travailleuse = -1;
        this.au_nid();
        this.Q = Q;
        this.M = M;
        this.porte_food = false;
        this.random = new Random();
    }
    
    public void cross(final Ant dad, final Ant mum, final City c_nid, final float Q, final float M) {
        final double[][] multi = { { dad.alpha, mum.alpha }, { dad.beta, mum.beta } };
        this.alpha = multi[0][new Random().nextInt(1)];
        this.beta = multi[1][new Random().nextInt(1)];
        this.vitesse = (dad.vitesse + mum.vitesse) / 2.0;
        this.next_city = c_nid;
        this.q0 = (dad.q0 + mum.q0) / 2.0f;
        this.x = c_nid.X;
        this.y = c_nid.Y;
        this.path = null;
        System.out.println("Crossover de la fourmi au point : " + this.x + " , " + this.y + " - Vitesse : " + this.vitesse + " - Parametres : " + this.alpha + " " + this.beta);
        this.previous_city = null;
        this.marche = false;
        this.paths = new ArrayList<Path>();
        this.tournee = 0.0;
        this.exploratrice = 0;
        this.travailleuse = -1;
        this.au_nid();
        this.Q = Q;
        this.M = M;
        this.porte_food = false;
    }
    
    public void migrate(final City c_nid, final float Q, final float M) {
        this.alpha = Math.random() * 3.0 + 1.0;
        this.beta = Math.random() * 3.0 + 1.0;
        this.vitesse = Math.random() * 3.0 + 3.0;
        this.next_city = c_nid;
        this.q0 = (float)Math.random();
        this.x = c_nid.X;
        this.y = c_nid.Y;
        this.path = null;
        System.out.println("Creation de la fourmi au point : " + this.x + " , " + this.y + " - Vitesse : " + this.vitesse + " - Parametres : " + this.alpha + " " + this.beta);
        this.previous_city = null;
        this.marche = false;
        this.paths = new ArrayList<Path>();
        this.tournee = 0.0;
        this.exploratrice = 0;
        this.travailleuse = -1;
        this.au_nid();
        this.Q = Q;
        this.M = M;
        this.porte_food = false;
    }
    
    public void mutation() {
        this.alpha += this.M * (Math.random() - 0.5);
        this.beta += this.M * (Math.random() - 0.5);
        this.vitesse += this.M * (Math.random() - 0.5);
    }
    
    public boolean porte_food() {
        return this.porte_food;
    }
    
    public void prend_food() {
        this.porte_food = true;
    }
    
    public void depose_food() {
        this.porte_food = false;
    }
    
    public Path choix_arrete(final List<Path> paths_, final Path path) {
        final List<Path> paths = paths_;
        Collections.shuffle(paths, this.random);
        Path p_privilegiee = path;
        final float q = (float)Math.random();
        if (q < this.q0) {
            double s_max = (float)(-this.alpha);
            for (final Path p : paths) {
                final double s = Math.pow(p.pheromone, this.alpha) / Math.pow(p.length, this.beta);
                if (s > s_max && p.begin != this.previous_city && p.end != this.previous_city) {
                    p_privilegiee = p;
                    s_max = s;
                }
            }
        }
        else {
            p_privilegiee = paths.get((int)(Math.random() * paths.size()));
        }
        return p_privilegiee;
    }
    
    public void depose_pheromone() {
        for (final Path path : this.paths) {
            final Path p = path;
            path.pheromone += (float)(this.Q / this.tournee);
        }
    }
    
    public void walk() {
        this.distance -= this.vitesse;
        if (this.distance < 0.0) {
            this.x = this.next_city.X;
            this.y = this.next_city.Y;
            this.tournee += this.path.length;
            this.paths.add(this.path);
            this.marche = false;
        }
        else {
            this.x = (int)(this.previous_city.X - (this.previous_city.X - this.next_city.X) * (1.0 - this.distance / this.path.length));
            this.y = (int)(this.previous_city.Y - (this.previous_city.Y - this.next_city.Y) * (1.0 - this.distance / this.path.length));
        }
    }
    
    public void in_a_city_to_go() {
        Path p_privilegiee = this.path;
        if (this.next_city.food) {
            this.prend_food();
        }
        else {
            p_privilegiee = this.choix_arrete(this.next_city.paths, p_privilegiee);
            this.memorize_trip(this.next_city);
        }
        this.previous_city = this.next_city;
        if (p_privilegiee.begin == this.previous_city) {
            this.next_city = p_privilegiee.end;
        }
        else {
            this.next_city = p_privilegiee.begin;
        }
        this.path = p_privilegiee;
        if (this.path != null) {
            this.distance = this.path.length;
        }
        this.marche = true;
    }
    
    public void memorize_trip(final City c) {
        final int i = this.memoire.lastIndexOf(c);
        if (i == -1) {
            this.memoire.add(c);
        }
        else {
            this.memoire = this.memoire.subList(0, i + 1);
            ++this.exploratrice;
        }
    }
    
    public void in_a_return_city() {
        this.previous_city = this.next_city;
        if (this.previous_city.nid) {
            this.depose_food();
            this.au_nid();
        }
        else {
            this.next_city = this.memoire.get(this.memoire.lastIndexOf(this.previous_city) - 1);
            for (final Path p : this.next_city.paths) {
                if (p.begin == this.previous_city || p.end == this.previous_city) {
                    this.path = p;
                    this.distance = this.path.length;
                    break;
                }
            }
        }
        this.marche = true;
    }
    
    public void au_nid() {
        this.memoire = new ArrayList<City>();
        this.depose_pheromone();
        this.paths.clear();
        this.tournee = 0.0;
        ++this.travailleuse;
    }
    
    public void run() {
        if (this.marche) {
            this.walk();
        }
        else if (this.porte_food) {
            this.in_a_return_city();
        }
        else {
            this.in_a_city_to_go();
            System.out.println("Go !");
        }
    }      
}
