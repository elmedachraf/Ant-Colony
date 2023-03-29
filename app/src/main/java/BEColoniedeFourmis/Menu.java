/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BEColoniedeFourmis;

/**
 *
 * @author mohammedachrafelkhamlichi
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 

public class Menu implements ActionListener{
    Options opt;
    
    Menu(final Options opt) {
        this.opt = opt;
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        this.opt.f.setVisible(true);
    }     
}
