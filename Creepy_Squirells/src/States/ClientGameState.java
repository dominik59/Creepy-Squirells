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

		if (lives <= 0) {
			die_first();
		}
		if (lives_second <= 0) {
			die_second();
		}

		delta = delta + alpha;
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

			if (gc.getInput().isKeyPressed(Input.KEY_SPACE)) {

				if (flag_r_2) {

					second_posx = second_player_x + 1;
					second_posy = second_player_y;

					second_pos_player_x = second_player_x + 32;
					second_pos_player_y = second_player_y;

					setPositionofPlayer_2(new Vector2f(second_pos_player_x, second_pos_player_y));
					setPosition_2(new Vector2f(second_posx, second_posy));
					fireBullet_2(new Vector2f(second_position_of_player), new Shooting());
					did_second_player_fired = true;

				}

				if (flag_l_2) {

					second_posx = second_player_x - 1;
					second_posy = second_player_y;

					second_pos_player_x = second_player_x - 32;
					second_pos_player_y = second_player_y;

					setPositionofPlayer_2(new Vector2f(second_pos_player_x, second_pos_player_y));
					setPosition_2(new Vector2f(second_posx, second_posy));
					fireBullet_2(new Vector2f(second_position_of_player), new Shooting());
					did_second_player_fired = true;

				}

				if (flag_u_2) {

					second_posx = second_player_x;
					second_posy = second_player_y - 1;

					second_pos_player_x = second_player_x;
					second_pos_player_y = second_player_y - 32;

					setPositionofPlayer_2(new Vector2f(second_pos_player_x, second_pos_player_y));
					setPosition_2(new Vector2f(second_posx, second_posy));
					fireBullet_2(new Vector2f(second_position_of_player), new Shooting());
					did_second_player_fired = true;

				}

				if (flag_d_2) {

					second_posx = second_player_x;
					second_posy = second_player_y + 1;

					second_pos_player_x = second_player_x;
					second_pos_player_y = second_player_y + 32;

					setPositionofPlayer_2(new Vector2f(second_pos_player_x, second_pos_player_y));
					setPosition_2(new Vector2f(second_posx, second_posy));
					fireBullet_2(new Vector2f(second_position_of_player), new Shooting());
					did_second_player_fired = true;

				}

			}
		}

		// float deltaLenght = (float)alpha/5;

		if (select_2_2) {

			if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {

				setPosition_2(new Vector2f(second_player_x * 32, second_player_y * 32));
				fireBullet_2(new Vector2f(gc.getInput().getMouseX(), gc.getInput().getMouseY()), new Shooting());
				did_second_player_fired = true;

			}
		}
		
	



		 if(did_first_player_fired){
			 System.out.println("shoot first");

		 setPositionofPlayer(new Vector2f(pos_player_x, pos_player_y));
		 setPosition(new Vector2f(posx, posy));
		 fireBullet(new Vector2f(position_of_player), new Shooting());
		 did_first_player_fired = false;
		 }
		 did_first_player_fired = false;
		did_second_player_fired = false;


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
	
	public Shooting[] getShoots() {
		return shoots;
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
