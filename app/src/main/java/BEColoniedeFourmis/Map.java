/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BEColoniedeFourmis;

import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
/**
 *
 * @author mohammedachrafelkhamlichi
 */
public class Map extends JPanel implements ActionListener, MouseListener, MouseMotionListener{
    List<City> cities;
    List<Path> paths;
    Path path_selection;
    City colonie;
    City food;
    City selection;
    City selection1;
    City selection2;
    int xMouse;
    int yMouse;
    boolean mode_add_city;
    boolean mode_positioning_colonie;
    boolean mode_positioning_food;
    boolean mode_shift_city;
    boolean mode_add_path;
    List<Ant> ants;
    int population;
    Launch launch;
    float chi;
    float Q;
    float pheromone0;
    float PM;
    float M;
    float percentMigration;
    float percentCrossOver;
    float gamma;
    int SN;
    int SN_remaining;

    
    
    Map() {
        this.population = 50;
        this.chi = 0.98f;
        this.Q = 10000.0f;
        this.pheromone0 = 20.0f;
        this.PM = 0.1f;
        this.M = 1.0f;
        this.percentMigration = 0.3f;
        this.percentCrossOver = 0.3f;
        this.gamma = 0.6f;
        this.SN = 500;
        this.SN_remaining = this.SN;
        this.cities = new ArrayList<City>();
        this.colonie = new City(200, 100, true);
        this.food = new City(620, 500, false);
        this.paths = new ArrayList<Path>();
        this.path_selection = null;
        this.ants = new ArrayList<Ant>();
    }
    
    @Override
    public void paintComponent(final Graphics g_) {
        super.paintComponent(g_);
        final Graphics2D g = (Graphics2D)g_;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (final Path p : this.paths) {
            this.add_path(p.begin.X, p.begin.Y, p.end.X, p.end.Y, g, p.pheromone);
            this.level_pheromon(p.begin.X, p.begin.Y, p.end.X, p.end.Y, g, String.format("%.2f", p.pheromone));
        }
        g.setStroke(new BasicStroke(1.0f));
        for (final City c : this.cities) {
            this.add_city(c.X, c.Y, g);
        }
        this.add_city_departure(this.colonie.X, this.colonie.Y, g);
        this.add_city_arrival(this.food.X, this.food.Y, g);
        if (this.selection1 != null) {
            this.colorer_selection(this.selection1.X, this.selection1.Y, g);
        }
        if (this.selection2 != null) {
            this.colorer_selection(this.selection2.X, this.selection2.Y, g);
        }
        g.setStroke(new BasicStroke(3.0f));
        if (this.selection != null) {
            this.entourer_selection(this.selection.X, this.selection.Y, g);
        }
        if (this.path_selection != null) {
            this.tracer_route_selection(this.path_selection.begin.X, this.path_selection.begin.Y, this.path_selection.end.X, this.path_selection.end.Y, g);
        }
        g.setStroke(new BasicStroke(1.0f));
        for (final Ant a : this.ants) {
            this.tracer_fourmi(a.x, a.y, g, a.porte_food);
        }
        this.selection_naturelle(g);
    }
    
    private void tracer_route_selection(final int i, final int j, final int k, final int l, final Graphics g) {
        g.setColor(Color.WHITE);
        g.drawLine(i, j, k, l);
    }
    
    public void add_city(final int i, final int j, final Graphics g) {
        int a = 5;
        int b = 8;
        int c = 10;
        int d = 12;
        g.setColor(Color.WHITE);
        g.fillOval(i-d, j-d, 2*d, 2*d);
        g.setColor(Color.GRAY);
        g.fillOval(i-c, j-c, 2*c, 2*c);
        
        g.setColor(Color.WHITE);
        //g.fillOval(i - 10, j - 10, 20, 20);
        //g.drawOval(i - 13, j - 13, 26, 26);
        int[] X = { i-a , i+a , i};
        int[] Y = { j-3 , j-3 , j+b};
        g.drawPolygon(X,Y,3);
        g.fillPolygon(X,Y,3);
        g.fillArc(i-a,j-a-3,2*a,2*a,0,180);
        g.setColor(Color.GRAY);
        g.fillOval(i-2, j-5, 4, 4);
        
        
        
    }
    
    public void add_city_departure(final int i, final int j, final Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(i - 10, j - 10, 20, 20);
        g.drawOval(i - 13, j - 13, 26, 26);
    }
    
    public void add_city_arrival(final int i, final int j, final Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(i - 10, j - 10, 20, 20);
        g.drawOval(i - 13, j - 13, 26, 26);
    }
    
    public void add_path(final int i, final int j, final int k, final int l, final Graphics2D g, final float w) {
        g.setStroke(new BasicStroke(w / 10.0f, 1, 1));
        g.setColor(Color.BLUE);
        g.drawLine(i, j, k, l);
    }
    
    private void colorer_selection(final int i, final int j, final Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(i - 10, j - 10, 20, 20);
    }
    
