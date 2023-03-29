/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BEColoniedeFourmis;

/**
 *
 * @author mohammedachrafelkhamlichi
 */
import java.util.Comparator;
public class AntComparator implements Comparator<Ant>{
    private float gamma;
    
    public AntComparator(final float g) {
        this.gamma = g;
    }
    
    @Override
    public int compare(final Ant a1, final Ant a2) {
        return (int)(a1.travailleuse * this.gamma - (1.0f - this.gamma) * a1.exploratrice) - (int)(a2.travailleuse * this.gamma - (1.0f - this.gamma) * a2.exploratrice);
    }      
}
