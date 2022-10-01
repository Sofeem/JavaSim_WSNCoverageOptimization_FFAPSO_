/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package particleso;

/**
 *
 * @author SOFEEM
 */
public class Velocity {
    private double[] vel;

	public Velocity(double[] vel) {
		super();
		this.vel = vel;
	}

	public double[] getPos() {
		return vel;
	}

	public void setPos(double[] vel) {
		this.vel = vel;
	}
    
}
