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

import Core.ClassesInstances;
import Core.Resources;

public class Shooting {
	
	protected Vector2f position;
	protected Vector2f direction;
	private static int actual_life = 0;
	private static int max_life = 2000; //czas zycia pocisku - 2000ms
	private boolean is_active = true;
	private int radius_squared = 1000 ;
	
	private int damage = 1;
	
	private String bullet_1="bullet_1";
	private String bullet_2="bullet_2";

	ServerGameState gamestate = (ServerGameState) ClassesInstances.serverGameState;
	
	private PhysicalInfluence physicalInfluence;

	
	public Shooting(Vector2f position, Vector2f direction){
		
		this.position = position;
		this.direction = direction;
		physicalInfluence = new PhysicalInfluence();
		
		direction.scale(500);
		
	}
	
	public Shooting init( Vector2f position, Vector2f direction)
	{
		this.position = position;
		this.direction = direction;
		physicalInfluence = new PhysicalInfluence();
		
		direction.scale(500);
		setActive(true);
		return this;
	}
	
	public Shooting(){
		
		is_active = false;
		
	}
	
	
	
	public void update(int time){
		
		
	
		
		
		if(is_active){
			
			if(gamestate.getSelect1() || gamestate.getSelect1_2() ){
			
			Vector2f actual_speed = direction.copy();
			actual_speed.scale((time/20000.0f));
			position.add(actual_speed);
			
			actual_life = actual_life + time;
			//System.out.println("BulletState: " + BulletState());
			//System.out.println("Actual life: " + actual_life);


			if(actual_life > max_life){
				
				is_active = false;
				actual_life = 0;
			}
					
		}
		
		if(gamestate.getSelect2() || gamestate.getSelect2_2()){
			
			Vector2f actual_speed = direction.copy();
			actual_speed.scale((time/2000.0f));
			position.add(actual_speed);
			position.add(physicalInfluence.EffectOnFlight(actual_speed, 10.0d));
			
			actual_life = actual_life + time;
			//System.out.println("BulletState: " + BulletState());
			//System.out.println("Actual life: " + actual_life);


			if(actual_life > max_life){
				
				is_active = false;
				actual_life = 0;
			}
					
		}
			
		}
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		if(is_active){
			
			if(gamestate.getSelect1() || gamestate.getSelect1_2() ){
				g.drawImage(Resources.getSpritesheet(bullet_1).getSubImage(0,0,32,32),position.getX()*32,position.getY()*32);

			}
			else if(gamestate.getSelect2() || gamestate.getSelect2_2()){
				g.drawImage(Resources.getSpritesheet(bullet_2).getSubImage(0,0,32,32),position.getX(), position.getY());

			}

		}
		//g.fillOval(position.getX(),position.getY(), 20, 20);
	}
	
	public boolean collission(Vector2f other_position, int other_radius_squared)
	{
		int distance = (int)other_position.copy().sub(position).lengthSquared();
		
		if(distance<(other_radius_squared+radius_squared))
		{
			return true;
		}
		return false;
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
		public int getDamage(){
		
		return damage;
	}
		
		
	public Vector2f getShootPosition(){
		return position;
		
	}
		
	
}



