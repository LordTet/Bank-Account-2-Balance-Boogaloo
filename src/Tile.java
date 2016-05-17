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
	
	public Tile(String x, boolean w)
	{
		walkable = w;
		name = x;
		try
		{
			//System.out.println(x);
			sprite = new Image(x);
		}
		catch(Exception e)
		{
			System.out.println("Image not found!");
		}
	}
	

}
