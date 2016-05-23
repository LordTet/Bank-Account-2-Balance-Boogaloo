import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Battle extends BasicGameState
{
	GameContainer gc;
	StateBasedGame game;
	Image player;
	Image opponent;
	
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
	{
		gc = arg0;
		game = arg1;
		
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException
	{
		arg2.drawRect(50, 400, 700, 150);
		//TODO: Fight, Item, Flee buttons.
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException
	{
		
	}

	public int getID()
	{
		return 3;
	}
	
}
