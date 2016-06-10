//State:Local Map
//By Jake Holtham and Andrew Soque
//Due 6/10/16
//Mr. Segall | Data Structures | Period 1
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.util.FontUtils;
import org.newdawn.slick.Image;
import java.util.Scanner;
import java.awt.Font;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
public class LocalMap extends BasicGameState
{

	private ArrayList<Map> maps;
	private Random generator;
	private TrueTypeFont crux;
	private PrintWriter wr;
	private Map currentMap;
	private GameContainer gc;
	public boolean interacting = true;
	public String introText = null;
	public boolean intro = true;
	public boolean battleChange = false;
	Battle battleState;
	StateBasedGame game;
	Player p1;
	
	//Custom constructor for retrieving the battle state
	//Calls superclass constructor, and saves the battle state
	public LocalMap(Battle x)
	{
		super();
		battleState = x;
	}
	
	
	//Initialize required variables, and read in every map in src/maps/ numerically
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
	{
		battleState.give(this);
		gc = arg0;
		game = arg1;
		p1 = new Player(2,10,10);
		try 
		{
			wr = new PrintWriter("battle_enemy.txt");
		} 
		catch (FileNotFoundException e1) 
		{
			
		}
		//The font used for the game
		crux = new TrueTypeFont(new Font("Coder's Crux", Font.PLAIN,24), false);
		
		//Read the amount of files in folder of maps, create an array list of maps
		generator = new Random();
		maps = new ArrayList<Map>();
		try
		{	
			int g = 0;
			while(true)
			{
				System.out.println("loading " + g);
				Map x = new Map(this);
				
				if(!x.loadMap(g))
				{
					break;
				}
				
				maps.add(x);
				
				g++;
			}
		}
		catch(Exception e)
		{
			System.out.println("OH NO");
		}
		changeMap(0, -1);

		if(introText.equals(""))
		{
			intro = false;
		}
		
	}
	
	//Changes the map to the file number denoted by id. also passes in oldID to tell the loadMap method the current map. 
	public void changeMap(int id, int oldID)
	{
		//if a map wasnt loaded yet, load the first one. Else load the next map as told by the map file.
		if(oldID == -1)
		{
			currentMap = maps.get(id);
			currentMap.loadMap(id);
		}
		else
		{
			currentMap = maps.get(id);
			currentMap.loadMap(id, oldID,p1);
		}
	}

	
	//Renders the graphics and does basic calculations on where the player is standing, interactions, etc.
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException
	{
		
		arg2.setFont(crux);
		
		int xloc = 100;
		int yloc = 0;
		
		for(Tile[] x : currentMap.tiles)
		{
			for(Tile y: x)
			{
				y.sprite.draw(xloc,yloc);
				y.cornerX = xloc;
				y.cornerY = yloc;
				xloc+=y.xsize;
			}
			xloc=100;
			yloc+=x[0].ysize;
		}
		
		//Player in the middle of movement switch
		if(!p1.moving)
		{
			//If the player is not moving, draw the player on the tile they are standing on.
			p1.draw(currentMap.tiles[p1.x][p1.y]);
		}
		else
		{
			//Otherwise, draw them in between the tiles as denoted by a number that tells the pixel offset.
			//The subtracted offset is based on direction.
			if(p1.direction == 3)
			{
				p1.sprite.draw(currentMap.tiles[p1.x][p1.y].cornerX-(float)p1.between , currentMap.tiles[p1.x][p1.y].cornerY);
			}
			else if(p1.direction == 1)
			{
				p1.sprite.draw(currentMap.tiles[p1.x][p1.y].cornerX+(float)p1.between , currentMap.tiles[p1.x][p1.y].cornerY);
			}
			else if(p1.direction == 2)
			{
				p1.sprite.draw(currentMap.tiles[p1.x][p1.y].cornerX, currentMap.tiles[p1.x][p1.y].cornerY+(float)p1.between);
			}
			else if(p1.direction == 0)
			{
				p1.sprite.draw(currentMap.tiles[p1.x][p1.y].cornerX, currentMap.tiles[p1.x][p1.y].cornerY-(float)p1.between);
			}
				
			p1.between+=2;
			//Once the offset has reached the tile, stop moving and draw the proper standing sprite.
			if(p1.between >= 0.0)
			{
				p1.between = -30;
				p1.moving = false;
				
				switch(p1.direction)
				{
				case 0:
					p1.sprite = p1.upSprite;
					break;
				case 1:
					p1.sprite = p1.rightSprite;
					break;
				case 2:
					p1.sprite = p1.downSprite;
					break;
				case 3:
					p1.sprite = p1.leftSprite;
					break;
				}

				
			}

		}
		
		//Handling interaction with tile
		if(interacting && !intro)
		{
			Tile interacted = null;
			//Deciding which tile to interact with based on the direction the player is facing.
			switch(p1.direction)
			{
				case 0:
					interacted = currentMap.tiles[p1.x-1][p1.y];
					break;
				case 1:
					interacted = currentMap.tiles[p1.x][p1.y+1];
					break;
				case 2:
					interacted = currentMap.tiles[p1.x+1][p1.y];
					break;
				case 3:
					interacted = currentMap.tiles[p1.x][p1.y-1];
					break;
			}
			String dialogue = interacted.interact;

			if(!dialogue.equals("null"))
			{
				drawTextBox(dialogue,arg2);
			}
			//handling for tiles without interaction text. The tile file will say "null"
			else if(dialogue.equals("null") && !intro)
			{
				interacting = !interacting;
			}
			
		}
		//The opening dialogue when you enter a map. Null for no text, anything else for opening text.
		else if(intro)
		{

			if(introText.equals("null"))
			{
				intro = false;
			}
			else
			{
				drawTextBox(introText, arg2);	
			}
		}
		
	}
	
