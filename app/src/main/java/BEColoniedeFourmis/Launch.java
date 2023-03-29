/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BEColoniedeFourmis;

import java.util.Collections;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.util.List;
import java.awt.event.ActionListener;
/**
 *
 * @author mohammedachrafelkhamlichi
 */
public class Launch extends Thread implements ActionListener{
    List<Ant> ants;
    List<Path> paths;
    int population;
    int SN;
    Map m;
    float chi;
    Timer timer;
    float PM;
    float percentMigration;
    float percentCrossOver;
    float gamma;
    int natural_selection_att;
    
    Launch(final Map m) {
        this.m = m;
        this.ants = m.ants;
        this.paths = m.paths;
        this.population = m.population;
        this.chi = m.chi;
        this.PM = m.PM;
        this.percentMigration = m.percentMigration;
        this.percentCrossOver = m.percentCrossOver;
        this.gamma = m.gamma;
        this.natural_selection_att = m.SN;
        this.SN = m.SN;
    }
    
    public void relancer() {
        this.timer.start();
    }
    
    @Override
    public void run() {
        (this.timer = new Timer(20, this)).setInitialDelay(190);
        this.timer.start();
    }
    
    public void interrompre() {
        this.timer.stop();
    }
    
    private void evaporation_pheromone() {
        for (final Path p : this.paths) {
            p.pheromone *= this.chi;
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent arg0) {
        for (final Ant a : this.ants) {
            a.run();
        }
        this.evaporation_pheromone();
        this.m.repaint();
        --this.natural_selection_att;
        this.m.SN_remaining = this.natural_selection_att;
        if (this.natural_selection_att < 0) {
            this.natural_selection_att = this.SN;
            this.natural_selection();
        }
    }
    
    public void natural_selection() {
        Collections.sort(this.ants, new AntComparator(this.gamma));
        final int A = (int)(this.percentMigration * this.population);
        final int B = (int)((this.percentMigration + this.percentCrossOver) * this.population);
        System.out.println("Population : " + this.population + " - Pourcentages de migration, crossover : " + this.percentMigration + ", " + this.percentCrossOver);
        System.out.println("Bornes migration : 0 -> " + A);
        System.out.println("Bornes crossover : " + A + " -> " + B);
        for (final Ant a : this.ants.subList(0, A)) {
            a.migrate(this.m.colonie, this.m.Q, this.m.M);
            System.out.println(a.travailleuse * this.gamma - (1.0f - this.gamma) * a.exploratrice);
            System.out.println(a.marche);
        }
        System.out.println("Migration");
        for (final Ant a : this.ants.subList(A, B)) {
            final int C = this.population - 1 - (int)(Math.random() * (1.0f - this.percentMigration - this.percentCrossOver) * this.population);
            final int D = this.population - 1 - (int)(Math.random() * (1.0f - this.percentMigration - this.percentCrossOver) * this.population);
            System.out.println("Cross over entre les fourmis " + C + " et " + D);
            a.cross(this.ants.get(C), this.ants.get(D), this.m.colonie, this.m.Q, this.m.M);
            if (Math.random() < this.PM) {
                a.mutation();
                System.out.println("   -> mutation");
            }
            System.out.println(a.marche);
        }
    }      
}
