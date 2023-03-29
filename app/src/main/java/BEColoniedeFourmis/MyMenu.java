/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BEColoniedeFourmis;

//import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

/**
 *
 * @author mohammedachrafelkhamlichi
 */
public class MyMenu extends JMenuBar implements ActionListener{
    
    MyMenu( Map up) {   
        
        JMenu menu = new JMenu("File");
        this.add(menu);
        
        
        final Menu menu_item_special = new Menu(new Options(up));
        JMenuItem menuItem = new JMenuItem("Change the simulation parameters");
        //menuItem.setBackground(Color.BLACK);
        //menuItem.setForeground(Color.WHITE);
        menuItem.addActionListener(up);
        menuItem.addActionListener(menu_item_special);
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Erase the map");
        //menuItem.setBackground(Color.BLACK);
        //menuItem.setForeground(Color.WHITE);
        menuItem.addActionListener(up);
        menu.add(menuItem);
        
        
        JMenu menu_edit = new JMenu("Edit");
        this.add(menu_edit);
        
        menuItem = new JMenuItem("Add a city");
        //menuItem.setBackground(Color.BLACK);
        //menuItem.setForeground(Color.WHITE);
        menuItem.addActionListener(up);
        menu_edit.add(menuItem);
        
        menuItem = new JMenuItem("Move a city");
        //menuItem.setBackground(Color.BLACK);
        //menuItem.setForeground(Color.WHITE);
        menuItem.addActionListener(up);
        menu_edit.add(menuItem);
        
        menuItem = new JMenuItem("Move the colony");
        //menuItem.setBackground(Color.BLACK);
        //menuItem.setForeground(Color.WHITE);
        menuItem.addActionListener(up);
        menu_edit.add(menuItem);
        
        menuItem = new JMenuItem("Move the food location");
        //menuItem.setBackground(Color.BLACK);
        //menuItem.setForeground(Color.WHITE);
        menuItem.addActionListener(up);
        menu_edit.add(menuItem);
        
        menu_edit.add(new JSeparator());
        
        menuItem = new JMenuItem("Add a path");
        //menuItem.setBackground(Color.BLACK);
        //menuItem.setForeground(Color.WHITE);
        menuItem.addActionListener(up);
        menu_edit.add(menuItem);
        
        
        JMenu menu_run = new JMenu("Run");
        this.add(menu_run);
        
        menuItem = new JMenuItem("Run");
        //menuItem.setBackground(Color.BLACK);
        //menuItem.setForeground(Color.WHITE);
        menuItem.addActionListener(up);
        menu_run.add(menuItem);
        
        menuItem = new JMenuItem("Stop");
        //menuItem.setBackground(Color.BLACK);
        //menuItem.setForeground(Color.WHITE);
        menuItem.addActionListener(up);
        menu_run.add(menuItem);
        
        menuItem = new JMenuItem("Reset");
        //menuItem.setBackground(Color.BLACK);
        //menuItem.setForeground(Color.WHITE);
        menuItem.addActionListener(up);
        menu_run.add(menuItem);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
