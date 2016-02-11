package States;



import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Core.Window;
public class MenuState extends BasicGameState{

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.drawString("1.Uruchomienie gry", Window.width/2, 50);
		g.drawString("2.Wyœwietlenie twórców", Window.width/2, 100);
		g.setColor(Color.red);
		
		g.setBackground(Color.cyan);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int alpha) throws SlickException {
		// TODO Auto-generated method stub
		if(gc.getInput().isKeyPressed(Input.KEY_1))sbg.enterState(StatesCodes.GAME);
		if(gc.getInput().isKeyPressed(Input.KEY_2))sbg.enterState(StatesCodes.CREDITS);		
		//Resources.getImage("tiles").draw();
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return StatesCodes.MENU;
	}

}
