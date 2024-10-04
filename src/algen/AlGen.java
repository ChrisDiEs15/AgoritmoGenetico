/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package algen;

import org.jgap.Chromosome; // Importa la clase Chromosome de JGAP
import org.jgap.Configuration; // Importa la clase Configuration de JGAP
import org.jgap.Gene; // Importa la clase Gene de JGAP
import org.jgap.Genotype; // Importa la clase Genotype de JGAP
import org.jgap.IChromosome; // Importa la interfaz IChromosome de JGAP
import org.jgap.InvalidConfigurationException; // Importa la excepción InvalidConfigurationException de JGAP
import org.jgap.impl.DefaultConfiguration; // Importa la implementación DefaultConfiguration de JGAP
import org.jgap.impl.IntegerGene; // Importa la clase IntegerGene de JGAP
import org.jgap.impl.MutationOperator; // Importa la clase MutationOperator de JGAP

/**
 * Clase principal que implementa un algoritmo genético.
 */
public class AlGen {

    /**
     * Constructor de la clase AlGen.
     * Inicializa la configuración del algoritmo genético y ejecuta la evolución.
     * @throws org.jgap.InvalidConfigurationException
     */
    public AlGen() throws InvalidConfigurationException {
        // Definición del tamaño de la población y el número de generaciones
        final int tamPoblacion = 10000; // Número de individuos en la población
        final int nGeneraciones = 5000;  // Número de generaciones para evolucionar
        // Definición de los valores máximos y mínimos para cada gen
        int valMaximo[] = {800, 800, 800, 800}; // Valores máximos para los genes
        int valMinimo[] = new int[]{-1, -1, -1, -1}; // Valores mínimos para los genes
        
        // Imprime encabezados y detalles del algoritmo
        System.out.println("===== Algoritmo Genetico =====");
        System.out.println("Maximizar la funcion z = 6x + 5y +3w +7z");
        System.out.print("\nnumero de individuos=" + tamPoblacion);
        // Imprime el rango de cada gen
        for (int i = 0; i < valMinimo.length; i++) {
            System.out.printf("\nrango %d[%d , %d]", i, valMinimo[i], valMaximo[i]);
        }

        // Imprime encabezados para la salida de generaciones
        System.out.println("\n\nGeneracion  Aptitud[mejor]    MejorIndividuo");
        System.out.print("--------  ------------    -------------------");

        // Configuración del algoritmo genético
        Configuration conf = new DefaultConfiguration(); // Crea una nueva configuración
        FuncionAptitud funcion = new FuncionAptitud(); // Crea una instancia de la función de aptitud

        // Configuración de la tasa de mutación (5% de la población)
        double mutationRate = 0.05;  // Tasa de mutación del 5%
        int numberOfMutations = (int) (tamPoblacion * mutationRate); // Calcula el número de individuos a mutar

        // Inicializa el operador de mutación y establece la tasa de mutación
        MutationOperator mutationOperator = new MutationOperator(conf);
        mutationOperator.setMutationRate(numberOfMutations); // Establece el número de mutaciones en cada generación

        // Configura la función de aptitud y preservación del mejor individuo
        conf.setFitnessFunction(funcion); // Asigna la función de aptitud a la configuración
        conf.setPreservFittestIndividual(true); // Activa la preservación del mejor individuo en la población

        // Creación de un arreglo de genes
        Gene[] genMuestra = new Gene[4]; // Crea un arreglo para 4 genes
        // Inicializa cada gen con valores mínimos y máximos
        genMuestra[0] = new IntegerGene(conf, valMinimo[0], valMaximo[0]);
        genMuestra[1] = new IntegerGene(conf, valMinimo[1], valMaximo[1]);
        genMuestra[2] = new IntegerGene(conf, valMinimo[2], valMaximo[2]);
        genMuestra[3] = new IntegerGene(conf, valMinimo[3], valMaximo[3]);

        // Crea un cromosoma a partir de la muestra de genes
        Chromosome cromosomaMuestra = new Chromosome(conf, genMuestra);
        conf.setSampleChromosome(cromosomaMuestra); // Asigna el cromosoma de muestra a la configuración
        conf.setPopulationSize(tamPoblacion); // Establece el tamaño de la población

        // Crea una población inicial aleatoria
        Genotype poblacion = Genotype.randomInitialGenotype(conf);

        // Inicio del proceso evolutivo
        for (int generacion = 1; generacion < nGeneraciones; generacion++) {
            poblacion.evolve(); // Evoluciona la población

            // Obtiene la mejor solución de la población actual
            IChromosome mejorIndividuo = poblacion.getFittestChromosome(); // Obtiene el mejor individuo
            double mejorAptitud = mejorIndividuo.getFitnessValue(); // Obtiene la aptitud del mejor individuo
            System.out.printf("\n%3d  %4.3f  ", generacion, mejorAptitud); // Imprime la generación y su aptitud

            // Imprime los valores de los genes del mejor individuo
            for (Gene g : mejorIndividuo.getGenes()) {
                System.out.printf("%d ", (int) g.getAllele()); // Imprime el valor del gen
            }

            /* 
            Descomentar la siguiente sección para imprimir la representación persistente del mejor individuo
            for (Gene g : mejorIndividuo.getGenes()) {
                System.out.printf("%s ", g.getPersistentRepresentation());
            }
            */
        }

    }

    /**
     * Método principal para ejecutar el algoritmo genético.
     * @param args
     * @throws org.jgap.InvalidConfigurationException
     */
    public static void main(String[] args) throws InvalidConfigurationException {
        new AlGen(); // Crea una instancia de AlGen y ejecuta el algoritmo
    }

}