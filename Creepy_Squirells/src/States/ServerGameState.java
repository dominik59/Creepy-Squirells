
package States;

import java.awt.Font;
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
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import Core.ClassesInstances;
import Core.Resources;
import Core.Window;
import States.MenuState;
import States.Shooting;
import States.ShootingClient;


public class ServerGameState extends BasicGameState {
	
	protected UnicodeFont gameFont;
	
	protected Integer first_player_x;
	protected Integer first_player_y;
	protected Integer second_player_x;
	protected Integer second_player_y;

	protected String first_player_picture = "sqi_r";
	protected String second_player_picture = "sqi_l";

	protected static Integer posx = 0;
	protected static Integer posy = 0;
	protected static Integer second_posx = 0;
	protected static Integer second_posy = 0;

	protected static Integer pos_player_x = 0;
	protected static Integer pos_player_y = 0;
	protected static Integer second_pos_player_x = 0;
	protected static Integer second_pos_player_y = 0;

	protected Music sound;
	protected Music music;
	protected Sound wood_step;
	protected Sound grass_step;

	protected TiledMap mapa;

	// private Shooting shoot;
	// private LinkedList<Shooting> shoot;
	protected Shooting[] shoots;
	protected ShootingClient[] shoots_client;

	protected static int fire_rate = 100;
	protected int actual_bullet = 0;
	protected int actual_bullet_client = 0;

	public int delta = 0;
	protected int radius_squared;

	protected Boolean set_position = true;
	protected Boolean set_position_2 = true;

	protected Vector2f position_of_player;
	protected Vector2f position;
	protected Vector2f second_position_of_player;
	protected Vector2f second_position;

	protected Integer lives;
	protected Integer lives_second;

	protected Boolean is_alive_first = true;
	protected Boolean is_alive_second = true;

	protected Boolean flag_r;
	protected Boolean flag_l;
	protected Boolean flag_u;
	protected Boolean flag_d;

	protected Boolean flag_r_2;
	protected Boolean flag_l_2;
	protected Boolean flag_u_2;
	protected Boolean flag_d_2;

	protected static Boolean select_1 = false;
	protected static Boolean select_2 = false;

	protected static Boolean select_1_2 = false;
	protected static Boolean select_2_2 = false;

	protected Boolean did_first_player_fired = false;
	protected Boolean did_second_player_fired = false;

	protected Shape enemy;
	protected Shape shoot;
	
	protected  int time_serwer = 0;
	protected int  time_client = 0;
	

	MenuState menustate = ClassesInstances.menuState;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		gameFont = new UnicodeFont(new java.awt.Font("Arial", Font.BOLD, 50));
		gameFont.getEffects().add(new ColorEffect(java.awt.Color.white));
		gameFont.addGlyphs("ąćłóężźńś"); // szczególnie ważna jest ta linijka bo
											// to ona dodaje polskie znaki
		gameFont.addNeheGlyphs();
		gameFont.loadGlyphs();
		
		mapa = Resources.getMap("level1");

		sound = Resources.getAudio("level1_music");
		music = Resources.getAudio("level1_sound");
		wood_step = Resources.getSound("wood_step");
		grass_step = Resources.getSound("grass_step");

		first_player_x = 2;
		first_player_y = 18;

		second_player_x = 22;
		second_player_y = 18;
		// 10,18

		lives = 3;
		lives_second = 3;
		// shoot = new Shooting(new Vector2f(0,100), new Vector2f(200,100));
		// shoot = new LinkedList<Shooting>();

		shoots = new Shooting[20];

		for (int i = 0; i < shoots.length; i++) {
			shoots[i] = new Shooting();
		}
		
		shoots_client = new ShootingClient[20];

		for (int i = 0; i < shoots_client.length; i++) {
			shoots_client[i] = new ShootingClient();
		}

		flag_r = false;
		flag_l = false;
		flag_u = false;
		flag_d = false;

		flag_r_2 = false;
		flag_l_2 = false;
		flag_u_2 = false;
		flag_d_2 = false;

