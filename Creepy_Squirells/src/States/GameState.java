package States;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import Core.Resources;
import States.MenuState;

public class GameState extends BasicGameState{
	private Integer first_player_x;
	private Integer first_player_y;
	private String first_player_picture="sqi_r";
	
	private Sound sound;
	private Music music;
	
	private TiledMap mapa;
	
	MenuState menustate = new MenuState();
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		mapa = new TiledMap("/level1.tmx");
		
		sound = new Sound("/inception.wav");
		music = new Music("/hehe.ogg");
		
		first_player_x=11;
		first_player_y=18;
		
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub


		g.setBackground(Color.black);
		//g.drawImage(Resources.getSpritesheet("tiles").getSubImage(1, 1,800,600),0,0);
		
		mapa.render(0,0);
		g.drawImage(Resources.getSpritesheet(first_player_picture).getSubImage(1,1,32,32),first_player_x*32,first_player_y*32);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int alpha) throws SlickException {
		// TODO Auto-generated method stub
		
		int kolizje = mapa.getLayerIndex("Kolizje");
		mapa.getTileId(0, 0, kolizje);
		
		
		if(menustate.gamemusic)
		{
			music.play();
			music.setVolume(0.2f);
			menustate.gamemusic = false;
			
		}
		
		if(gc.getInput().isKeyPressed(Input.KEY_ENTER))sbg.enterState(StatesCodes.MENU);
		
		if(gc.getInput().isKeyPressed(Input.KEY_RIGHT))
		{
			first_player_picture="sqi_r";
			
			if(mapa.getTileId(first_player_x+1, first_player_y, kolizje)==0)
			{
				first_player_x++;
			}
				
		}
		
		
		if(gc.getInput().isKeyPressed(Input.KEY_LEFT))
		{	
			first_player_picture="sqi_l";
			
			if(mapa.getTileId(first_player_x-1, first_player_y, kolizje)==0)
			{
				first_player_x--;
			}
				
		}
			
		if(gc.getInput().isKeyPressed(Input.KEY_UP))
		{
			if(mapa.getTileId(first_player_x, first_player_y-1, kolizje)==0)
			{
				first_player_y--;
			}
				
		}
		if(gc.getInput().isKeyPressed(Input.KEY_DOWN))
		{
			if(mapa.getTileId(first_player_x, first_player_y+1, kolizje)==0)
			{
				first_player_y++;
			}
				
		}
		
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return StatesCodes.GAME;
	}

}
