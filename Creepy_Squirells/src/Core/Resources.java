package Core;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;
import org.newdawn.slick.Sound;


public class Resources {
	private static Map<String, Image> images;
	private static Map<String, SpriteSheet> spritesheets;
	private static Map<String, Audio> sounds;
	
	public Resources()
	{
		images = new HashMap<String, Image>();
		spritesheets = new HashMap<String, SpriteSheet>();
		sounds= new HashMap<String,Audio>();
		
		try {
			sounds.put("menuSound", AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("/pink_panter.wav")));
			images.put("button_play",loadImage("/button_graj.png"));
			spritesheets.put("sqi_r", new SpriteSheet(loadImage("/sqi1.png"), 32, 32 ));
			spritesheets.put("sqi_l", new SpriteSheet(loadImage("/sqi2.png"), 32, 32 ));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
	public static Audio getAudio(String name)
	{
		return sounds.get(name);
	}
	
}
