/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package algen;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

/**
 *
 * @author Chris
 */
public class AlGen {

    public AlGen() throws InvalidConfigurationException {
        //modificar poblacion y generaciones en caso de que los resultados del problema plantead no sean los adecuados
        final int tamPoblacion = 1000;
        final int nGeneraciones = 71420;
        int valMaximo[] = {800, 800};
        int valMinimo[] = new int[]{-1, -1};
        System.out.println("===== Algoritmo Genetico =====");
        System.out.println("Maximizar la funcion "
                + "z = 1200x + 1400y");
        System.out.print("\nnumero de individuos="
                + tamPoblacion);
        for (int i = 0; i < valMinimo.length; i++) {
            System.out.printf("\nrango %d[%d , %d]", i,
                    valMinimo[i], valMaximo[i]);
        }

        System.out.println("\n\nGeneracion  Aptitud[mejor]    MejorIndividuo");
        System.out.print("--------  ------------    -------------------");

        Configuration conf = new DefaultConfiguration();
        FuncionAptitud funcion = new FuncionAptitud();

        conf.setFitnessFunction(funcion);
        conf.setPreservFittestIndividual(true);
        

        Gene[] genMuestra = new Gene[2];
        genMuestra[0] = new IntegerGene(conf, valMinimo[0], valMaximo[0]);
        genMuestra[1] = new IntegerGene(conf, valMinimo[1], valMaximo[1]);

        Chromosome cromosomaMuestra = new Chromosome(conf, genMuestra);
        conf.setSampleChromosome(cromosomaMuestra);
        conf.setPopulationSize(tamPoblacion);

        Genotype poblacion = Genotype.randomInitialGenotype(conf);
        //Inicio de funcionalidad 
        for (int generacion = 1; generacion < nGeneraciones; generacion++) {
            poblacion.evolve();

            // Obtener la mejor solución
            IChromosome mejorIndividuo = poblacion.getFittestChromosome();
            double mejorAptitud = mejorIndividuo.getFitnessValue();
            System.out.printf("\n%3d  %4.3f  ", generacion, mejorAptitud);

            for (Gene g : mejorIndividuo.getGenes()) {
                System.out.printf("%d ", (int) g.getAllele());
            }

            /*for (Gene g : mejorIndividuo.getGenes()) {
                System.out.printf("%s ", g.getPersistentRepresentation());
            }*/
        }

    }

    public static void main(String[] args)
            // TODO code application logic here

            throws InvalidConfigurationException {
        new AlGen();
    }

}
