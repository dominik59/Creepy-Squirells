package States;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import Core.ClassesInstances;

public class ClientGameState extends ServerGameState {

	
	

	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int alpha) throws SlickException {
	
				

				
				delta = delta + alpha;
				for(Shooting s : shoots){
					
					s.update(alpha);
				}
				
				int kolizje = mapa.getLayerIndex("Kolizje");
				mapa.getTileId(0, 0, kolizje);
				
				
				if(menustate.gamemusic)
				{
					music.play();
					music.setVolume(0.008f);
					menustate.gamemusic = false;			
				}
				
				if(gc.getInput().isKeyPressed(Input.KEY_ENTER))
					{
						if(ClassesInstances.clientTCP!=null)
						{
							ClassesInstances.clientTCP.interrupt();
						}
						if(ClassesInstances.serverTCP!=null)
						{
							ClassesInstances.serverTCP.interrupt();
						}
						

						music.stop();
						menustate.play_menu_music();
						sbg.enterState(StatesCodes.MENU);
					}
				
				if(gc.getInput().isKeyPressed(Input.KEY_D) || gc.getInput().isKeyPressed(Input.KEY_RIGHT))
				{
					second_player_picture="sqi_r";
					flag_r = true;
					flag_l = false;
					flag_u = false;
					flag_d = false;
					
					if(mapa.getTileId(second_player_x+1, second_player_y, kolizje)==0)
					{
						second_player_x++;
					}
						
				}
				
				if(gc.getInput().isKeyPressed(Input.KEY_A) || gc.getInput().isKeyPressed(Input.KEY_LEFT))
				{	
					second_player_picture="sqi_l";
					flag_r = false;
					flag_l = true;
					flag_u = false;
					flag_d = false;
					
					if(mapa.getTileId(second_player_x-1, second_player_y, kolizje)==0)
					{
						second_player_x--;
					}
						
				}
					
				if(gc.getInput().isKeyPressed(Input.KEY_W) || gc.getInput().isKeyPressed(Input.KEY_UP))
				{
					flag_r = false;
					flag_l = false;
					flag_u = true;
					flag_d = false;
					if(mapa.getTileId(second_player_x, second_player_y-1, kolizje)==0)
					{
						second_player_y--;
					}
						
				}
				if(gc.getInput().isKeyPressed(Input.KEY_S) || gc.getInput().isKeyPressed(Input.KEY_DOWN))
				{
					flag_r = false;
					flag_l = false;
					flag_u = false;
					flag_d = true;
					if(mapa.getTileId(second_player_x, second_player_y+1, kolizje)==0)
					{
						second_player_y++;
					}
						
				}
				
				
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

							posx = second_player_x + 1;
							posy = second_player_y;

							pos_player_x = second_player_x + 32;
							pos_player_y = second_player_y;

							setPositionofPlayer(new Vector2f(pos_player_x, pos_player_y));
							setPosition(new Vector2f(posx, posy));
							fireBullet(new Vector2f(position_of_player), new Shooting());
						}

						if (flag_l) {

							posx = second_player_x - 1;
							posy = second_player_y;

							pos_player_x = second_player_x - 32;
							pos_player_y = second_player_y;

							setPositionofPlayer(new Vector2f(pos_player_x, pos_player_y));
							setPosition(new Vector2f(posx, posy));
							fireBullet(new Vector2f(position_of_player), new Shooting());
						}

						if (flag_u) {

							posx = second_player_x;
							posy = second_player_y - 1;

							pos_player_x = second_player_x;
							pos_player_y = second_player_y - 32;

							setPositionofPlayer(new Vector2f(pos_player_x, pos_player_y));
							setPosition(new Vector2f(posx, posy));
							fireBullet(new Vector2f(position_of_player), new Shooting());
						}

						if (flag_d) {

							posx = second_player_x;
							posy = second_player_y + 1;

							pos_player_x = second_player_x;
							pos_player_y = second_player_y + 32;

							setPositionofPlayer(new Vector2f(pos_player_x, pos_player_y));
							setPosition(new Vector2f(posx, posy));
							fireBullet(new Vector2f(position_of_player), new Shooting());
						}

					}
				}
				
				float deltaLenght = (float)alpha/5;
				
				if(select_2){
					
					if(set_position){
						
						setPosition(new Vector2f(second_player_x, second_player_y));
						set_position = false;
					}

					
					if( gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
					{
						fireBullet(new Vector2f(gc.getInput().getMouseX(),gc.getInput().getMouseY()), new Shooting());

						
						if (flag_r) {
							
							if(position.getX() < 800){
								
								setPosition(new Vector2f(second_player_x + 1, second_player_y));
								position.add( new Vector2f(deltaLenght,0) );

							}
						}

						if (flag_l) {

							if(position.getX() > -20){
								
								setPosition(new Vector2f(second_player_x - 1, second_player_y));
								position.add( new Vector2f(-deltaLenght,0) );

							}
						}

						if (flag_u) {

							if(position.getY() < 640){
								
								setPosition(new Vector2f(second_player_x, second_player_y - 1));
								position.add( new Vector2f(0,deltaLenght) );

							}
						}

						if (flag_d) {

							if(position.getY() < -20){
								
								setPosition(new Vector2f(second_player_x, second_player_y + 1));
								position.add( new Vector2f(0,-deltaLenght) );

							}
						
						
						
						
						
						
						
//						setPositionofPlayer(new Vector2f(second_player_x, second_player_y));
//						setPosition(new Vector2f(position_of_player));
//						fireBullet(new Vector2f(gc.getInput().getMouseX(),gc.getInput().getMouseY()), new Shooting());
					}	
					
					
//					float deltaLenght = (float)alpha/5;
//					
//					if( ( gc.getInput().isKeyDown(Input.KEY_RIGHT) || gc.getInput().isKeyDown(Input.KEY_D) ) && 
//							position.getX() < 800  )
//					{
//						position.add( new Vector2f(deltaLenght,0) );
//					}
//					if( ( gc.getInput().isKeyDown(Input.KEY_LEFT) || gc.getInput().isKeyDown(Input.KEY_A) ) && 
//							position.getX() > 0  )
//					{
//						position.add( new Vector2f(-deltaLenght,0) );
//					}
//					if( ( gc.getInput().isKeyDown(Input.KEY_DOWN) || gc.getInput().isKeyDown(Input.KEY_S) ) && 
//							position.getY() < 640  )
//					{
//						position.add( new Vector2f(0,deltaLenght) );
//					}
//					if( ( gc.getInput().isKeyDown(Input.KEY_UP) || gc.getInput().isKeyDown(Input.KEY_W) ) && 
//							position.getY() > 0  )
//					{
//						position.add( new Vector2f(0,-deltaLenght) );
//					}
					
					
					
				}
				}
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return StatesCodes.CLIENT_GAME;
	}
	
	public Integer get_player_2_x_position()
	{
		return second_player_x;			
	}
	public Integer get_player_2_y_position()
	{
		return second_player_y;			
	}
	public void set_player_1_x_position(Integer x)
	{
		first_player_x=x;			
	}
	public void set_player_1_y_position(Integer y)
	{
		first_player_y=y;			
	}
	public Boolean get_player_2_fire_status() {
		return did_second_player_fired;
	}

	public void set_player_1_fire_status(Boolean status) {
		did_first_player_fired=status;
	}
	

}
