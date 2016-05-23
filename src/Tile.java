import org.newdawn.slick.Image;
public class Tile
{
	public Image sprite;
	public int xsize = 30;
	public int ysize = 30;
	public String name;
	public int cornerY = 0;
	public int cornerX = 0;
	public boolean walkable;
	public String interact = null;
	public boolean interactable = false;
	
	public boolean[] properties;
	
	public Tile(String x, boolean w)
	{
		properties = new boolean[5];
		walkable = w;
		properties[0] = walkable;
		
		name = x;
		try
		{
			//System.out.println(x);
			sprite = new Image(x);
		}
		catch(Exception e)
		{
			System.out.println("Image " + name + " not found!");
		}
	}
	

}
