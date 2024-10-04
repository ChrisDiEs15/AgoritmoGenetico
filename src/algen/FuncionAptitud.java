/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algen; // Paquete donde se encuentra la clase

import org.jgap.IChromosome; // Importa la interfaz IChromosome de JGAP
import org.jgap.FitnessFunction; // Importa la clase FitnessFunction de JGAP

/**
 * Clase que representa la función de aptitud para el algoritmo genético.
 * Esta clase extiende la clase FitnessFunction de JGAP.
 */
public class FuncionAptitud extends FitnessFunction {

    /**
     * Método que evalúa la aptitud de un cromosoma.
     * 
     * @param e El cromosoma a evaluar.
     * @return La aptitud del cromosoma, que es el valor de la función objetivo si cumple las restricciones, o 0 en caso contrario.
     */
    @Override 
    protected double evaluate(IChromosome e) {
        // Obtiene los valores de los genes del cromosoma
        int x0 = (int) e.getGene(0).getAllele(); // Primer gene
        int x1 = (int) e.getGene(1).getAllele(); // Segundo gene
        int x2 = (int) e.getGene(2).getAllele(); // Tercer gene
        int x3 = (int) e.getGene(3).getAllele(); // Cuarto gene
        
        // Calcula el valor de la función objetivo
        double z = 6 * x0 + 5 * x1 + 3 * x2 + 7 * x3;

        // Evaluación de restricciones
        boolean r1 = (x0 + x1 + 3 * x3 <= 50); // Restricción 1
        boolean r2 = (2 * x0 + x1 + 2 * x2 + x3 <= 150); // Restricción 2
        boolean r3 = (x0 + x1 + x2 <= 80); // Restricción 3
        boolean r4 = (x0 >= 0 && x1 >= 0 && x2 >= 0 && x3 >= 0); // Restricción 4 (no negativos)

        // Verifica si se cumplen todas las restricciones
        if (r1 && r2 && r3 && r4) {
            // Si se cumplen las restricciones, retorna el valor de la función objetivo
            return z;
        } else {
            // Si no se cumplen las restricciones, retorna 0 (indica una solución no válida)
            return 0;
        }
    }
}