package Core;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Sound;


public class Resources {
	private static Map<String, Image> images;
	private static Map<String, SpriteSheet> spritesheets;
	private static Map<String, Sound> sounds;
	
	public Resources()
	{
		images = new HashMap<String, Image>();
		spritesheets = new HashMap<String, SpriteSheet>();
		sounds= new HashMap<String,Sound>();
		
		try {
			spritesheets.put("tiles", new SpriteSheet(loadImage("/Level_tiled_map.png"), 32, 32) );
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
	
}