		enemy = new Rectangle(second_player_x * 32 + 1, second_player_y * 32 + 1, 32, 32);
		shoot = new Rectangle(1, 1, 32, 32);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.setFont(gameFont);
		
		g.setBackground(Color.black);

		mapa.render(0, 0);
		g.drawImage(Resources.getSpritesheet(first_player_picture).getSubImage(0, 0, 32, 32), first_player_x * 32,
				first_player_y * 32);
		g.drawImage(Resources.getSpritesheet(second_player_picture).getSubImage(0, 0, 32, 32), second_player_x * 32,
				second_player_y * 32);
				// s.render(gc, sbg, g);

		// do debudowania postaci przeciwnika
		drawDebugLines(g, 32);
		g.setColor(Color.cyan);
		g.draw(enemy);
		g.setColor(Color.yellow);
		g.draw(shoot);

		/**
		 * życie pierwszej postaci
		 */
		if (lives == 3) {
			g.drawImage(Resources.getSpritesheet("player_hp").getSubImage(0, 0, 32, 28), 0 + 20, 10);
			g.drawImage(Resources.getSpritesheet("player_hp").getSubImage(0, 0, 32, 28), 0 + 60, 10);
			g.drawImage(Resources.getSpritesheet("player_hp").getSubImage(0, 0, 32, 28), 0 + 100, 10);
		} else if (lives == 2) {
			g.drawImage(Resources.getSpritesheet("gray_hp").getSubImage(0, 0, 32, 28), 0 + 20, 10);
			g.drawImage(Resources.getSpritesheet("player_hp").getSubImage(0, 0, 32, 28), 0 + 60, 10);
			g.drawImage(Resources.getSpritesheet("player_hp").getSubImage(0, 0, 32, 28), 0 + 100, 10);
		} else if (lives == 1) {
			g.drawImage(Resources.getSpritesheet("gray_hp").getSubImage(0, 0, 32, 28), 0 + 20, 10);
			g.drawImage(Resources.getSpritesheet("gray_hp").getSubImage(0, 0, 32, 28), 0 + 60, 10);
			g.drawImage(Resources.getSpritesheet("player_hp").getSubImage(0, 0, 32, 28), 0 + 100, 10);

		} else {
			g.drawImage(Resources.getSpritesheet("gray_hp").getSubImage(0, 0, 32, 28), 0 + 20, 10);
			g.drawImage(Resources.getSpritesheet("gray_hp").getSubImage(0, 0, 32, 28), 0 + 60, 10);
			g.drawImage(Resources.getSpritesheet("gray_hp").getSubImage(0, 0, 32, 28), 0 + 100, 10);
		}

		/**
		 * życie drugiej postaci
		 */
		if (lives_second == 3) {
			g.drawImage(Resources.getSpritesheet("player_hp").getSubImage(0, 0, 32, 28), Window.width + 20, 10);
			g.drawImage(Resources.getSpritesheet("player_hp").getSubImage(0, 0, 32, 28), Window.width + 60, 10);
			g.drawImage(Resources.getSpritesheet("player_hp").getSubImage(0, 0, 32, 28), Window.width + 100, 10);
		} else if (lives_second == 2) {
			g.drawImage(Resources.getSpritesheet("gray_hp").getSubImage(0, 0, 32, 28), Window.width + 20, 10);
			g.drawImage(Resources.getSpritesheet("player_hp").getSubImage(0, 0, 32, 28), Window.width + 60, 10);
			g.drawImage(Resources.getSpritesheet("player_hp").getSubImage(0, 0, 32, 28), Window.width + 100, 10);
		} else if (lives_second == 1) {
			g.drawImage(Resources.getSpritesheet("gray_hp").getSubImage(0, 0, 32, 28), Window.width + 20, 10);
			g.drawImage(Resources.getSpritesheet("gray_hp").getSubImage(0, 0, 32, 28), Window.width + 60, 10);
			g.drawImage(Resources.getSpritesheet("player_hp").getSubImage(0, 0, 32, 28), Window.width + 100, 10);

		} else {
			g.drawImage(Resources.getSpritesheet("gray_hp").getSubImage(0, 0, 32, 28), Window.width + 20, 10);
			g.drawImage(Resources.getSpritesheet("gray_hp").getSubImage(0, 0, 32, 28), Window.width + 60, 10);
			g.drawImage(Resources.getSpritesheet("gray_hp").getSubImage(0, 0, 32, 28), Window.width + 100, 10);
		}

