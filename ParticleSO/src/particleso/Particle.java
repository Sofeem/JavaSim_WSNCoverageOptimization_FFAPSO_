/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package particleso;

/**
 *
 * @author SOFEEM
 */
public class Particle {
    private double fitnessValue;
	private Velocity velocity;
	private Position position;
        private Sensor sensor;
	
	public Particle() {
		super();
	}

	public Particle(Sensor sensor,double fitnessValue, Velocity velocity, Position postion) {
		super();
		this.fitnessValue = fitnessValue;
		this.velocity = velocity;
		this.position = position;
                this.sensor=sensor;
                
	}

	public Velocity getVelocity() {
		return velocity;
	}

	public void setVelocity(Velocity velocity) {
		this.velocity = velocity;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public double getFitnessValue() {
		fitnessValue = Coverage.evaluate(sensor);
		return fitnessValue;
	}
        
        public void setSenpos(Sensor senpos){
           
            this.sensor=senpos;
        
        }
        public Sensor getsensor(){
            
            return sensor;
        }

    
   
  
}
