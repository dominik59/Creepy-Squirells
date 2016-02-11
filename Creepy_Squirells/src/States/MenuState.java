package States;




import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


import Core.Window;
import Core.Resources;

public class MenuState extends BasicGameState{
	private UnicodeFont menuFont;
	private Audio menuAudio=Resources.getAudio("menuSound");
	
	public boolean gamemusic;
	
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		/**
		 * To wszystko trzeba dodać aby uruchomić polskie znaki
		 */
		menuFont = new UnicodeFont(new java.awt.Font("Arial", Font.BOLD, 20));
		menuFont.getEffects().add(new ColorEffect(java.awt.Color.white));
		menuFont.addGlyphs("ąćłóężźńś"); // szczególnie ważna jest ta linijka bo
											// to ona dodaje polskie znaki
		menuFont.addNeheGlyphs();
		menuFont.loadGlyphs();
		menuAudio.playAsMusic(1.0f, 1.0f, false);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.setFont(menuFont);
		g.drawImage(Resources.getImage("button_play"), Window.width/2-50, 50);
		g.drawString("1.Uruchomienie gry", Window.width/2, 85);
		g.drawImage(Resources.getImage("button_play"), Window.width/2-50, 150);

		g.drawString("2.Wyświetlenie twórców", Window.width/2-10, 185);

		g.setColor(Color.white);
		
		g.setBackground(Color.gray);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int alpha) throws SlickException {
		// TODO Auto-generated method stub
		if(gc.getInput().isKeyPressed(Input.KEY_1))
			{
				if(menuAudio.isPlaying())
				{
					menuAudio.stop();
				}
				
				sbg.enterState(StatesCodes.GAME);
				gamemusic = true;
			}
		if(gc.getInput().isKeyPressed(Input.KEY_2))sbg.enterState(StatesCodes.CREDITS);		
		//Resources.getImage("tiles").draw();
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return StatesCodes.MENU;
	}

}