		for (Shooting s : shoots) {

			s.render(gc, sbg, g);
		}
		
		for (ShootingClient sc : shoots_client) {

			sc.render(gc, sbg, g);
		}
		
		
		
		
		if(!(is_alive_first && is_alive_second))
		{
			g.drawString("KONIEC GRY", Window.width/2-80, Window.height/2-120);
		}
	}

	protected void drawDebugLines(Graphics g, int i) {

		int resolution = 800;
		g.setColor(Color.red);
		for (int a = 0; a < resolution; a = a + i) {
			g.drawLine(a, 0, a, resolution);
			g.drawLine(0, a, resolution, a);
		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int alpha) throws SlickException {
		// TODO Auto-generated method stub
		
		if(time_serwer  >= 40){
			did_first_player_fired = false;
			time_serwer = 0;
		}
		
		
		
		
		// zmienna od strzalow
		if (lives <= 0) {
			die_first();
		}
		if (lives_second <= 0) {
			die_second();
		}
		// aktualizuje wspolrzedne przeciwnika
		enemy.setLocation(second_player_x * 32 + 1, second_player_y * 32 + 1);
		for (Shooting s : shoots) {

			if (s.BulletState()) {
				if (select_1) {

					shoot.setLocation(s.getShootPosition().getX() * 32 + 1, s.getShootPosition().getY() * 32 + 1);

				} else if (select_2) {

					shoot.setLocation(s.getShootPosition().getX() + 1, s.getShootPosition().getY() + 1);

				}
			}

		}

		delta = delta + alpha;

		
		for (Shooting s : shoots) {

			s.update(alpha);
		}
		for (ShootingClient sc : shoots_client) {

			sc.update(alpha);
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
			first_player_picture = "sqi_r";
			flag_r = true;
			flag_l = false;
			flag_u = false;
			flag_d = false;

			if (mapa.getTileId(first_player_x + 1, first_player_y, kolizje) == 0) {
				first_player_x++;
			}
			if (first_player_y < 18) {
				wood_step.play();
			} else {
				grass_step.play();
			}
		}

		if (gc.getInput().isKeyPressed(Input.KEY_A) || gc.getInput().isKeyPressed(Input.KEY_LEFT)) {
			first_player_picture = "sqi_l";
			flag_r = false;
			flag_l = true;
			flag_u = false;
			flag_d = false;

			if (mapa.getTileId(first_player_x - 1, first_player_y, kolizje) == 0) {
				first_player_x--;
			}
			if (first_player_y < 18) {
				wood_step.play();
			} else {
				grass_step.play();
			}
		}

		if (gc.getInput().isKeyPressed(Input.KEY_W) || gc.getInput().isKeyPressed(Input.KEY_UP)) {
			first_player_picture = "sqi_b";
			flag_r = false;
			flag_l = false;
			flag_u = true;
			flag_d = false;
			if (mapa.getTileId(first_player_x, first_player_y - 1, kolizje) == 0) {
				first_player_y--;
			}
			if (first_player_y < 18) {
				wood_step.play();
			} else {
				grass_step.play();
			}
		}
		if (gc.getInput().isKeyPressed(Input.KEY_S) || gc.getInput().isKeyPressed(Input.KEY_DOWN)) {
			first_player_picture = "sqi_f";
			flag_r = false;
			flag_l = false;
			flag_u = false;
			flag_d = true;
			if (mapa.getTileId(first_player_x, first_player_y + 1, kolizje) == 0) {
				first_player_y++;
			}
			if (first_player_y < 18) {
				wood_step.play();
			} else {
				grass_step.play();
			}
		}

		// shoot.update(alpha);
		// Iterator<Shooting> i = shoot.iterator();
		//
		// while(i.hasNext()){
		//
		// Shooting s = i.next();
		// if(s.BulletState()){
		//
		// s.update(alpha);
		// }
		// else{
		//
		// i.remove();
		// s.setActual(0);
		// }
		//
		// }

		if (gc.getInput().isKeyPressed(Input.KEY_1)) {
			select_1 = true;
			select_2 = false;
		} else if (gc.getInput().isKeyPressed(Input.KEY_2)) {
			select_1 = false;
			select_2 = true;
		}

		if (select_1) {

			if (gc.getInput().isKeyPressed(Input.KEY_SPACE)) {

				if (flag_r) {

					posx = first_player_x*32 + 1;
					posy = first_player_y*32;

					pos_player_x = first_player_x*32 + 32;
					pos_player_y = first_player_y*32;

					setPositionofPlayer(new Vector2f(pos_player_x, pos_player_y));
					setPosition(new Vector2f(posx, posy));
					fireBullet(new Vector2f(position_of_player), new Shooting());
					did_first_player_fired = true;
				}

				if (flag_l) {

					posx = first_player_x*32 - 1;
					posy = first_player_y*32;

					pos_player_x = first_player_x*32 - 32;
					pos_player_y = first_player_y*32;

					setPositionofPlayer(new Vector2f(pos_player_x, pos_player_y));
					setPosition(new Vector2f(posx, posy));
					fireBullet(new Vector2f(position_of_player), new Shooting());
					did_first_player_fired = true;

				}

				if (flag_u) {

					posx = first_player_x*32;
					posy = first_player_y*32 - 1;

					pos_player_x = first_player_x*32;
					pos_player_y = first_player_y*32 - 32;

					setPositionofPlayer(new Vector2f(pos_player_x, pos_player_y));
					setPosition(new Vector2f(posx, posy));
					fireBullet(new Vector2f(position_of_player), new Shooting());
					did_first_player_fired = true;

				}

				if (flag_d) {

					posx = first_player_x*32;
					posy = first_player_y*32 + 1;

					pos_player_x = first_player_x*32;
					pos_player_y = first_player_y*32 + 32;

					setPositionofPlayer(new Vector2f(pos_player_x, pos_player_y));
					setPosition(new Vector2f(posx, posy));
					fireBullet(new Vector2f(position_of_player), new Shooting());
					did_first_player_fired = true;

				}

			}
		}

		// float deltaLenght = (float) alpha / 5;

		if (select_2) {

			if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {

				setPosition(new Vector2f(first_player_x * 32, first_player_y * 32));
				fireBullet(new Vector2f(gc.getInput().getMouseX(), gc.getInput().getMouseY()), new Shooting());
				did_first_player_fired = true;

			}
			


			//
			// if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			// fireBullet(new Vector2f(gc.getInput().getMouseX(),
			// gc.getInput().getMouseY()), new Shooting());
			//
			// if (flag_r) {
			//
			// if (position.getX() < 800) {
			//
			// setPosition(new Vector2f(first_player_x + 1, first_player_y));
			// position.add(new Vector2f(deltaLenght, 0));
			//
			// }
			// }
			//
			// if (flag_l) {
			//
			// if (position.getX() > -20) {
			//
			// setPosition(new Vector2f(first_player_x - 1, first_player_y));
			// position.add(new Vector2f(-deltaLenght, 0));
			//
			// }
			// }
			//
			// if (flag_u) {
			//
			// if (position.getY() < 640) {
			//
			// setPosition(new Vector2f(first_player_x, first_player_y - 1));
			// position.add(new Vector2f(0, deltaLenght));
			//
			// }
			// }
			//
			// if (flag_d) {
			//
			// if (position.getY() < -20) {
			//
			// setPosition(new Vector2f(first_player_x, first_player_y + 1));
			// position.add(new Vector2f(0, -deltaLenght));
			//
			// }
			//
			// // setPositionofPlayer(new Vector2f(first_player_x,
			// // first_player_y));
			// // setPosition(new Vector2f(position_of_player));
			// // fireBullet(new
			// // Vector2f(gc.getInput().getMouseX(),gc.getInput().getMouseY()),
			// // new Shooting());
			// }

			// float deltaLenght = (float)alpha/5;
			//
			// if( ( gc.getInput().isKeyDown(Input.KEY_RIGHT) ||
			// gc.getInput().isKeyDown(Input.KEY_D) ) &&
			// position.getX() < 800 )
			// {
			// position.add( new Vector2f(deltaLenght,0) );
			// }
			// if( ( gc.getInput().isKeyDown(Input.KEY_LEFT) ||
			// gc.getInput().isKeyDown(Input.KEY_A) ) &&
			// position.getX() > 0 )
			// {
			// position.add( new Vector2f(-deltaLenght,0) );
			// }
			// if( ( gc.getInput().isKeyDown(Input.KEY_DOWN) ||
			// gc.getInput().isKeyDown(Input.KEY_S) ) &&
			// position.getY() < 640 )
			// {
			// position.add( new Vector2f(0,deltaLenght) );
			// }
			// if( ( gc.getInput().isKeyDown(Input.KEY_UP) ||
			// gc.getInput().isKeyDown(Input.KEY_W) ) &&
			// position.getY() > 0 )
			// {
			// position.add( new Vector2f(0,-deltaLenght) );
			// }

			checkShootingCollision(getShoots());

		}
		
		checkShootingCollision(getShoots());
		
		 if(did_second_player_fired){
			 
			 if(second_player_picture.equals("sqi_r")){
				 
				 	second_posx = second_player_x*32 + 1;
					second_posy = second_player_y*32;

					second_pos_player_x = second_player_x*32 + 32;
					second_pos_player_y = second_player_y*32;

					setPositionofPlayer_2(new Vector2f(second_pos_player_x, second_pos_player_y));
					setPosition_2(new Vector2f(second_posx, second_posy));
					fireBullet_2(new Vector2f(second_position_of_player), new ShootingClient());
					
					 did_second_player_fired = false;

			 }
			 if(second_player_picture.equals("sqi_l")){
				 
					second_posx = second_player_x*32 - 1;
					second_posy = second_player_y*32;

					second_pos_player_x = second_player_x*32 - 32;
					second_pos_player_y = second_player_y*32;

					setPositionofPlayer_2(new Vector2f(second_pos_player_x, second_pos_player_y));
					setPosition_2(new Vector2f(second_posx, second_posy));
					fireBullet_2(new Vector2f(second_position_of_player), new ShootingClient());
					
					 did_second_player_fired = false;

			 }
			 if(second_player_picture.equals("sqi_f")){
				 
				 
					second_posx = second_player_x*32;
					second_posy = second_player_y*32 + 1;

					second_pos_player_x = second_player_x*32;
					second_pos_player_y = second_player_y*32 + 32;

					setPositionofPlayer_2(new Vector2f(second_pos_player_x, second_pos_player_y));
					setPosition_2(new Vector2f(second_posx, second_posy));
					fireBullet_2(new Vector2f(second_position_of_player), new ShootingClient());
					
					 did_second_player_fired = false;

				 
			 }
			 if(second_player_picture.equals("sqi_b")){
				 
					second_posx = second_player_x*32;
					second_posy = second_player_y*32 - 1;

					second_pos_player_x = second_player_x*32;
					second_pos_player_y = second_player_y*32 - 32;

					setPositionofPlayer_2(new Vector2f(second_pos_player_x, second_pos_player_y));
					setPosition_2(new Vector2f(second_posx, second_posy));
					fireBullet_2(new Vector2f(second_position_of_player), new ShootingClient());
					
					 did_second_player_fired = false;

				 
			 }
		
		 }		


	}

	public void checkShootingCollision(Shooting[] shoots) {
		for (Shooting s : shoots) {
			if (s.BulletState()
					&& (s.collission(new Vector2f(second_player_x * 32, second_player_y * 32), radius_squared) || s.collission(new Vector2f(second_player_x, second_player_y), radius_squared)))
				if (s.BulletState()) {
					s.setActive(false);
					lives_second = lives_second - s.getDamage();
					get_lives_second();
					if (lives_second < 1 && is_alive_first)
						die_second();
				}
		}
	}

	public Shooting[] getShoots() {
		return shoots;
	}

	public void die_first() {
		is_alive_first = false;
		first_player_picture = "dead";
	}

	public void die_second() {
		is_alive_second = false;
		second_player_picture = "dead";
	}

	public void fireBullet(Vector2f vector, Shooting s) {
		delta = 0;

		vector.sub(position);
		vector.normalise();
		shoots[actual_bullet] = s.init(position.copy(), vector);
		actual_bullet++;
		if (actual_bullet >= shoots.length)
			actual_bullet = 0;
	}

	public void fireBullet_2(Vector2f vector, ShootingClient sc) {
		delta = 0;

		vector.sub(second_position);
		vector.normalise();
		shoots_client[actual_bullet_client] = sc.init(second_position.copy(), vector);
		actual_bullet_client++;
		if (actual_bullet_client >= shoots_client.length)
			actual_bullet_client = 0;
	}

	public void setPositionofPlayer(Vector2f vector) {

		this.position_of_player = vector;

	}

	public void setPosition(Vector2f vector) {

		this.position = vector;

	}

	public void setPositionofPlayer_2(Vector2f vector) {

		this.second_position_of_player = vector;

	}

	public void setPosition_2(Vector2f vector) {

		this.second_position = vector;

	}

	public boolean getSelect1() {
		return select_1;
	}

	public boolean getSelect2() {
		return select_2;
	}

	public boolean getSelect1_2() {
		return select_1_2;
	}

	public boolean getSelect2_2() {
		return select_2_2;
	}

	public Integer get_player_1_x_position() {
		return first_player_x;
	}

	public Integer get_player_1_y_position() {
		return first_player_y;
	}

	public void set_player_2_x_position(Integer x) {
		second_player_x = x;
	}

	public void set_player_2_y_position(Integer y) {
		second_player_y = y;
	}

	public Boolean get_player_1_fire_status() {
		return did_first_player_fired;
	}

	public void set_player_2_fire_status(Boolean status) {
		did_second_player_fired = status;
	}

	public String get_first_player_picture() {
		return first_player_picture;
	}

	public void set_first_player_picture(String name) {
		first_player_picture = name;
	}

	public String get_second_player_picture() {
		return second_player_picture;
	}

	public void set_second_player_picture(String name) {
		second_player_picture = name;
	}

	public Integer get_posx() {
		return posx;
	}

	public void set_posx(Integer value) {
		posx = value;
	}

	public Integer get_posy() {
		return posy;
	}

	public void set_posy(Integer value) {
		posy = value;
	}

	public Integer get_second_posx() {
		return second_posx;
	}

	public void set_second_posx(Integer value) {
		second_posx = value;
	}

	public Integer get_second_posy() {
		return second_posx;
	}

	public void set_second_posy(Integer value) {
		second_posy = value;
	}

	public Integer get_lives() {
		return lives;
	}

	public void set_lives(Integer how_many) {
		lives = how_many;
	}

	public Integer get_lives_second() {
		return lives_second;
	}

	public void set_lives_second(Integer how_many) {
		lives_second = how_many;
	}

	public Integer get_pos_player_x() {
		return pos_player_x;
	}

	public void set_pos_player_x(Integer value) {
		pos_player_x = value;
	}

	public Integer get_pos_player_y() {
		return pos_player_y;
	}

	public void set_pos_player_y(Integer value) {
		pos_player_y = value;
	}

	public Integer get_second_pos_player_x() {
		return second_pos_player_x;
	}

	public void set_second_pos_player_x(Integer value) {
		System.out.println(second_pos_player_x = value);
	}

	public Integer get_second_pos_player_y() {
		return second_pos_player_y;
	}

	public void set_second_pos_player_y(Integer value) {
		second_pos_player_y = value;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return StatesCodes.SERVER_GAME;
	}

}
