import org.newdawn.slick.Image;
public class Tile
{
	
	//Everything about the tile is public for local map access
	public Image sprite;
	public int xsize = 30;
	public int ysize = 30;
	public String name;
	public int cornerY = 0;
	public int cornerX = 0;
	double chn;
	public String interact = null;
	public boolean interactable = false;
	public boolean walkable;
	//Constructor passes in all the info, handled in the LocalMap class
	public Tile(String x, boolean w, String y)
	{
		walkable = w;
		name = x;
		interact = y;

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
	//toString returns the name of the tile
	public String toString()
	{
		return name;
	}
	

}