    private void entourer_selection(final int i, final int j, final Graphics g) {
        g.setColor(Color.WHITE);
        g.drawOval(i - 10, j - 10, 20, 20);
    }
    
    private void tracer_fourmi(final int i, final int j, final Graphics g, final boolean porte_food) {
        int r = 4;
        int d = 3;
        int a = 2*r + 2;
        int b = 2*r;
        int O1_x = i-d;
        int O2_x = i+d;
        g.setColor(Color.PINK);
        if (porte_food) {
            //g.fillOval(i - 4, j - 4, 8, 8);
            g.fillOval(O1_x - r, j - r, a, b);
            g.fillOval(O2_x - r, j - r, a, b);
            g.drawLine(O1_x-1, j+r,O1_x-1 , j+r+2);
            g.drawLine(O1_x+1, j+r,O1_x+1 , j+r+2);
            g.drawLine(O2_x-1, j+r,O2_x-1 , j+r+2);
            g.drawLine(O2_x+1, j+r,O2_x+1 , j+r+2);
        }
        else {
            //g.drawOval(i - 4, j - 4, 8, 8);
            g.drawOval(O1_x - r, j - r, a, b);
            g.drawOval(O2_x - r, j - r, a, b);
            //g.fillOval(O1_x - r, j - r, 2*r, 2*r);
            //g.fillOval(O2_x - r, j - r, 2*r, 2*r);
            g.drawLine(O1_x-1, j+r,O1_x-1 , j+r+2);
            g.drawLine(O1_x+1, j+r,O1_x+1 , j+r+2);
            g.drawLine(O2_x-1, j+r,O2_x-1 , j+r+2);
            g.drawLine(O2_x+1, j+r,O2_x+1 , j+r+2);
        }
    }
    
    private void level_pheromon(final int i, final int j, final int k, final int l, final Graphics g, final String f) {
        g.setColor(Color.BLACK);
        g.drawString(f, (i + k) / 2 - 10, (j + l) / 2);
    }
    
    private void selection_naturelle(final Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("Selection naturelle : " + this.SN_remaining, this.colonie.X - 65, this.colonie.Y - 16);
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        System.out.println("Action reperee !");
        final JMenuItem source = (JMenuItem)e.getSource();
        this.reset();
        if ("Add a city".equals(source.getText())) {
            this.mode_add_city = true;
            System.out.println("Mode ajout ville");
        }
        if ("Move the colony".equals(source.getText())) {
            this.mode_positioning_colonie = true;
            System.out.println("Mode positionnement de la colonie");
        }
        if ("Move the food location".equals(source.getText())) {
            this.mode_positioning_food = true;
            System.out.println("Mode positionnement de la nourriture");
        }
        if ("Move a city".equals(source.getText())) {
            this.mode_shift_city = true;
            System.out.println("Mode deplacement d'une ville");
        }
        if ("Add a path".equals(source.getText())) {
            this.mode_add_path = true;
            System.out.println("Mode ajout route");
        }
        if ("Run".equals(source.getText())) {
            System.out.println("Debut");
            this.initialiser();
        }
        if ("Stop".equals(source.getText())) {
            System.out.println("Interruption");
            this.interrompre();
        }
        if ("Reset".equals(source.getText())) {
            System.out.println("Reinitialisation");
            this.restart();
        }
        if ("Erase the map".equals(source.getText())) {
            System.out.println("Reinitialisation de la carte");
            this.viderCarte();
            this.repaint();
        }
    }
    
    private void viderCarte() {
        this.cities = new ArrayList<City>();
        this.colonie = new City(200, 100, true);
        this.food = new City(620, 500, false);
        this.paths = new ArrayList<Path>();
        this.path_selection = null;
        this.ants = new ArrayList<Ant>();
    }
    
    private void reset() {
        this.mode_add_city = false;
        this.mode_positioning_colonie = false;
        this.mode_positioning_food = false;
        this.mode_shift_city = false;
        this.mode_add_path = false;
        this.selection1 = null;
        this.selection2 = null;
    }
    
