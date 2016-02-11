package States;




import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


import Core.Window;
import Core.Resources;

public class MenuState extends BasicGameState{
	private TrueTypeFont menuFont;
	private Audio menuAudio;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
	     menuFont = new TrueTypeFont(awtFont, false);
	     Audio menuAudio=Resources.getAudio("menuSound");
	     menuAudio.playAsMusic(1.0f, 1.0f, true);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.setFont(menuFont);
		g.drawImage(Resources.getImage("button_play"), Window.width/2-50, 50);
		g.drawString("1.Uruchomienie gry", Window.width/2, 85);
		g.drawImage(Resources.getImage("button_play"), Window.width/2-50, 150);
		g.drawString("2.Wyœwietlenie twórców", Window.width/2-10, 185);
		g.setColor(Color.white);
		
		g.setBackground(Color.gray);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int alpha) throws SlickException {
		// TODO Auto-generated method stub
		if(gc.getInput().isKeyPressed(Input.KEY_1))
			{
				//menuAudio.stop();
				sbg.enterState(StatesCodes.GAME);
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
