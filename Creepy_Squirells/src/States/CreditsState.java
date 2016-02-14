package States;



import java.awt.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Core.ClassesInstances;
import Core.Window;

public class CreditsState extends BasicGameState{
	private UnicodeFont creditsFont;
	private Integer dominik_y = -100;
	private Integer krzychu_y = -200;
	private Integer grzesiek_y = -300;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		
		creditsFont = new UnicodeFont(new java.awt.Font("Times New Roman", Font.BOLD, 50));
		creditsFont.getEffects().add(new ColorEffect(java.awt.Color.white));
		creditsFont.addGlyphs("ąćłóężźńś"); // szczególnie ważna jest ta linijka bo
											// to ona dodaje polskie znaki
		creditsFont.addNeheGlyphs();
		creditsFont.loadGlyphs();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.setFont(creditsFont);
		g.drawString("Dominik Pawlik", Window.width / 2, dominik_y);
		g.drawString("Krzysztof Pęzioł", Window.width / 2, krzychu_y);
		g.drawString("Grzegorz Mąka", Window.width / 2, grzesiek_y);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int alpha) throws SlickException {
		// TODO Auto-generated method stub
		dominik_y++;
		krzychu_y++;
		grzesiek_y++;
		if (gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
			dominik_y = -100;
			krzychu_y = -200;
			grzesiek_y = -300;
			sbg.enterState(StatesCodes.MENU);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return StatesCodes.CREDITS;
	}

}
