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
	
	int[][] map;
	
	//temporary static player positions
	int playerx = 500;
	int playery = 500;
	
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
	{
		map = new int[20][20];
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
				System.out.println(current);
				map[row][col] = Integer.parseInt(current);
			}
		}
		
		x.close();
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException
	{
		
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
