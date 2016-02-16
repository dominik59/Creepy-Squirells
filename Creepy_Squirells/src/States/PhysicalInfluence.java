package States;

import org.newdawn.slick.geom.Vector2f;
import java.math.*;

public class PhysicalInfluence {
	double time;
	double airResistanceX;
	double airResistanceY;
	double gravitation;
	
	Vector2f position;
	Vector2f actual_speed;
	
	
	Vector2f airTrackModyfication;
	Vector2f gravityTrackModyfication;
	
	public PhysicalInfluence(){
		time = 0.0d;
		airResistanceX = 0.0d;
		airResistanceY = 0.0d;
		gravitation = 0.0d;
		//System.out.println("Wypuszczono pocisk.");
		
		airTrackModyfication = new Vector2f(0.0f, 0.0f);
		gravityTrackModyfication = new Vector2f(0.0f, 0.0f);
	}
	 /**
	  * 
	  * @param actual_speed - actual_speed form method "update" in Shooting class
	  * @param size 1.0d - small bullet, small air resistance; 5.0d - medium bullet, medium air resistance; 10.0d - big bullet, big air resistance \n 
	  * @return Vector of air influence to add to position of bullet
	  */
	public Vector2f airResistanceEffect (Vector2f actual_speed, double size){
		Vector2f as = actual_speed.copy();
		airResistanceX = airResistanceX + size*0.0003f*(Math.pow(as.getX()+airTrackModyfication.getX(),2));
		airResistanceY = airResistanceY + size*0.0003f*(Math.pow(as.getY()+airTrackModyfication.getY(),2));
		//System.out.println("Opór X = "+Double.toString(airResistanceX)+" Y = "+Double.toString(airResistanceY)+" Time: "+Double.toString(time)+ " Gravitation: "+Double.toString(gravitation));
		float sX = -1.0f * Math.signum(as.getX());
		float sY = -1.0f * Math.signum(as.getY());
		airTrackModyfication.set((float) airResistanceX*sX, (float) ((airResistanceY)*sY));
		//System.out.println("Rezultat X: "+Float.toString(airTrackModyfication.getX())+" Y: "+Float.toString(airTrackModyfication.getY()));
		return airTrackModyfication.copy();
	}
	/**
	 * @param mass - 1.0d - light bullet, weak gravitation influence; 5.0d - medium-weight bullet, medium gravitation influence; 10.0d - heavy bullet, strong gravitation influence
	 * @return Vector of gravity influence to add to position of bullet
	 */
	public Vector2f gravityEffect (double mass){
		time = time + 0.0001f;
		gravitation = gravitation + mass*10.0*time;
		return new Vector2f(0.0f, (float)gravitation);
	}
	

}
