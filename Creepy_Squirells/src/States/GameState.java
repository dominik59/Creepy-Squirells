package States;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Core.Resources;

public class GameState extends BasicGameState{

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.drawString("Siema eniu jestes w grze, wiem, nie tak to sobie wyobrazales ;), \nnacisnij enter by wyjsc", 50, 50);
		g.setColor(Color.red);
		/**
		 * funkcja pozwalaj�ca wy�iwtla� fragment obrazka
		 */
		g.setBackground(Color.black);
		g.drawImage(Resources.getSpritesheet("tiles").getSubImage(1, 1,800,600),0,0);
		g.drawImage(Resources.getSpritesheet("sqi").getSubImage(1,1,32,32),320,358);
		
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int alpha) throws SlickException {
		// TODO Auto-generated method stub
		if(gc.getInput().isKeyPressed(Input.KEY_ENTER))sbg.enterState(StatesCodes.MENU);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return StatesCodes.GAME;
	}

}
