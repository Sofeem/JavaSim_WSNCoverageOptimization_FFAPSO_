/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package particleso;

/**
 *
 * @author SOFEEM
 */
public interface PSOCons {
    int SWARM_SIZE = 25;
	int MAX_ITERATION = 10;
	int PROBLEM_DIMENSION = 2;
        int NoofSen = 5;
        static double Radius = 4;
	double C1 = 1.0;
	double C2 = 1.0;
	double W_UPPERBOUND = 1.0;
	double W_LOWERBOUND = 0.0;
        static double [][] grid = new double[9][9];
        double [][] content = new double[2][NoofSen];
    
}
