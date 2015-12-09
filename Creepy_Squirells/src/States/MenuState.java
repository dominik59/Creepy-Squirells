package States;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Core.Resources;

public class MenuState extends BasicGameState{

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.drawString("Siema, eniu,naciœnij enter by przejsc do okna opisujacego tworcow", 50, 50);
		g.drawString("Siema, eniu,naciscij spacje by wejsc do gry", 50, 100);
		g.setColor(Color.red);
		
		g.setBackground(Color.cyan);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int alpha) throws SlickException {
		// TODO Auto-generated method stub
		if(gc.getInput().isKeyPressed(Input.KEY_ENTER))sbg.enterState(StatesCodes.CREDITS);
		if(gc.getInput().isKeyPressed(Input.KEY_SPACE))sbg.enterState(StatesCodes.GAME);
		Resources.getImage("tiles").draw();
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return StatesCodes.MENU;
	}

}