    @Override
    public void mouseClicked(final MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(final MouseEvent arg0) {
    }
    
    @Override
    public void mouseExited(final MouseEvent arg0) {
    }
    
    @Override
    public void mousePressed(final MouseEvent e) {
        this.xMouse = e.getPoint().x;
        this.yMouse = e.getPoint().y;
        if (this.mode_add_city) {
            final City c = new City(this.xMouse, this.yMouse);
            this.cities.add(c);
            System.out.println("Nouvelle ville : X=" + c.X + " ; Y=" + c.Y);
            this.selection = c;
        }
        if (this.mode_positioning_colonie) {
            this.colonie.set_coordinates(this.xMouse, this.yMouse);
            System.out.println("Positionnement de la colonie : X=" + this.colonie.X + " ; Y=" + this.colonie.Y);
        }
        if (this.mode_positioning_food) {
            this.food.set_coordinates(this.xMouse, this.yMouse);
            System.out.println("Positionnement de la nourriture : X=" + this.food.X + " ; Y=" + this.food.Y);
        }
        if (this.mode_shift_city && this.selection1 == null) {
            this.selection1 = this.selection(this.xMouse, this.yMouse);
        }
        if (this.mode_add_path && this.selection1 == null) {
            this.selection1 = this.selection(this.xMouse, this.yMouse);
        }
        this.repaint();
    }
    
    private City selection(final int xMouse, final int yMouse) {
        City c = null;
        int e = 625;
        for (final City c_current : this.cities) {
            final int e_current = (xMouse - c_current.X) * (xMouse - c_current.X) + (yMouse - c_current.Y) * (yMouse - c_current.Y);
            if (e_current < e) {
                e = e_current;
                c = c_current;
            }
        }
        City c_current = this.colonie;
        int e_current = (xMouse - c_current.X) * (xMouse - c_current.X) + (yMouse - c_current.Y) * (yMouse - c_current.Y);
        if (e_current < e) {
            e = e_current;
            c = c_current;
        }
        c_current = this.food;
        e_current = (xMouse - c_current.X) * (xMouse - c_current.X) + (yMouse - c_current.Y) * (yMouse - c_current.Y);
        if (e_current < e) {
            c = c_current;
        }
        return c;
    }
    
    @Override
    public void mouseReleased(final MouseEvent e) {
        this.xMouse = e.getPoint().x;
        this.yMouse = e.getPoint().y;
        this.path_selection = null;
        if (this.mode_shift_city && this.selection1 != null) {
            this.selection1.set_coordinates(this.xMouse, this.yMouse);
            System.out.println("Deplacement ville : X=" + this.selection1.X + " ; Y=" + this.selection1.Y);
            for (final Path p : this.selection1.paths) {
                p.compute_length();
            }
            this.selection1 = null;
        }
        if (this.mode_add_path && this.selection1 != null) {
            this.selection2 = this.selection(this.xMouse, this.yMouse);
            if (this.selection2 != null && this.selection1 != this.selection2) {
                final Path p = new Path(this.selection1, this.selection2, this.pheromone0);
                this.selection1.add_edge(p);
                this.selection2.add_edge(p);
                System.out.println("Nouvelle route - depart  : X=" + this.selection1.X + " ; Y=" + this.selection1.Y);
                System.out.println("               - arrivee : X=" + this.selection2.X + " ; Y=" + this.selection2.Y);
                System.out.println("               - ville depart  : " + this.selection1.paths.size() + " connexions");
                System.out.println("               - ville arrivee : " + this.selection2.paths.size() + " connexions");
                this.paths.add(p);
            }
            this.selection1 = null;
            this.selection2 = null;
        }
        this.repaint();
    }
    
    @Override
    public void mouseDragged(final MouseEvent e) {
        this.xMouse = e.getPoint().x;
        this.yMouse = e.getPoint().y;
        this.selection = this.selection(this.xMouse, this.yMouse);
        this.repaint();
        if (this.mode_shift_city && this.selection1 != null) {
            this.selection1.set_coordinates(this.xMouse, this.yMouse);
        }
        if (this.mode_add_path && this.selection1 != null) {
            this.selection2 = this.selection(this.xMouse, this.yMouse);
            if (this.selection2 != null && this.selection1 != this.selection2) {
                this.path_selection = new Path(this.selection1, this.selection2, this.pheromone0);
            }
            else {
                this.path_selection = new Path(this.selection1, new City(this.xMouse, this.yMouse), this.pheromone0);
            }
        }
        this.repaint();
    }
    
    @Override
    public void mouseMoved(final MouseEvent e) {
        this.xMouse = e.getPoint().x;
        this.yMouse = e.getPoint().y;
        this.selection = this.selection(this.xMouse, this.yMouse);
        if (this.selection == null && this.mode_add_city) {
            this.selection = new City(this.xMouse, this.yMouse);
        }
        this.repaint();
    }
    
    public void initialiser() {
        System.out.println("Colonie : " + this.colonie.X + ", " + this.colonie.Y);
        for (int i = 0; i < this.population; ++i) {
            final Ant a = new Ant(this.colonie, this.Q, this.M);
            this.ants.add(a);
        }
        (this.launch = new Launch(this)).run();
    }
    
    public void interrompre() {
        this.launch.interrompre();
    }
    
    public void relancer() {
        this.launch.relancer();
    }
    
    public void setParametres(final float c, final int p, final float Q, final float pheromone0, final float PM, final float M, final float gamma, final float percentMigration, final float percentCrossOver, final int SN) {
        this.chi = c;
        this.population = p;
        this.Q = Q;
        this.pheromone0 = pheromone0;
        this.PM = PM;
        this.M = M;
        this.gamma = gamma;
        this.percentMigration = percentMigration;
        this.percentCrossOver = percentCrossOver;
        this.SN = SN;
    }
    
    public void restart() {
        this.launch.interrompre();
        this.ants.clear();
        for (final Path p : this.paths) {
            p.pheromone = this.pheromone0;
        }
        this.repaint();
    }       
}
