/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package particleso;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author SOFEEM
 */
public class PSORun implements PSOCons {
    private Vector<Particle> swarm = new Vector<Particle>();
    private double[] pBest = new double[SWARM_SIZE];
	private Vector<Position> pBestLocation = new Vector<Position>();
	private double gBest;
	private Position gBestLocation;
	private double[] fitnessValueList = new double[SWARM_SIZE];
        private Sensor gbestSenposi = new Sensor();
        Random generator = new Random();
        Random r = new Random();
       
        
        public void execute(){
        initializeSwarm();
	updateFitnessList();
        sensorinfo(); 
        System.out.println("Intial particle Positions : ");
        for(int i=0;i<SWARM_SIZE;i++){
             //double[] posi = new double[SWARM_SIZE];
             pBest[i] = fitnessValueList[i];
             //posi[i] = pBestLocation.get(i).getpos()[i];
             pBestLocation.add(swarm.get(i).getPosition());
             
             System.out.println("pBest : " + pBest[i]);
             System.out.println("pBestLoc X: " + pBestLocation.get(i).getpos()[0]);
             System.out.println("pBestLoc Y: " + pBestLocation.get(i).getpos()[1]);
        
        }
        
       int bestParticleIndex = PSOUtility.getMinPos(fitnessValueList);
			//if(fitnessValueList[bestParticleIndex] > gBest) {
				gBest = fitnessValueList[bestParticleIndex];
				gBestLocation = swarm.get(bestParticleIndex).getPosition();
                                gbestSenposi = swarm.get(bestParticleIndex).getsensor();
                                  System.out.println("gBest : " + gBest);
             System.out.println("gBestLoc X: " + gBestLocation.getpos()[0]);
             System.out.println("gBestLoc Y: " + gBestLocation.getpos()[1]);
              System.out.println(" newSengbest X-axis : ");
                                  for(int m = 0; m <2 ;m++)
                                            {     if (m == 1){
                                            System.out.println("newSengbest Y-axis : ");}
                                                for (int n =0 ;n<NoofSen ; n++){
                                                  
                                                System.out.print(gbestSenposi.getSenPos()[m][n]+"   ");
                                                
                                                }          
                                                            System.out.println();
                                                                        }
			//}
             System.out.println("PSO process starting  : ");
             
          int t = 1;
		double w;
		double err = 9999;
		
		while(t < MAX_ITERATION && err > ProblemSet.ERR_TOLERANCE ) {
                    System.out.println("Start of ITERATION " + t + " : ");
			// step 1 - update pBest
			for(int i=0; i<SWARM_SIZE; i++) {
				if(fitnessValueList[i] > pBest[i]) {
					pBest[i] = fitnessValueList[i];
					pBestLocation.set(i, swarm.get(i).getPosition());
				}
			}
				
			// step 2 - update gBest
			int bestParticleIndex1 = PSOUtility.getMinPos(fitnessValueList);
			if(t == 0 || fitnessValueList[bestParticleIndex1] > gBest) {
				gBest = fitnessValueList[bestParticleIndex1];
				gBestLocation = swarm.get(bestParticleIndex1).getPosition();
                                gbestSenposi = swarm.get(bestParticleIndex1).getsensor();
			}
        
        w = W_UPPERBOUND - (((double) t) / MAX_ITERATION) * (W_UPPERBOUND - W_LOWERBOUND);
       // System.out.println("w: "+ w);
			
			for(int i=0; i<SWARM_SIZE; i++) {
				double r1 = generator.nextDouble();
				double r2 = generator.nextDouble();
                                System.out.println("Particle "+(i+1)+ " :");
                                //System.out.println(" r2 : "+r2);
				
				Particle p = swarm.get(i);
				
				// step 3 - update velocity
				double[] newVel = new double[PROBLEM_DIMENSION];
				newVel[0] = (w * p.getPosition().getpos()[0]) + 
							(r1 * C1) * (pBestLocation.get(i).getpos()[0] - p.getPosition().getpos()[0]) +
							(r2 * C2) * (gBestLocation.getpos()[0] - p.getPosition().getpos()[0]);
				newVel[1] = (w * p.getPosition().getpos()[1]) + 
							(r1 * C1) * (pBestLocation.get(i).getpos()[1] - p.getPosition().getpos()[1]) +
							(r2 * C2) * (gBestLocation.getpos()[1] - p.getPosition().getpos()[1]);
				
                                System.out.println("newVelocity-Xaxis of p" +(i+1)+ " :  " + newVel[0]);
                                System.out.println("newVelocity-Yaxis of p" +(i+1)+ " :  " + newVel[1]);
                                
                                
                                Velocity vel = new Velocity(newVel);
				p.setVelocity(vel);
				
				// step 4 - update location
				double[] newLoc = new double[PROBLEM_DIMENSION];
				newLoc[0] = p.getPosition().getpos()[0] + newVel[0];
				newLoc[1] = p.getPosition().getpos()[1] + newVel[1];
                                
                                
                                System.out.println("newLocation-Xaxis of p" +(i+1)+ " :  " + newLoc[0]);
                                System.out.println("newLocation-Yaxis of p" +(i+1)+ " :  " + newLoc[1]);
				
                                Position loc = new Position(newLoc);
				p.setPosition(loc);
                                
                                // step 5 - update sensor position 
                                
                                double [][] newSen = new double[2][NoofSen];
                                for (int k = 0; k<2;k++){
                                        for (int j=0;j<NoofSen;j++){
                                                      if (k == 0){
                                                          newSen[k][j] = p.getsensor().getSenPos()[k][j] + (newVel[0]/grid.length);
                                                          }
                                                      if (k == 1){
                                                         newSen[k][j] = p.getsensor().getSenPos()[k][j] + (newVel[1]/grid.length);
                                                      
                                                      }}}
                                System.out.println(" newSen X-axis : ");
                                  for(int m = 0; m <2 ;m++)
                                            {     if (m == 1){
                                            System.out.println("newSen Y-axis : ");}
                                                for (int n =0 ;n<NoofSen ; n++){
                                                  
                                                System.out.print(newSen[m][n]+"   ");
                                                
                                                }          
                                                            System.out.println();
                                                                        }
                                Sensor sen = new Sensor(newSen);
                                p.setSenpos(sen);
                                
                                
                        }
                        
                        
                        System.out.println("ITERATION " + t + ": ");
			System.out.println("     Best X: " + gBestLocation.getpos()[0]);
			System.out.println("     Best Y: " + gBestLocation.getpos()[1]);
			System.out.println("     Value: " + (gBest));
                        System.out.println(" newSengbest X-axis : ");
                                  for(int m = 0; m <2 ;m++)
                                            {     if (m == 1){
                                            System.out.println("newSengbest Y-axis : ");}
                                                for (int n =0 ;n<NoofSen ; n++){
                                                  
                                                System.out.print(gbestSenposi.getSenPos()[m][n]+"   ");
                                                
                                                }          
                                                            System.out.println();
                                                                        }
			
                        System.out.println(" END of Iteration :");
                        System.out.println();
                        
                        t++;
			updateFitnessList();
                        
                        }
        
            System.out.println("\nSolution found at iteration " + (t - 1) + ", the solutions is:");
		System.out.println("     Best X: " + gBestLocation.getpos()[0]);
		System.out.println("     Best Y: " + gBestLocation.getpos()[1]);
                System.out.println("     Value: " + (gBest));
                System.out.println(" newSengbest X-axis : ");
                                  for(int m = 0; m <2 ;m++)
                                            {     if (m == 1){
                                            System.out.println("newSengbest Y-axis : ");}
                                                for (int n =0 ;n<NoofSen ; n++){
                                                  
                                                System.out.print(gbestSenposi.getSenPos()[m][n]+"   ");
                                                
                                                }          
                                                            System.out.println();
                                                                        }
        
        }
        
        
        
        
        
