import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class LocalMap extends BasicGameState
{
	
	Tile[][] tiles;
	
	//temporary static player positions
	int playerx = 500;
	int playery = 500;
	
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
	{
		tiles = new Tile[20][20];
		File stage = new File("src/maps/0.txt");
		Scanner x = null;
		try 
		{
			x = new Scanner(stage);
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		int row = 0;
		int col = 0;
		
		
		while(x.hasNext())
		{
			String current = x.next();
			if(!current.equals("k"))
			{
				//System.out.println(current);
				String path = "src/txtr/";
				path += Integer.parseInt(current) + ".png";
				tiles[row][col] = new Tile(path);
				col++;
			}
			else
			{
				col = 0;
				row++;
			}
		}
		
		x.close();
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException
	{
		int xloc = 100;
		int yloc = 0;
		
		//tiles[0][1].sprite.draw(0,0);
		
		for(Tile[] x : tiles)
		{
			for(Tile y: x)
			{
				System.out.println(y.name);
				y.sprite.draw(xloc,yloc);
				xloc+=y.xsize;
			}
			xloc=100;
			yloc+=x[0].ysize;
		}
		
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException
	{
		
	}
	
	public void keyReleased(int key, char c)
	{
		
	}

	public int getID()
	{
		return 1;
	}
	
}
