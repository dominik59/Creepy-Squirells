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

import Core.ClassesInstances;
import Core.Resources;
import Core.Window;

public class ServerClientState extends BasicGameState {
	private UnicodeFont menuFont;
	private boolean menu_active;
	private Sound click_sound;
	private MenuState menustate = ClassesInstances.menuState;

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
		g.drawString("1.Graj jako SERWER", Window.width / 2, 85);

		g.drawImage(Resources.getImage("button_play"), Window.width / 2 - 50, 150);
		g.drawString("2.Graj jako KLIENT", Window.width / 2 - 10, 185);

		g.drawImage(Resources.getImage("button_play"), Window.width / 2 - 50, 250);
		g.drawString("3.Wstecz", Window.width / 2 - 10, 285);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int alpha) throws SlickException {
		// TODO Auto-generated method stub
		if (menu_active) {
			if (gc.getInput().isKeyPressed(Input.KEY_1)) {
				click_sound.play();
				// sbg.enterState(StatesCodes.CREDITS);
			}
			if (gc.getInput().isKeyPressed(Input.KEY_2)) {
				click_sound.play();
				sbg.enterState(StatesCodes.GAME);
				// gc.exit();
			}
			if (gc.getInput().isKeyPressed(Input.KEY_3)) {
				click_sound.play();
				menustate.play_menu_music();
				
				sbg.enterState(StatesCodes.MENU);
			}
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}

}
