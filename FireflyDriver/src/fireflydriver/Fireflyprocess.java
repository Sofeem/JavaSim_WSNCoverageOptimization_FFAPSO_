/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fireflydriver;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author SOFEEM
 */
public class Fireflyprocess implements Cons {
    
    private Vector<Firefly> fireflies = new Vector<Firefly>();
    //private Vector<Location> l = new Vector<Location>();
    private double [] ligIn = new double[SWARM_SIZE];
    Random generator = new Random();
    Random r = new Random();
    
    
    public void execute(){
    
    intializefirefly();
    updatelightintensity();
    sensorinfo();
    
    int t = 0;
    
    while (t < MAX_ITERATION ){
                
    //int i =0;
               for (int i=0; i<SWARM_SIZE;i++){
                   for (int j =1;j<SWARM_SIZE;j++ ){
                           Firefly f1 = fireflies.get(i);
                           Firefly f2 = fireflies.get(j);
                       
                      // if (i!=j){
                       if(f1.getLightIn(f1.getsensor())<f2.getLightIn(f2.getsensor())){
                       
                       // firefly i towads j 
                           
                           // Step 1 move firefly i towards j
                           double[] newLoc = new double[PROBLEM_DIMENSION];
                           newLoc[0]= f1.getLocation().getLoc()[0] + movement(f1,f2,0);
                           newLoc[1]= f1.getLocation().getLoc()[1] + movement(f1,f2,1);
                           double c = movement(f1,f2,0);
                           double c1 = movement(f1,f2,1);
                            
                          System.out.println("newLoc X: " + newLoc[0]);
                            System.out.println("newLoc Y: " + newLoc[1]);
              
                           Location loc = new Location(newLoc);
			   f1.setLocation(loc);
                           // Step 2 upadte Sensor 
                          double [][] newSen = new double[2][NoofSen];
                               
                                for (int k = 0; k<2;k++){
                                            
                                        for (int l=0;l<NoofSen;l++){
                                                      if (k == 0){
                                                          newSen[k][l] = (f1.getsensor().getSenPos()[k][l] + c)%grid.length;
     
                                                          }
                                                      if (k == 1){
                                                         newSen[k][l] = (f1.getsensor().getSenPos()[k][l] + c1)%grid.length;
                                                      
                                                      }
                                        }
                                }System.out.println(" newSen X-axis : ");
                                  for(int m = 0; m <2 ;m++)
                                            {     if (m == 1){
                                            System.out.println("newSen Y-axis : ");}
                                                for (int n =0 ;n<NoofSen ; n++){
                                                  
                                                System.out.print(newSen[m][n]+"   ");
                                                
                                                }          
                                                            System.out.println();
                                                                        }
                           
                           Sensor sen = new Sensor(newSen);
                           f1.setSenpos(sen);
                            // updated ith firefly 
                                
                                
                            // evalute the solution and update light intensity .
                                
                                
                                ligIn[i]=f1.getLightIn(sen);
                                
                                
                        
                      for(int q=0; q<SWARM_SIZE; q++) {
			//fitnessValue[q] = fireflies.get(q).getFitnessValue();
                        //System.out.print("Fitness "+(i+1)+" :  " + fitnessValue[i]);
                        //ligIn[q]=fireflies.get(q).getLightIn();
                        System.out.println("LightIntensity "+(q+1)+" :  " + ligIn[q] + "-- ");
                                
                      }
                                
                        }   
                       
                    }
                       
                  }
                   
                   
                   t++;}
                   
                   
               
                       System.out.println("end");
              
                    for(int q=0; q<SWARM_SIZE; q++) {
			//fitnessValue[q] = fireflies.get(q).getFitnessValue();
                        //System.out.print("Fitness "+(i+1)+" :  " + fitnessValue[i]);
                        ligIn[q]=fireflies.get(q).getLightIn(fireflies.get(q).getsensor());
                        System.out.print("LightIntensity "+(q+1)+" :  " + ligIn[q] + "-- ");
        
        } System.out.println();
    
    }   
    
    
    
    
    
    
    
    
    

    private void intializefirefly() {
        Firefly f;
        for (int i = 0; i<SWARM_SIZE;i++){
              
            f = new Firefly();
            
            //Step1 intial position of firefly
            
            // randomize location inside a space defined in Problem Set
			double[] loc = new double[PROBLEM_DIMENSION];
			loc[0] = ProblemSet.LOC_X_LOW + generator.nextDouble() * (ProblemSet.LOC_X_HIGH - ProblemSet.LOC_X_LOW);
			loc[1] = ProblemSet.LOC_Y_LOW + generator.nextDouble() * (ProblemSet.LOC_Y_HIGH - ProblemSet.LOC_Y_LOW);
			Location location = new Location(loc);
            
             //adding sensors position
                        double[][] sen =  new double[2][NoofSen];
                        for (int k = 0; k<2;k++){
                        for (int j=0;j<NoofSen;j++){
                            double R = r.nextInt(grid.length-0) + 0;
                            sen[k][j]=R;
                        }
                            
                        } 
                        Sensor senpos = new Sensor(sen);
                        
                        
                        f.setLocation(location);
                        f.setSenpos(senpos);
                        fireflies.add(f);
                        System.out.println("Firefly  " +(i+1)+ " added");
            
            
            
        }
    }

    private void updatelightintensity() {
        for(int i=0; i<SWARM_SIZE; i++) {
			
                        //System.out.print("Fitness "+(i+1)+" :  " + fitnessValue[i]);
                        ligIn[i]=fireflies.get(i).getLightIn(fireflies.get(i).getsensor());
                        System.out.print("LightIntensity "+(i+1)+" :  " + ligIn[i] + "-- ");
                        System.out.println();
        
        }
    }
       public void sensorinfo(){
        
        for (int i=0;i<SWARM_SIZE;i++){
                System.out.println("Firefly " +(i+1)+" :");
                                Sensor p = new Sensor();
                                double [][] content = new double[2][NoofSen];
                                p = fireflies.get(i).getsensor();
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
             System.out.println("Firefly end");
        }
        
        
        }
       
      public double movement(Firefly f1, Firefly f2, int index){
      
           double x =f1.getLocation().getLoc()[index]-f2.getLocation().getLoc()[index] ;
           double eD = Math.sqrt((x*x));
           double exp = 1/(1+(gamma*(Math.pow(Math.exp(1*eD),2))));
           double b = beta*exp;
           double result = alpha+b;
           
           
      
      
      
      return result;
      }
}