	//Wrapper method for drawstring, draws the entire text box with string "Text"
	public void drawTextBox(String text, Graphics g)
	{
		g.drawRect(199, 4, (text.length()*10)+2, 52);
		g.drawRect(200, 5, text.length()*10, 50);
		g.setColor(Color.black);
		g.fillRect(201, 6, (text.length()*10)-1, 49);
		g.setColor(Color.white);
		g.drawString(text,203,23);
	}

	//Some basic game logic, checks if the current tile is a "door" tile, and checks when to change into the battle state.
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException
	{
		for(int[] x : currentMap.doors)
		{
			if(p1.x == x[0] && p1.y == x[1])
			{
				changeMap(x[2],currentMap.mapID);
			}
		}
		
		if (battleChange)
		{
			battleState.init(arg0, arg1);
			battleChange = false;
			arg1.enterState(2);
		}
	}
	
	//carries out functions based on key pressed
	public void keyPressed(int key, char c)
	{
		//If ESC, exit game.
		if(key == 1)
		{
			gc.exit();
		}
		System.out.println(key);
		
		//If Z, initiate or cancel an interaction with the tile in front of you.
		if(key == 44)
		{
			interacting = !interacting;
			intro = false;
		}
		//When the player isnt interacting with anything or already moving, handle movement.
		if(!interacting && !p1.moving)
		{
		
			switch(key)
			{
			
			//DOWN, LEFT, AND RIGHT DO THE SAME THING AS UP, BUT IN THE OTHER CARDINAL DIRECTIONS. ONLY UP IS EXPLAINED.
			
			//up
			case 200:
				
				//Set direction and sprite to the moving version
				p1.direction = 0;
				p1.sprite = p1.movingUpSprite;
				
				//only move if you can walk on the next tile
				if(currentMap.tiles[p1.x-1][p1.y].walkable)
				{
					//Handle boss tile initiating battle, change the next fight to the boss and initiate the fight boolean.
					if (currentMap.tiles[p1.x-1][p1.y].name.contains("6.png"))
					{
						File f = new File("src/data/battle_enemy.txt");
						File e = new File("src/data/boss.txt");
						try 
						{
							PrintWriter pw = new PrintWriter(f);
							Scanner sc = new Scanner(e);
							while(sc.hasNextLine())
							{
								pw.println(sc.nextLine());
							}
							pw.close();
							sc.close();
							try
							{
								battleChange = true;
							}
							catch(RuntimeException exc)
							{
								exc.printStackTrace();
							}
						} 
						catch (FileNotFoundException e1) 
						{
							System.out.println("Damn.");
						}
					}
					//Generate a number to match a 5% chance to initiate battle when moving tiles.
					int ch = generator.nextInt(100);
					p1.x--;
					p1.between = -30;
					p1.moving = true;
					if (ch < 5)
					{
						
						//Write enemy data to file in order for the battle state to access it.
						File f = new File("src/data/battle_enemy.txt");
						int enemyfile = generator.nextInt(2) + 1;
						File e = new File("src/data/enemy" + enemyfile + ".txt");
						try 
						{
							PrintWriter pw = new PrintWriter(f);
							Scanner sc = new Scanner(e);
							while(sc.hasNextLine())
							{
								pw.println(sc.nextLine());
							}
							pw.close();
							sc.close();
							try
							{
								battleChange = true;
							}
							catch(RuntimeException exc)
							{
								exc.printStackTrace();
							}
						} 
						catch (FileNotFoundException e1) 
						{
							System.out.println("Damn.");
						}
					}
				} 

				break;
				
			//down
			case 208:
				
				p1.direction = 2;

				p1.sprite = p1.movingDownSprite;

				if(currentMap.tiles[p1.x+1][p1.y].walkable)

				{
					if (currentMap.tiles[p1.x+1][p1.y].name.contains("6.png"))
					{
						File f = new File("src/data/battle_enemy.txt");
						File e = new File("src/data/boss.txt");
						try 
						{
							PrintWriter pw = new PrintWriter(f);
							Scanner sc = new Scanner(e);
							while(sc.hasNextLine())
							{
								pw.println(sc.nextLine());
							}
							pw.close();
							sc.close();
							try
							{
								battleChange = true;
							}
							catch(RuntimeException exc)
							{
								exc.printStackTrace();
							}
						} 
						catch (FileNotFoundException e1) 
						{
							System.out.println("Damn.");
						}
					}
					p1.x++;
					p1.between = -30;
					p1.moving = true;
					int ch = generator.nextInt(100);
					if (ch < 5)
					{
						File f = new File("src/data/battle_enemy.txt");
						int enemyfile = generator.nextInt(2) + 1;
						File e = new File("src/data/enemy" + enemyfile + ".txt");
						try 
						{
							PrintWriter pw = new PrintWriter(f);
							Scanner sc = new Scanner(e);
							while(sc.hasNextLine())
							{
								pw.println(sc.nextLine());
							}
							pw.close();
							sc.close();
							battleChange = true;
							
						} 
						catch (FileNotFoundException e1) 
						{
							System.out.println("Damn.");
						}
					}
				}
				break;
				
			//left
			case 203:
				
				p1.direction = 3;

				p1.sprite = p1.movingLeftSprite;

				if(currentMap.tiles[p1.x][p1.y-1].walkable)
				{
					if (currentMap.tiles[p1.x][p1.y-1].name.contains("6.png"))
					{
						File f = new File("src/data/battle_enemy.txt");
						File e = new File("src/data/boss.txt");
						try 
						{
							PrintWriter pw = new PrintWriter(f);
							Scanner sc = new Scanner(e);
							while(sc.hasNextLine())
							{
								pw.println(sc.nextLine());
							}
							pw.close();
							sc.close();
							try
							{
								battleChange = true;
							}
							catch(RuntimeException exc)
							{
								exc.printStackTrace();
							}
						} 
						catch (FileNotFoundException e1) 
						{
							System.out.println("Damn.");
						}
					}
					int ch = generator.nextInt(100);
					p1.moving = true;
					p1.between = -30;
					p1.y--;
					if (ch < 5)
					{
						File f = new File("src/data/battle_enemy.txt");
						int enemyfile = generator.nextInt(2) + 1;
						File e = new File("src/data/enemy" + enemyfile + ".txt");
						try 
						{
							PrintWriter pw = new PrintWriter(f);
							Scanner sc = new Scanner(e);
							while(sc.hasNextLine())
							{
								pw.println(sc.nextLine());
							}
							pw.close();
							sc.close();
							try
							{
								battleChange = true;
							}
							catch(RuntimeException exc)
							{
								exc.printStackTrace();
							}
						} 
						catch (FileNotFoundException e1) 
						{
							System.out.println("Damn.");
						}
					}
				}
				break;
				
			//right
			case 205:
				
				p1.direction = 1;


				p1.sprite = p1.movingRightSprite;

				if(currentMap.tiles[p1.x][p1.y+1].walkable)
				{
					if (currentMap.tiles[p1.x][p1.y+1].name.contains("6.png"))
					{
						File f = new File("src/data/battle_enemy.txt");
						File e = new File("src/data/boss.txt");
						try 
						{
							PrintWriter pw = new PrintWriter(f);
							Scanner sc = new Scanner(e);
							while(sc.hasNextLine())
							{
								pw.println(sc.nextLine());
							}
							pw.close();
							sc.close();
							try
							{
								battleChange = true;
							}
							catch(RuntimeException exc)
							{
								exc.printStackTrace();
							}
						} 
						catch (FileNotFoundException e1) 
						{
							System.out.println("Damn.");
						}
					}
					int ch = generator.nextInt(100);
					p1.y++;
					p1.between = -30;
					p1.moving = true;
					if (ch < 5)
					{
						File f = new File("src/data/battle_enemy.txt");
						int enemyfile = generator.nextInt(2) + 1;
						File e = new File("src/data/enemy" + enemyfile + ".txt");
						try 
						{
							PrintWriter pw = new PrintWriter(f);
							Scanner sc = new Scanner(e);
							while(sc.hasNextLine())
							{
								pw.println(sc.nextLine());
							}
							pw.close();
							sc.close();
							try
							{
								battleChange = true;
							}
							catch(RuntimeException exc)
							{
								exc.printStackTrace();
							}
						} 
						catch (FileNotFoundException e1) 
						{
							System.out.println("Damn.");
						}
						
					}
				}
				break;
				
				
			}
		}
		
		
	}

	//Get the ID of the state. ID = 1.
	public int getID()
	{
		return 1;
	}
	
	
}
