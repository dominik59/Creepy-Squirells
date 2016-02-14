package States;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

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
import States.MenuState;
import States.Shooting;

public class GameState extends BasicGameState{
	private Integer first_player_x;
	private Integer first_player_y;
	private String first_player_picture="sqi_r";
	
	private Integer posx;
	private Integer posy;
	
	private Integer pos_player_x;
	private Integer pos_player_y;
	
	
	private Music sound;
	private Music music;
	
	private TiledMap mapa;
	
	//private Shooting shoot;
//	private LinkedList<Shooting> shoot;
	private Shooting[] shoots;
	private static int fire_rate = 100;
	private int actual_bullet = 0;
	public int delta = 0;
	protected Vector2f position_of_player;
	private int radius_squared;
	protected Vector2f position;
	private boolean set_position = true;
	
	private int lives = 3;
	private boolean is_alive = true;
	
	private boolean flag_r;
	private boolean flag_l;
	private boolean flag_u;
	private boolean flag_d;
	
	public static boolean select_1 = true;
	public static boolean select_2 = false;
		
	
	MenuState menustate = new MenuState();
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		mapa = Resources.getMap("level1");
		
		sound = Resources.getAudio("level1_music");
		music = Resources.getAudio("level1_sound");
		
		first_player_x=10;
		first_player_y=18;
		//10,18
		
		//shoot = new Shooting(new Vector2f(0,100), new Vector2f(200,100));
//		shoot = new LinkedList<Shooting>();
		
		shoots = new Shooting[20];
		
		for(int i=0;i<shoots.length;i++)
		{
			shoots[i] = new Shooting();
		}
		
