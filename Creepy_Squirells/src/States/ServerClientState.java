package States;

import java.awt.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


import Connection.ClientTCP;
import Connection.ServerTCP;

import Core.ClassesInstances;
import Core.Resources;
import Core.Window;

public class ServerClientState extends BasicGameState {
	private UnicodeFont menuFont;

	private boolean menu_active;

	private Sound click_sound;
	private MenuState menustate = ClassesInstances.menuState;

	private String connection_adress="127.0.0.1";

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub

		menuFont = new UnicodeFont(new java.awt.Font("Arial", Font.BOLD, 20));
		menuFont.getEffects().add(new ColorEffect(java.awt.Color.white));
		menuFont.addGlyphs("ąćłóężźńś"); // szczególnie ważna jest ta linijka bo
											// to ona dodaje polskie znaki
		menuFont.addNeheGlyphs();
		menuFont.loadGlyphs();

		menu_active = true;


		click_sound = new Sound("/click_sound.wav");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.setFont(menuFont);
		g.drawImage(Resources.getImage("button_play"), Window.width / 2 - 50, 50);
		g.drawString("S: Graj jako SERWER", Window.width / 2, 85);

		g.drawImage(Resources.getImage("button_play"), Window.width / 2 - 50, 150);
		g.drawString("K: Graj jako KLIENT", Window.width / 2 - 10, 185);

		g.drawImage(Resources.getImage("button_play"), Window.width / 2 - 50, 250);
		g.drawString("ESC: Wstecz", Window.width / 2 - 10, 285);
		g.drawString("Wpisz adres IP pod który ma się połączyć klient:", Window.width / 2 - 140, 350);
		g.drawString(connection_adress, Window.width / 2 + 50, 385);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int alpha) throws SlickException {
		// TODO Auto-generated method stub
		if(gc.getInput().isKeyPressed(Input.KEY_0)) {
			click_sound.play();
			connection_adress+="0";
		}
		if(gc.getInput().isKeyPressed(Input.KEY_1)) {
			click_sound.play();
			connection_adress+="1";
		}
		if(gc.getInput().isKeyPressed(Input.KEY_2)) {
			click_sound.play();
			connection_adress+="2";
		}
		if(gc.getInput().isKeyPressed(Input.KEY_3)) {
			click_sound.play();
			connection_adress+="3";
		}
		if(gc.getInput().isKeyPressed(Input.KEY_4)) {
			click_sound.play();
			connection_adress+="4";
		}
		if(gc.getInput().isKeyPressed(Input.KEY_5)) {
			click_sound.play();
			connection_adress+="5";
		}
		if(gc.getInput().isKeyPressed(Input.KEY_6)) {
			click_sound.play();
			connection_adress+="6";
		}
		if(gc.getInput().isKeyPressed(Input.KEY_7)) {
			click_sound.play();
			connection_adress+="7";
		}
		if(gc.getInput().isKeyPressed(Input.KEY_8)) {
			click_sound.play();
			connection_adress+="8";
		}
		if(gc.getInput().isKeyPressed(Input.KEY_9)) {
			click_sound.play();
			connection_adress+="9";
		}
		if(gc.getInput().isKeyPressed(Input.KEY_PERIOD)) {
			click_sound.play();
			connection_adress+=".";
		}
		if(gc.getInput().isKeyPressed(Input.KEY_BACK)) {
			click_sound.play();
			connection_adress="";
		}
		
		

		if (gc.getInput().isKeyPressed(Input.KEY_S)) {
			click_sound.play();
			ClassesInstances.serverTCP = new ServerTCP();
			ClassesInstances.serverTCP.start();
			sbg.enterState(StatesCodes.SERVER_GAME);
			//serverTCP.main(null);
			// sbg.enterState(StatesCodes.CREDITS);
		}
		if (gc.getInput().isKeyPressed(Input.KEY_K)) {
			click_sound.play();
			ClassesInstances.clientTCP = new ClientTCP(connection_adress);
			ClassesInstances.clientTCP.start();
			sbg.enterState(StatesCodes.CLIENT_GAME);

		}
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			click_sound.play();
			menustate.play_menu_music();

			sbg.enterState(StatesCodes.MENU);
		}


	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return StatesCodes.SERVERCLIENT;
	}

}
