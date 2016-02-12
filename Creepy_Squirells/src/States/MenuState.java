package States;




import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
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
	private Music menuAudio=Resources.getAudio("menuSound");
	private boolean menuAudio_playing=false;
	public static boolean gamemusic;
	
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
		

		menuAudio.play();
		menuAudio.setVolume(0.2f);
		menuAudio_playing=true;
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.setFont(menuFont);
		g.drawImage(Resources.getImage("button_play"), Window.width/2-50, 50);
		g.drawString("1.Uruchomienie gry", Window.width/2, 85);
		
		g.drawImage(Resources.getImage("button_play"), Window.width/2-50, 150);
		g.drawString("2.Wyświetlenie twórców", Window.width/2-10, 185);

		g.drawImage(Resources.getImage("button_play"), Window.width/2-50, 250);
		g.drawString("3.Wyjdź z gry", Window.width/2-10, 285);
		
		g.setColor(Color.white);
		g.setBackground(Color.gray);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int alpha) throws SlickException {
		// TODO Auto-generated method stub
		if(gc.getInput().isKeyPressed(Input.KEY_1))
			{
				if(menuAudio_playing)
				{
					menuAudio.stop();
					menuAudio_playing=false;
				}
				
				gamemusic = true;
				System.out.println("gamemusic status: " + gamemusic); //tak sobie debuguje
				
				sbg.enterState(StatesCodes.GAME);
				
			}
		if(gc.getInput().isKeyPressed(Input.KEY_2))sbg.enterState(StatesCodes.CREDITS);
		if(gc.getInput().isKeyPressed(Input.KEY_3))gc.exit();
		//Resources.getImage("tiles").draw();
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return StatesCodes.MENU;
	}

	public void play_menu_music() {
		// TODO Auto-generated method stub
		menuAudio.play();
		menuAudio_playing=true;
	}

}
