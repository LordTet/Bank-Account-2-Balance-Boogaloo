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
	
	public LocalMap(Battle x)
	{
		super();
		battleState = x;
	}
	
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
	{
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

		crux = new TrueTypeFont(new Font("Coder's Crux", Font.PLAIN,24), false);
		//read the amount of files in folder of maps, create array of maps
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
	
	//Changes the map to the file number denoted by id

	public void changeMap(int id, int oldID)
	{
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
		
		if(!p1.moving)
		{
			p1.draw(currentMap.tiles[p1.x][p1.y]);
		}
		else
		{
			
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
				
			p1.between+=4;
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
		
		
		if(interacting && !intro)
		{
			Tile interacted = null;
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
				//TEST MORE
				/*
				arg2.drawRect(199, 4, (dialogue.length()*10)+2, 52);
				arg2.drawRect(200, 5, dialogue.length()*10, 50);
				arg2.setColor(Color.black);
				arg2.fillRect(201, 6, (dialogue.length()*10)-1, 49);
				arg2.setColor(Color.white);
				arg2.drawString(dialogue, 230, 10);
				*/
				drawTextBox(dialogue,arg2);
			}
			else if(dialogue.equals("null") && !intro)
			{
				interacting = !interacting;
			}
			
		}
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
	
	public void drawTextBox(String text, Graphics g)
	{
		g.drawRect(199, 4, (text.length()*10)+2, 52);
		g.drawRect(200, 5, text.length()*10, 50);
		g.setColor(Color.black);
		g.fillRect(201, 6, (text.length()*10)-1, 49);
		g.setColor(Color.white);
		g.drawString(text,203,23);
	}

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
	
	public void keyPressed(int key, char c)
	{
		if(key == 1)
		{
			gc.exit();
		}
		System.out.println(key);
		
		if(key == 44)
		{
			interacting = !interacting;
			intro = false;
		}
		
		if(!interacting && !p1.moving)
		{
		
			switch(key)
			{
			//up
			case 200:
				
				p1.direction = 0;

				p1.sprite = p1.movingUpSprite;
				
				if(currentMap.tiles[p1.x-1][p1.y].walkable)

				{
					int ch = generator.nextInt(100);
					p1.x--;
					p1.between = -30;
					p1.moving = true;
					if (ch < 2)
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
				
			//down
			case 208:
				
				p1.direction = 2;

				p1.sprite = p1.movingDownSprite;

				if(currentMap.tiles[p1.x+1][p1.y].walkable)

				{
					p1.x++;
					p1.between = -30;
					p1.moving = true;
					int ch = generator.nextInt(100);
					if (ch < 2)
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
					int ch = generator.nextInt(100);
					p1.moving = true;
					p1.between = -30;
					p1.y--;
					if (ch < 2)
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
					int ch = generator.nextInt(100);
					p1.y++;
					p1.between = -30;
					p1.moving = true;
					if (ch < 2)
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

	public int getID()
	{
		return 1;
	}
	
	
	public void showDialogue(String dia)
	{
		
	}
	
}