		flag_r = false;
		flag_l = false;
		flag_u = false;
		flag_d = false;
		

				
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException  {
		// TODO Auto-generated method stub


		g.setBackground(Color.black);

		mapa.render(0,0);
		g.drawImage(Resources.getSpritesheet(first_player_picture).getSubImage(0,0,32,32),first_player_x*32,first_player_y*32);

//		s.render(gc, sbg, g);
		
		for(Shooting s : shoots){
			
			s.render(gc, sbg, g);
		}
		
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int alpha) throws SlickException {
		// TODO Auto-generated method stub
		//zmienna od strzalow
		

		
		delta = delta + alpha;
//		if(delta > fire_rate && gc.getInput().isKeyPressed(Input.KEY_SPACE))
//		{
//			
//			fireBullet(new Vector2f(position.getX(),gc.getInput().getMouseY()), new Shooting());
//
//			//shoots[actual_bullet] = new Shooting(new Vector2f(0,50), new Vector2f(500,200));
//			//shoots[actual_bullet].setActive(true);
//			//shoots[actual_bullet].setActual(0);
//
//
//			actual_bullet++;
//			
//			if(actual_bullet >= shoots.length){
//				
//				actual_bullet = 0;
//				shoots[actual_bullet].setActual(0);
//			}
//			
//			delta = 0;
//			
//		}
		
		for(Shooting s : shoots){
			
			s.update(alpha);
		}
		
		int kolizje = mapa.getLayerIndex("Kolizje");
		mapa.getTileId(0, 0, kolizje);
		
		
		if(menustate.gamemusic)
		{
			music.play();
			music.setVolume(0.1f);
			menustate.gamemusic = false;			
		}
		
		if(gc.getInput().isKeyPressed(Input.KEY_ENTER))
			{
				music.stop();
				menustate.play_menu_music();
				sbg.enterState(StatesCodes.MENU);
			}
		
		if(gc.getInput().isKeyPressed(Input.KEY_D) || gc.getInput().isKeyPressed(Input.KEY_RIGHT))
		{
			first_player_picture="sqi_r";
			flag_r = true;
			flag_l = false;
			flag_u = false;
			flag_d = false;
			
			if(mapa.getTileId(first_player_x+1, first_player_y, kolizje)==0)
			{
				first_player_x++;
			}
				
		}
		
		if(gc.getInput().isKeyPressed(Input.KEY_A) || gc.getInput().isKeyPressed(Input.KEY_LEFT))
		{	
			first_player_picture="sqi_l";
			flag_r = false;
			flag_l = true;
			flag_u = false;
			flag_d = false;
			
			if(mapa.getTileId(first_player_x-1, first_player_y, kolizje)==0)
			{
				first_player_x--;
			}
				
		}
			
		if(gc.getInput().isKeyPressed(Input.KEY_W) || gc.getInput().isKeyPressed(Input.KEY_UP))
		{
			flag_r = false;
			flag_l = false;
			flag_u = true;
			flag_d = false;
			if(mapa.getTileId(first_player_x, first_player_y-1, kolizje)==0)
			{
				first_player_y--;
			}
				
		}
		if(gc.getInput().isKeyPressed(Input.KEY_S) || gc.getInput().isKeyPressed(Input.KEY_DOWN))
		{
			flag_r = false;
			flag_l = false;
			flag_u = false;
			flag_d = true;
			if(mapa.getTileId(first_player_x, first_player_y+1, kolizje)==0)
			{
				first_player_y++;
			}
				
		}
		
		//shoot.update(alpha);
//		Iterator<Shooting> i = shoot.iterator();
//		
//		while(i.hasNext()){
//			
//			Shooting s = i.next();
//			if(s.BulletState()){
//				
//				s.update(alpha);
//			}
//			else{
//				
//				i.remove();
//				s.setActual(0);
//			}
//			
//		}
		
		if (gc.getInput().isKeyPressed(Input.KEY_1)) {
			select_1 = true;
			select_2 = false;
		}
		else if (gc.getInput().isKeyPressed(Input.KEY_2)){
			select_1 = false;
			select_2 = true;
		}


		
		
		if (select_1) {

			if (gc.getInput().isKeyPressed(Input.KEY_SPACE)) {

				if (flag_r) {

					posx = first_player_x + 1;
					posy = first_player_y;

					pos_player_x = first_player_x + 32;
					pos_player_y = first_player_y;

					setPositionofPlayer(new Vector2f(pos_player_x, pos_player_y));
					setPosition(new Vector2f(posx, posy));
					fireBullet(new Vector2f(position_of_player), new Shooting());
				}

				if (flag_l) {

					posx = first_player_x - 1;
					posy = first_player_y;

					pos_player_x = first_player_x - 32;
					pos_player_y = first_player_y;

					setPositionofPlayer(new Vector2f(pos_player_x, pos_player_y));
					setPosition(new Vector2f(posx, posy));
					fireBullet(new Vector2f(position_of_player), new Shooting());
				}

				if (flag_u) {

					posx = first_player_x;
					posy = first_player_y - 1;

					pos_player_x = first_player_x;
					pos_player_y = first_player_y - 32;

					setPositionofPlayer(new Vector2f(pos_player_x, pos_player_y));
					setPosition(new Vector2f(posx, posy));
					fireBullet(new Vector2f(position_of_player), new Shooting());
				}

				if (flag_d) {

					posx = first_player_x;
					posy = first_player_y + 1;

					pos_player_x = first_player_x;
					pos_player_y = first_player_y + 32;

					setPositionofPlayer(new Vector2f(pos_player_x, pos_player_y));
					setPosition(new Vector2f(posx, posy));
					fireBullet(new Vector2f(position_of_player), new Shooting());
				}

			}
		}
		
		float deltaLenght = (float)alpha/5;
		
		if(select_2){
			
			if(set_position){
				
				setPosition(new Vector2f(first_player_x, first_player_y));
				set_position = false;
			}

			
			if( gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
			{
				fireBullet(new Vector2f(gc.getInput().getMouseX(),gc.getInput().getMouseY()), new Shooting());

				
				if (flag_r) {
					
					if(position.getX() < 800){
						
						setPosition(new Vector2f(first_player_x + 1, first_player_y));
						position.add( new Vector2f(deltaLenght,0) );

					}
				}

				if (flag_l) {

					if(position.getX() > -20){
						
						setPosition(new Vector2f(first_player_x - 1, first_player_y));
						position.add( new Vector2f(-deltaLenght,0) );

					}
				}

				if (flag_u) {

					if(position.getY() < 640){
						
						setPosition(new Vector2f(first_player_x, first_player_y - 1));
						position.add( new Vector2f(0,deltaLenght) );

					}
				}

				if (flag_d) {

					if(position.getY() < -20){
						
						setPosition(new Vector2f(first_player_x, first_player_y + 1));
						position.add( new Vector2f(0,-deltaLenght) );

					}
				
				
				
				
				
				
				
//				setPositionofPlayer(new Vector2f(first_player_x, first_player_y));
//				setPosition(new Vector2f(position_of_player));
//				fireBullet(new Vector2f(gc.getInput().getMouseX(),gc.getInput().getMouseY()), new Shooting());
			}	
			
			
//			float deltaLenght = (float)alpha/5;
//			
//			if( ( gc.getInput().isKeyDown(Input.KEY_RIGHT) || gc.getInput().isKeyDown(Input.KEY_D) ) && 
//					position.getX() < 800  )
//			{
//				position.add( new Vector2f(deltaLenght,0) );
//			}
//			if( ( gc.getInput().isKeyDown(Input.KEY_LEFT) || gc.getInput().isKeyDown(Input.KEY_A) ) && 
//					position.getX() > 0  )
//			{
//				position.add( new Vector2f(-deltaLenght,0) );
//			}
//			if( ( gc.getInput().isKeyDown(Input.KEY_DOWN) || gc.getInput().isKeyDown(Input.KEY_S) ) && 
//					position.getY() < 640  )
//			{
//				position.add( new Vector2f(0,deltaLenght) );
//			}
//			if( ( gc.getInput().isKeyDown(Input.KEY_UP) || gc.getInput().isKeyDown(Input.KEY_W) ) && 
//					position.getY() > 0  )
//			{
//				position.add( new Vector2f(0,-deltaLenght) );
//			}
			
			
			
		}
		}
			

	}
	
	public void checkShootingCollision ( Shooting[] shoots )
	{
		for( Shooting s : shoots )
		{
//			if ( s.BulletState() && s.collission(position_of_player, radius_squared) )
			if ( s.BulletState() )
			{
				s.setActive(false);
				lives -= s.getDamage();
				if( lives < 1 && is_alive) die();
			}	
		}
	}
		
		public void die(){
			is_alive = false;
		}
		
		
		public void fireBullet(Vector2f vector , Shooting s)
		{
			delta = 0;

			vector.sub(position);
			vector.normalise();
			shoots[actual_bullet] = s.init(position.copy(),vector);
			actual_bullet++; 
			if( actual_bullet >= shoots.length ) actual_bullet = 0;
		}
		
		
		public void setPositionofPlayer(Vector2f vector){
			
			this.position_of_player=vector;
			
		}
		public void setPosition(Vector2f vector){
			
			this.position=vector;
			
		}
		
		public boolean getSelect1(){
			return select_1;
		}
		public boolean getSelect2(){
			return select_2;
		}
	

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return StatesCodes.GAME;
	}

}
