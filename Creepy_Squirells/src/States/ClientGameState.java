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

		System.out.println("Shit from client: ");
		System.out.println("Second player shoot status: " + did_second_player_fired);
		System.out.println("Second player chosed weapon: " + second_player_currently_choosed_weapon);
		System.out.println("Second player bullet render status: " + end_of_second_player_shoot_rendering);


		
		if (lives <= 0) {
			die_first();
		}
		if (lives_second <= 0) {
			die_second();
		}

		delta = delta + alpha;
		
		for (ShootingClient sc : shoots_client) {

			sc.update(alpha);
		}
		for (Shooting s : shoots) {

			s.update(alpha);
		}

		int kolizje = mapa.getLayerIndex("Kolizje");
		mapa.getTileId(0, 0, kolizje);

		if (menustate.gamemusic) {
			music.play();
			music.loop(1.0f, 0.008f);
			menustate.gamemusic = false;
		}

		if (gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
			if (ClassesInstances.clientTCP != null) {
				ClassesInstances.clientTCP.interrupt();
			}
			if (ClassesInstances.serverTCP != null) {
				ClassesInstances.serverTCP.interrupt();
			}

			music.stop();
			menustate.play_menu_music();
			sbg.enterState(StatesCodes.MENU);
		}

		if (gc.getInput().isKeyPressed(Input.KEY_D) || gc.getInput().isKeyPressed(Input.KEY_RIGHT)) {
			second_player_picture = "sqi_r";
			flag_r_2 = true;
			flag_l_2 = false;
			flag_u_2 = false;
			flag_d_2 = false;

			if (mapa.getTileId(second_player_x + 1, second_player_y, kolizje) == 0) {
				second_player_x++;
			}
			if (second_player_y < 18) {
				wood_step.play();
			} else {
				grass_step.play();
			}
		}

		if (gc.getInput().isKeyPressed(Input.KEY_A) || gc.getInput().isKeyPressed(Input.KEY_LEFT)) {
			second_player_picture = "sqi_l";
			flag_r_2 = false;
			flag_l_2 = true;
			flag_u_2 = false;
			flag_d_2 = false;

			if (mapa.getTileId(second_player_x - 1, second_player_y, kolizje) == 0) {
				second_player_x--;
			}
			if (second_player_y < 18) {
				wood_step.play();
			} else {
				grass_step.play();
			}
		}

		if (gc.getInput().isKeyPressed(Input.KEY_W) || gc.getInput().isKeyPressed(Input.KEY_UP)) {
			second_player_picture = "sqi_b";
			flag_r_2 = false;
			flag_l_2 = false;
			flag_u_2 = true;
			flag_d_2 = false;
			if (mapa.getTileId(second_player_x, second_player_y - 1, kolizje) == 0) {
				second_player_y--;
			}
			if (second_player_y < 18) {
				wood_step.play();
			} else {
				grass_step.play();
			}
		}
		if (gc.getInput().isKeyPressed(Input.KEY_S) || gc.getInput().isKeyPressed(Input.KEY_DOWN)) {
			second_player_picture = "sqi_f";
			flag_r_2 = false;
			flag_l_2 = false;
			flag_u_2 = false;
			flag_d_2 = true;
			if (mapa.getTileId(second_player_x, second_player_y + 1, kolizje) == 0) {
				second_player_y++;
			}
			if (second_player_y < 18) {
				wood_step.play();
			} else {
				grass_step.play();
			}
		}

		if (gc.getInput().isKeyPressed(Input.KEY_1)) {
			select_1_2 = true;
			select_2_2 = false;
		} else if (gc.getInput().isKeyPressed(Input.KEY_2)) {
			select_1_2 = false;
			select_2_2 = true;
		}

		if (select_1_2) {
			
			set_second_player_currently_choosed_weapon(1);

			if (gc.getInput().isKeyPressed(Input.KEY_SPACE)) {

				if (flag_r_2) {

					second_posx = second_player_x*32 + 1;
					second_posy = second_player_y*32;

					second_pos_player_x = second_player_x*32 + 32;
					second_pos_player_y = second_player_y*32;

					setPositionofPlayer_2(new Vector2f(second_pos_player_x, second_pos_player_y));
					setPosition_2(new Vector2f(second_posx, second_posy));
					fireBullet_2(new Vector2f(second_position_of_player), new ShootingClient());
					
					did_second_player_fired = true;

				}

				if (flag_l_2) {

					second_posx = second_player_x*32 - 1;
					second_posy = second_player_y*32;

					second_pos_player_x = second_player_x*32 - 32;
					second_pos_player_y = second_player_y*32;

					setPositionofPlayer_2(new Vector2f(second_pos_player_x, second_pos_player_y));
					setPosition_2(new Vector2f(second_posx, second_posy));
					fireBullet_2(new Vector2f(second_position_of_player), new ShootingClient());
					
					did_second_player_fired = true;

				}

				if (flag_u_2) {

					second_posx = second_player_x*32;
					second_posy = second_player_y*32 - 1;

					second_pos_player_x = second_player_x*32;
					second_pos_player_y = second_player_y*32 - 32;

					setPositionofPlayer_2(new Vector2f(second_pos_player_x, second_pos_player_y));
					setPosition_2(new Vector2f(second_posx, second_posy));
					fireBullet_2(new Vector2f(second_position_of_player), new ShootingClient());
					
					did_second_player_fired = true;

				}

				if (flag_d_2) {

					second_posx = second_player_x*32;
					second_posy = second_player_y*32 + 1;

					second_pos_player_x = second_player_x*32;
					second_pos_player_y = second_player_y*32 + 32;

					setPositionofPlayer_2(new Vector2f(second_pos_player_x, second_pos_player_y));
					setPosition_2(new Vector2f(second_posx, second_posy));
					fireBullet_2(new Vector2f(second_position_of_player), new ShootingClient());
					
					did_second_player_fired = true;

				}

			}
		}

		// float deltaLenght = (float)alpha/5;

		if (select_2_2) {
			
			set_second_player_currently_choosed_weapon(2);


			if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {

				setPosition_2(new Vector2f(second_player_x * 32, second_player_y * 32));
				fireBullet_2(new Vector2f(gc.getInput().getMouseX(), gc.getInput().getMouseY()), new ShootingClient());
				
				set_player_2_fire_status(true);

			}
			checkShootingCollision_2(getShootsClient());

		}
		
		checkShootingCollision_2(getShootsClient());




		if(did_first_player_fired){
			 if(first_player_currently_choosed_weapon == 1){
			 if(first_player_picture.equals("sqi_r")){
				 
				 	posx = first_player_x*32 + 1;
					posy = first_player_y*32;

					pos_player_x = first_player_x*32 + 32;
					pos_player_y = first_player_y*32;

					setPositionofPlayer(new Vector2f(pos_player_x, pos_player_y));
					setPosition(new Vector2f(posx, posy));
					fireBullet(new Vector2f(position_of_player), new Shooting());
					
					
					end_of_first_player_shoot_rendering = true;

					
					 did_first_player_fired = false;

			 }
			 if(first_player_picture.equals("sqi_l")){
				 
					posx = first_player_x*32 - 1;
					posy = first_player_y*32;

					pos_player_x = first_player_x*32 - 32;
					pos_player_y = first_player_y*32;

					setPositionofPlayer(new Vector2f(pos_player_x, pos_player_y));
					setPosition(new Vector2f(posx, posy));
					fireBullet(new Vector2f(position_of_player), new Shooting());
					
					end_of_first_player_shoot_rendering = true;

					
					 did_first_player_fired = false;

			 }
			 if(first_player_picture.equals("sqi_f")){
				 
				 
					posx = first_player_x*32;
					posy = first_player_y*32 + 1;

					pos_player_x = first_player_x*32;
					pos_player_y = first_player_y*32 + 32;

					setPositionofPlayer(new Vector2f(pos_player_x, pos_player_y));
					setPosition(new Vector2f(posx, posy));
					fireBullet(new Vector2f(position_of_player), new Shooting());
					
					end_of_first_player_shoot_rendering = true;

					
					 did_first_player_fired = false;

				 
			 }
			 if(first_player_picture.equals("sqi_b")){
				 
					posx = first_player_x*32;
					posy = first_player_y*32 - 1;

					pos_player_x = first_player_x*32;
					pos_player_y = first_player_y*32 - 32;

					setPositionofPlayer(new Vector2f(pos_player_x, pos_player_y));
					setPosition(new Vector2f(posx, posy));
					fireBullet(new Vector2f(position_of_player), new Shooting());
					
					end_of_first_player_shoot_rendering = true;

					
					 did_first_player_fired = false;

				 
			 }
		
		 }		
		}
		
		
