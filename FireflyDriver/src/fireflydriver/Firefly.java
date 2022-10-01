/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fireflydriver;

/**
 *
 * @author SOFEEM
 */
public class Firefly {
    private double lightintensity;
    private Location location;
    private Sensor sen;
    //private double fitnessvalue;
    
    
    
    
    public Firefly() {
		super();
	}
    
    public Firefly(Sensor sen, double fitnessValue, Location location,double lightintensity) {
		super();
		//this.fitnessvalue = fitnessValue;
		this.location = location;
                this.lightintensity = lightintensity;
                this.sen=sen;
	}

    

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
       
        
        public void setSenpos(Sensor senpos){
           
            this.sen=senpos;
        
        }
        public Sensor getsensor(){
            
            return sen;
        }
       
        public double getLightIn(Sensor sen){
            lightintensity = Coverage.evaluate(sen); ;
            return lightintensity;
        
        }
   
    
}
