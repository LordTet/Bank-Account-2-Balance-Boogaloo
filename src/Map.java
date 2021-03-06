//Object: Map
//By Jake Holtham
//Due 6/10/16
//Mr. Segall | Data Structures | Period 1
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class Map
{
	public Tile[][] tiles;
	private LocalMap wrap;
	public int mapID;
	
	//Coordinate Data, saves entry/exit point for the map.
	//ARRAY MEANING: 0 = x coord, 1 = y coord, 2 = map to load. Null means no teleport.
	public Set<int[]> doors;
	
	public Map(LocalMap g)
	{
		wrap = g;
		doors = new HashSet<int[]>();
	}
	
	//loads map sans enterance nodes
	public boolean loadMap(int mapNum)
	{
		
		mapID = mapNum;
		doors = new HashSet<int[]>();
		tiles = new Tile[20][20];
		File stage = new File("src/maps/" + mapNum + ".txt");
		Scanner x = null;
		try 
		{
			x = new Scanner(stage);
		} 
		catch (FileNotFoundException e)
		{
			return false;
		}
		
		//Get the entrance text
		wrap.introText = x.nextLine();
		wrap.intro = true;
		wrap.interacting = true;
		
		int row = 0;
		int col = 0;
		int maxCol = 0;
		//see how large the map file is
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
		System.out.println("MAP LOADING: " + mapNum);
		
		tiles = new Tile[row][maxCol];
		
		
		try
		{
			x.close();
			x = new Scanner(stage);
			x.nextLine();
		}
		catch(Exception e)
		{
			
		}
		
		
		row = 0;
		col = 0;
		
		String current = "k";
		//Actualyl construct the tile map
		while(!current.equals("j"))
		{
			current = x.next();
			if(current.equals("x"))
			{
				tiles[row][col] = null;
			}
			//Loads tile
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
				
				//Pulls tile interaction text
				while(!additive.equals("~"))
				{
					interact = interact + " " + additive;
					additive = tileScanner.next();
				}
				interact = interact.substring(1);
				tiles[row][col] = new Tile(spritePath, walk, interact);
				col++;
			}
			//Enters at the end of the tile area of the map file
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
		
		
		//Read the doors area of the map file
		int[] insert = new int[3];
		String g = x.next();
		int counter = 0;
		while(!g.equals("end"))
		{

			insert[counter] = Integer.parseInt(g);
			if(counter == 2)
			{
				doors.add(insert);
				counter = -1;
				insert = new int[3];
			}
			counter++;
			g = x.next();

		}
		
		x.close();
		return true;
	}
	
	
	//Loads the map, and places the player in the proper location as dictated by the map file
	public boolean loadMap(int mapNum, int oldMapNum, Player p1)
	{
		Scanner x = null;
		loadMap(mapNum);
		File stage = new File("src/maps/" + mapNum + ".txt");
		try
		{
			x = new Scanner(stage);
		}
		catch(FileNotFoundException e)
		{
			return false;
		}
		String current = x.next();
		while(!current.equals("end"))
		{
			current = x.next();
		}
		System.out.println(oldMapNum);
		while(x.hasNext())
		{
			int newX = Integer.parseInt(x.next());
			int newY = Integer.parseInt(x.next());
			int source = Integer.parseInt(x.next());
			System.out.println("Ayy:" +  source);
			if(oldMapNum == source)
			{
				System.out.println(newX);
				System.out.println(newY);
				p1.x = newX;
				p1.y = newY;
			}
		}
		
		
		
		return true;
		
	}
	
	
	
	
	
}
