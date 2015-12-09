package Core;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
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
		
	}
	
}
