package Core;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.ResourceLoader;
import org.newdawn.slick.Sound;


public class Resources {
	private static Map<String, Image> images;
	private static Map<String, SpriteSheet> spritesheets;
	private static Map<String, Music> music;
	private static Map<String, Sound> sound;
	private static Map<String, TiledMap> maps;
	
	public Resources()
	{
		images = new HashMap<String, Image>();
		maps=new HashMap<String,TiledMap>();
		spritesheets = new HashMap<String, SpriteSheet>();
		music= new HashMap<String,Music>();
		sound = new HashMap<String,Sound>();
		
		
		try {
			maps.put("level1", new TiledMap("/level1.tmx"));
			music.put("menuSound", new Music("/pink_panter.wav"));
			music.put("level1_music", new Music("/inception.wav"));
			music.put("level1_sound", new Music("/hehe.ogg"));
			music.put("click_sound", new Music("/click_sound.wav"));	
			sound.put("wood_step", new Sound("/wood_step.wav"));
			sound.put("grass_step", new Sound("/grass_step.wav"));
			images.put("button_play",loadImage("/button_graj.png"));
			images.put("menu_background",loadImage("/menu_background.png"));
			spritesheets.put("bullet_1", new SpriteSheet(loadImage("/bullet_1.png"), 32, 32 ));
			spritesheets.put("bullet_2", new SpriteSheet(loadImage("/bullet_2.png"), 32, 32 ));
			spritesheets.put("player_hp", new SpriteSheet(loadImage("/player_hp.png"), 32, 28 ));
			spritesheets.put("gray_hp", new SpriteSheet(loadImage("/gray_hp.png"), 32, 28 ));	
			spritesheets.put("sqi_r", new SpriteSheet(loadImage("/sqi1.png"), 32, 32 ));
			spritesheets.put("sqi_l", new SpriteSheet(loadImage("/sqi2.png"), 32, 32 ));
			spritesheets.put("sqi_f", new SpriteSheet(loadImage("/sqi3.png"), 32, 32 ));
			spritesheets.put("sqi_b", new SpriteSheet(loadImage("/sqi4.png"), 32, 32 ));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	private Image loadImage(String path) throws SlickException {
		// TODO Auto-generated method stub
		return new Image(path,false,Image.FILTER_NEAREST);
	}
	public static Image getImage(String type)
	{
		return images.get(type);
	}
	public static SpriteSheet getSpritesheet(String type)
	{
		return spritesheets.get(type);
	}
	public static Music getAudio(String name)
	{
		return music.get(name);
	}
	public static Sound getSound(String name)
	{
		return sound.get(name);
	}
	public static TiledMap getMap(String name)
	{
		return maps.get(name);
	}
	
}
