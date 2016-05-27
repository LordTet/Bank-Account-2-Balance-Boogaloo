import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map
{
	public Tile[][] tiles;
	private LocalMap wrap;
	
	//Coordinate Data, saves entry/exit point for the map.
	//ARRAY MEANING: 0 = x coord, 1 = y coord, 2 = map to load
	public int[] top = new int[3];
	public int[] bottom = new int[3];
	public int[] left = new int[3];
	public int[] right = new int[3];
	
	
	
	
	
	public Map(LocalMap g)
	{
		wrap = g;
	}
	
	
	public void loadMap(int mapnum)
	{
		tiles = new Tile[20][20];
		File stage = new File("src/maps/" + mapnum + ".txt");
		Scanner x = null;
		try 
		{
			x = new Scanner(stage);
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		wrap.introText = x.nextLine();
		wrap.intro = true;
		wrap.interacting = true;
		
		int row = 0;
		int col = 0;
		int maxCol = 0;
		while(x.hasNext())
		{
			col++;
			if(x.next().equals("k"))
			{
				row++;
				col--;
				if(col > maxCol)
				{
					maxCol = col;
				}
				col = 0;
			}
		}
		System.out.println("row: " + row + " col: " + maxCol);
		
		tiles = new Tile[row][maxCol];
		
		
		try
		{
			x.close();
			x = new Scanner(stage);
			x.nextLine();
		}
		catch(Exception e)
		{
			//System.out.println(e);
		}
		
		
		row = 0;
		col = 0;
		
		String current = "k";
		while(!current.equals("j"))
		{
			current = x.next();
			
			//TODO: make map generation draw nothing on null.
			if(current.equals("x"))
			{
				tiles[row][col] = null;
			}
			else if(!current.equals("k") && !current.equals("j"))
			{

				String path = "src/txtr/";
				path += Integer.parseInt(current) + ".png";

				
				
				Scanner tileScanner = null;
				try
				{
					tileScanner = new Scanner(new File("src/data/tile" + current + ".txt"));
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				String spritePath = tileScanner.next();
				Boolean walk = tileScanner.nextBoolean();
				String interact = "";
				String additive = tileScanner.next();
				while(!additive.equals("~"))
				{
					interact = interact + " " + additive;
					additive = tileScanner.next();
				}
				interact = interact.substring(1);
				tiles[row][col] = new Tile(spritePath, walk, interact);
				col++;
			}
			else if(current.equals("j"))
			{
				row--;
			}
			else
			{
				col = 0;
				row++;
			}
		}
		
		x.close();
	}
	
	
	
	
	
	
}
