package States;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import Core.Resources;

public class Shooting {
	
	private Vector2f position;
	private Vector2f speed;
	private static int actual_life = 0;
	private static int max_life = 1000; //czas zycia pocisku - 2000ms
	private boolean is_active = true;
	
	private String bullet_1="bullet_1";

	
	public Shooting(Vector2f position, Vector2f speed){
		
		this.position = position;
		this.speed = speed;
		
	}
	
	public Shooting(){
		
		is_active = false;
		
	}
	
	
	
	public void update(int time){
		
		if(is_active){
			
			Vector2f actual_speed = speed.copy();
			actual_speed.scale((time/1000.0f));
			position.add(actual_speed);
			
			actual_life = actual_life + time;
			System.out.println("BulletState: " + BulletState());
			System.out.println("Actual life: " + actual_life);


			if(actual_life > max_life){
				
				is_active = false;
				actual_life = 0;
			}
					
		}
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		if(is_active){
			g.drawImage(Resources.getSpritesheet(bullet_1).getSubImage(0,0,32,32),position.getX(),position.getY());

		}
		//g.fillOval(position.getX(),position.getY(), 20, 20);
	}

	public boolean BulletState(){
		
		return is_active;
	}
	
	public void setActual(int actual){
		this.actual_life = actual;
	}
	
	public void setActive(boolean active){
		this.is_active = active;
	}
}
