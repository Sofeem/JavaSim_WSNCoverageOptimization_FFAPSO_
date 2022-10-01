/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fireflydriver;

/**
 *
 * @author SOFEEM
 */
public interface Cons {
        int SWARM_SIZE = 10;
	int MAX_ITERATION = 10;
	int PROBLEM_DIMENSION = 2;
        int NoofSen = 5;
        static double Radius = 4;
	double beta = 0.5;
	double alpha = 0.7;
        double gamma = 1;
        double W_UPPERBOUND = 1.0;
	double W_LOWERBOUND = 0.0;
        static double [][] grid = new double[9][9];
        double [][] content = new double[2][NoofSen];
    
}
