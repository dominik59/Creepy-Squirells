package Core;

import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import States.CreditsState;
import States.MenuState;
import States.ServerClientState;

public class Engine extends StateBasedGame{

	public Engine() {
		super("Creepy Squirells");
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args)
	{
		File f = new File("natives");
		if(f.exists())System.setProperty("org.lwjgl.librarypath", f.getAbsolutePath());
		try {
			AppGameContainer app=new AppGameContainer(new Engine());
			app.setDisplayMode(Window.height, Window.width, false);
			app.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		gc.setAlwaysRender(true);
		gc.setTargetFrameRate(60);
		gc.setVSync(true);
		new Resources();
		ClassesInstances.menuState=new MenuState();
		ClassesInstances.creditsState=new CreditsState();
		ClassesInstances.serverClientState=new ServerClientState();
		ClassesInstances.gameState=new States.GameState();
		
		this.addState(ClassesInstances.menuState);
		this.addState(ClassesInstances.creditsState);
		this.addState(ClassesInstances.serverClientState);
		this.addState(ClassesInstances.gameState);
	}
	
	

}
