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
	
	
	Vector2f trackModyfication;
	
	public PhysicalInfluence(){
		time = 0.0d;
		airResistanceX = 0.0d;
		airResistanceY = 0.0d;
		gravitation = 0.0d;
		
		trackModyfication = new Vector2f(0.0f, 0.0f);
	}
	
	public Vector2f EffectOnFlight (Vector2f actual_speed, double mass){
		Vector2f as = actual_speed.copy();
		time = time + 0.0001f;
		airResistanceX = airResistanceX + 0.0001f*(Math.pow(as.getX()+trackModyfication.getX(),2));
		airResistanceY = airResistanceY + 0.0001f*(Math.pow(as.getY()+trackModyfication.getY(),2));
		gravitation = gravitation + mass*10.0*time;
		float sX = -1.0f * Math.signum(as.getX());
		float sY = -1.0f * Math.signum(as.getX());
		trackModyfication.set((float) airResistanceX*sX, (float) ((airResistanceY)*sY+gravitation));
		return trackModyfication.copy();
	}

}
