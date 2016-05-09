import org.newdawn.slick.Image;
public class Tile
{
	Image sprite;
	int xsize = 50;
	int ysize = 50;
	
	//TODO: implement walkable, interactable, etc.
	
	public Tile(String x)
	{
		try
		{
			sprite = new Image(x);
		}
		catch(Exception e)
		{
			System.out.println("Image not found!");
		}
	}
}
