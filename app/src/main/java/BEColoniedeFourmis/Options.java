/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BEColoniedeFourmis;
import java.awt.event.ActionEvent;
import javax.swing.JRootPane;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.util.List;
import java.awt.event.ActionListener;
/**
 *
 * @author mohammedachrafelkhamlichi
 */
public class Options implements ActionListener{
    Map m;
    List<JTextField> fields;
    JFrame f;
    
    Options(final Map m) {
        this.m = m;
        this.f = new JFrame("Options");
        final String[] labels = { "Facteur d'evaporation : ", "Colonie : ", "Quantite de pheromone a deposer par tournee : ", "Quantite de pheromone initiale par route : ", "Probabilite de mutation : ", "Facteur de mutation : ", "Facteur de selection naturelle (exploratrice/travailleuse) : ", "Proportion a migrer pour la selection naturelle : ", "Proportion a enfanter pour la selection naturelle : ", "Iterations avant selection naturelle : " };
        final float[] text = { m.chi, (float)m.population, m.Q, m.pheromone0, m.PM, m.M, m.gamma, m.percentMigration, m.percentCrossOver, (float)m.SN };
        final int numPairs = labels.length;
        JPanel p = new JPanel();
        p.setBorder(new EmptyBorder(10, 10, 10, 10));
        p.setLayout(new GridLayout(10, 2, 10, 10));
        this.fields = new ArrayList<JTextField>();
        for (int i = 0; i < numPairs; ++i) {
            final JLabel l = new JLabel(labels[i], 11);
            p.add(l);
            final JTextField textField = new JTextField(String.valueOf(text[i]));
            l.setLabelFor(textField);
            p.add(textField);
            this.fields.add(textField);
        }
        this.f.getContentPane().add(p, "Center");
        p = new JPanel();
        final JButton Annuler = new JButton("Fermer");
        p.add(Annuler, "East");
        final JButton Ok = new JButton("Enregistrer");
        Ok.addActionListener(this);
        p.add(Ok, "West");
        this.f.getContentPane().add(p, "South");
        final JRootPane rootPane = this.f.getRootPane();
        rootPane.setDefaultButton(Ok);
        this.f.pack();
        this.f.setLocationRelativeTo(null);
        this.f.setResizable(false);
        this.f.setVisible(false);
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        final JButton source = (JButton)e.getSource();
        if ("Enregistrer".equals(source.getText())) {
            this.m.setParametres(Float.parseFloat(this.fields.get(0).getText()), (int)Float.parseFloat(this.fields.get(1).getText()), Float.parseFloat(this.fields.get(2).getText()), Float.parseFloat(this.fields.get(3).getText()), Float.parseFloat(this.fields.get(4).getText()), Float.parseFloat(this.fields.get(5).getText()), Float.parseFloat(this.fields.get(6).getText()), Float.parseFloat(this.fields.get(7).getText()), Float.parseFloat(this.fields.get(8).getText()), (int)Float.parseFloat(this.fields.get(9).getText()));
            System.out.println("Nouveau set de parametres");
        }
        if ("Fermer".equals(source.getText())) {
            System.out.println("Annule");
        }
        this.f.setVisible(false);
    }     
}
