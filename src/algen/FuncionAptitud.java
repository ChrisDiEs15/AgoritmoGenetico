/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algen;
import org.jgap.IChromosome;
import org.jgap.FitnessFunction;

/**
 *
 * @author Chris
 */
public class FuncionAptitud extends FitnessFunction {
    
    @Override 
    protected double evaluate(IChromosome e){
    int x0 = (int) e.getGene(0).getAllele();
int x1 = (int) e.getGene(1).getAllele();
double z = 1200 * x0 + 1400 * x1;

// Restricciones
boolean r1 = (x0 + 2 * x1 <= 800);
boolean r2 = (2 * x0 + x1 <= 800);
boolean r3 = (x0 + x1 <= 500);
boolean r4 = (x0 >= 0 && x1 >= 0);

if (r1 && r2 && r3 && r4) {
    // Si se cumplen las restricciones
    // entonces la solución es candidata
    return z;
} else {
    // No cumplió con las restricciones
    return 0;
}
    
    }}
