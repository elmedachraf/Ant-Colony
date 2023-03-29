/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BEColoniedeFourmis;

/**
 *
 * @author mohammedachrafelkhamlichi
 */

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.Image;

public class MyWindow extends JFrame{
    public MyMenu my_menu_bar ;
    public int width = 772;
    public int height = 705;
    MyWindow(){
        setTitle("Ant Colony Simulator");
        final JLayeredPane layers = new JLayeredPane();
        getContentPane().add(layers, "Center");


        // Create a new ImageIcon from the image file 
        ImageIcon imageIcon = new ImageIcon("../app/src/main/images/MyMap.png");  
        imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH));
        JLabel backgroundLabel = new JLabel(imageIcon);
        backgroundLabel.setBounds(0, 0, width, height);
        
        // Create the Map
        final Map up = new Map(); 
        up.setBounds(0, 0, width, height);
        up.setOpaque(false);
        up.addMouseListener(up);
        up.addMouseMotionListener(up);

        // Set the map and the background
        layers.add(backgroundLabel, Integer.valueOf(1));
        layers.add(up,Integer.valueOf(2));

        // Set the menu bar
        my_menu_bar = new MyMenu(up);

        // Window Settings
        setSize(width, height);
        setJMenuBar(my_menu_bar);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(3);  
    }
    
 
}