if(end_of_second_player_shoot_rendering){
	did_second_player_fired = false;
	end_of_second_player_shoot_rendering = false;
}

time_second = time_second + alpha;
System.out.println(time_second);
if(time_second >= 80){
	time_second = 0;
	did_second_player_fired = false;
}


		
	}
	
	public void checkShootingCollision_2(ShootingClient[] shoots_client) {
		for (ShootingClient sc : shoots_client) {
			if (sc.BulletState()
					&& (sc.collission(new Vector2f(first_player_x * 32, first_player_y * 32), radius_squared) || sc.collission(new Vector2f(first_player_x, first_player_y), radius_squared)))
				if (sc.BulletState()) {
					sc.setActive(false);
					lives = lives - sc.getDamage();
					get_lives();
					if (lives < 1 && is_alive_second)
						die_first();
				}
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return StatesCodes.CLIENT_GAME;
	}

	public void setPositionofPlayer_2(Vector2f vector) {

		this.second_position_of_player = vector;

	}

	public void setPosition_2(Vector2f vector) {

		this.second_position = vector;

	}
	
	public ShootingClient[] getShootsClient() {
		return shoots_client;
	}

	public Integer get_player_2_x_position() {
		return second_player_x;
	}

	public Integer get_player_2_y_position() {
		return second_player_y;
	}

	public void set_player_1_x_position(Integer x) {
		first_player_x = x;
	}

	public void set_player_1_y_position(Integer y) {
		first_player_y = y;
	}

	public Boolean get_player_2_fire_status() {
		return did_second_player_fired;
	}

	public void set_player_1_fire_status(Boolean status) {
		did_first_player_fired = status;
	}

}
