package States;




import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


import Core.Window;
import Core.ClassesInstances;
import Core.Resources;

public class MenuState extends BasicGameState{
	private UnicodeFont menuFont;
	private Music menuAudio=Resources.getAudio("menuSound");
	private Sound click_sound;
	private Long timer=new Long(0);
	private boolean is_menuAudio_playing=false;
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
		
		click_sound=new Sound("/click_sound.wav");
		menuAudio.play();
		menuAudio.setVolume(0.2f);
		is_menuAudio_playing=true;
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.setFont(menuFont);
		g.drawImage(Resources.getImage("menu_background"), 0, 0);
		g.drawImage(Resources.getImage("button_play"), 50, Window.height-500);
		g.drawString("1.Uruchomienie gry", 100, Window.height-465);
		
		g.drawImage(Resources.getImage("button_play"),50, Window.height-400);
		g.drawString("2.Wyświetlenie twórców", 100, Window.height-365);

		g.drawImage(Resources.getImage("button_play"), 50, Window.height-300);
		g.drawString("3.Wyjdź z gry", 100, Window.height-265);
		
		g.setColor(Color.white);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub
		if(gc.getInput().isKeyPressed(Input.KEY_1))
			{
				if(is_menuAudio_playing)
				{
					menuAudio.stop();
					click_sound.play();
					is_menuAudio_playing=false;
				}
				
				gamemusic = true;
				System.out.println("gamemusic status: " + gamemusic); //tak sobie debuguje
				
				sbg.enterState(StatesCodes.SERVERCLIENT);
				
			}
		if (gc.getInput().isKeyPressed(Input.KEY_2)) {
			click_sound.play();
			sbg.enterState(StatesCodes.CREDITS);
		}
		if (gc.getInput().isKeyPressed(Input.KEY_3)) {
			click_sound.play();
			gc.exit();
		}
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
		menuAudio.setVolume(0.2f);
		is_menuAudio_playing=true;
	}

}