        public void initializeSwarm() {
		Particle p;
		for(int i=0; i<SWARM_SIZE; i++) {
			p = new Particle();
			
			// randomize location inside a space defined in Problem Set
			double[] pos = new double[PROBLEM_DIMENSION];
			pos[0] = ProblemSet.LOC_X_LOW + generator.nextDouble() * (ProblemSet.LOC_X_HIGH - ProblemSet.LOC_X_LOW);
			pos[1] = ProblemSet.LOC_Y_LOW + generator.nextDouble() * (ProblemSet.LOC_Y_HIGH - ProblemSet.LOC_Y_LOW);
			Position position = new Position(pos);
			
			// randomize velocity in the range defined in Problem Set
			double[] vel = new double[PROBLEM_DIMENSION];
			vel[0] = ProblemSet.VEL_LOW + generator.nextDouble() * (ProblemSet.VEL_HIGH - ProblemSet.VEL_LOW);
			vel[1] = ProblemSet.VEL_LOW + generator.nextDouble() * (ProblemSet.VEL_HIGH - ProblemSet.VEL_LOW);
			Velocity velocity = new Velocity(vel);
                        
                        //add sensors position
                        double[][] sen =  new double[2][NoofSen];
                        for (int k = 0; k<2;k++){
                        for (int j=0;j<NoofSen;j++){
                            double R = r.nextInt(grid.length-0) + 0;
                            sen[k][j]=R;
                        }
                            
                        } 
                        Sensor senpos = new Sensor(sen);
			
			p.setPosition(position);
			p.setVelocity(velocity);
                        p.setSenpos(senpos);
			swarm.add(p);
                        System.out.println("Particle " +(i+1)+ " added");
		}
	}
        
        public void updateFitnessList() {
		for(int i=0; i<SWARM_SIZE; i++) {
			fitnessValueList[i] = swarm.get(i).getFitnessValue();
                        System.out.println("Fitness "+(i+1)+" :  " + fitnessValueList[i]);
		}
	}
        
        public void sensorinfo(){
        
        for (int i=0;i<SWARM_SIZE;i++){
                System.out.println("Particle " +(i+1)+" :");
                                Sensor p = new Sensor();
                                double [][] content = new double[2][NoofSen];
                                p = swarm.get(i).getsensor();
                            System.out.println("X-axis : ");
                                    for (int j=0;j<2;j++){
                                        if (j == 1){
                                         System.out.println("Y-axis : ");}
                                        for (int k=0;k<NoofSen;k++){
                 
                                    content[j][k] = p.getSenPos()[j][k];
                      
                                    System.out.print("  " + content[j][k]);
                                    }
                      System.out.println();
                      
             }
             System.out.println("Particle end");
        }
        
        
        }
   
    
}
