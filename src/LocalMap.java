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
	boolean moving = true;
	GameContainer gc;
	//temporary static player positions

	Player p1;
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
	{
		gc = arg0;
		p1 = new Player(0,9,18);
		
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
				tiles[row][col] = new Tile(path,true);
				if(Integer.parseInt(current) == 0)
				{
					tiles[row][col].walkable = false;
				}
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

	
	//FOR PLAYER: Render player, use arrow key to add 30 to player position, lock input until they move to tile.
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException
	{
		int xloc = 100;
		int yloc = 0;
		
		//tiles[0][1].sprite.draw(0,0);
		
		for(Tile[] x : tiles)
		{
			for(Tile y: x)
			{
				//System.out.println(y.name);
				y.sprite.draw(xloc,yloc);
				y.cornerX = xloc;
				y.cornerY = yloc;
				xloc+=y.xsize;
			}
			xloc=100;
			yloc+=x[0].ysize;
		}
		
		
		p1.draw(tiles[p1.x][p1.y]);
		
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException
	{
		
	}
	
	public void keyPressed(int key, char c)
	{
		if(key == 1)
		{
			gc.exit();
		}
		System.out.println(key);
		/*Up: 200
		 *Down:208
		 * Left:203
		 * Right:205
		 */
		//TODO: Make sprite move
		
		switch(key)
		{
		case 200:
			p1.sprite = p1.upSprite;
			System.out.println(tiles[p1.x-1][p1.y].walkable + "\n" + p1.x);
			if(tiles[p1.x-1][p1.y].walkable)
				p1.x--;
			break;
		case 208:
			p1.sprite = p1.downSprite;
			if(tiles[p1.x+1][p1.y].walkable)
				p1.x++;
			break;
		case 203:
			p1.sprite = p1.leftSprite;
			if(tiles[p1.x][p1.y-1].walkable)
				p1.y--;
			break;
		case 205:
			p1.sprite = p1.rightSprite;
			if(tiles[p1.x][p1.y+1].walkable)
				p1.y++;
			break;
		}
		
		
		
	}

	public int getID()
	{
		return 1;
	}
	
}
