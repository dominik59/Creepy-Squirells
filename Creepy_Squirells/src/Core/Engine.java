package Core;
import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Engine extends BasicGame{

	public Engine() {
		super("Creepy Squirells");
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args)
	{
		File f =new File("natives");
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
	public void render(GameContainer gc, Graphics gr) throws SlickException {
		// TODO Auto-generated method stub
		gc.setTargetFrameRate(60);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer gc, int alpha) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	

}
