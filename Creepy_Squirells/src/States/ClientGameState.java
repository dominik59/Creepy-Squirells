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
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				//zmienna od strzalow
				

				
				delta = delta + alpha;
//				if(delta > fire_rate && gc.getInput().isKeyPressed(Input.KEY_SPACE))
//				{
//					
//					fireBullet(new Vector2f(position.getX(),gc.getInput().getMouseY()), new Shooting());
		//
//					//shoots[actual_bullet] = new Shooting(new Vector2f(0,50), new Vector2f(500,200));
//					//shoots[actual_bullet].setActive(true);
//					//shoots[actual_bullet].setActual(0);
		//
		//
//					actual_bullet++;
//					
//					if(actual_bullet >= shoots.length){
//						
//						actual_bullet = 0;
//						shoots[actual_bullet].setActual(0);
//					}
//					
//					delta = 0;
//					
//				}
				
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
//				Iterator<Shooting> i = shoot.iterator();
//				
//				while(i.hasNext()){
//					
//					Shooting s = i.next();
//					if(s.BulletState()){
//						
//						s.update(alpha);
//					}
//					else{
//						
//						i.remove();
//						s.setActual(0);
//					}
//					
//				}
				
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
						
						
						
						
						
						
						
//						setPositionofPlayer(new Vector2f(first_player_x, first_player_y));
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

}
