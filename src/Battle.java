import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
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
	Input inp;
	boolean isPlayerTurn;
	
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
	{
		gc = arg0;
		game = arg1;
		isPlayerTurn = true;
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException
	{
		arg2.drawRect(50, 400, 700, 150);
		//TODO: Fight, Item, Flee buttons.
		
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException
	{
		inp = gc.getInput();
		/*if the mouse is at the fight button and pressed, fight
		if (inp.getAbsoluteMouseX() == (fight location) && inp.isMousePressed(0) && isPlayerTurn)
		{
			//playerBattle.attack()
		}
		else if (mouse is at item button, is pressed, is player turn)
		{
			//playerBattle.useItem();
		}
		else if (mouse is at flee button, is pressed, is player turn)
		{
			//playerBattle.flee();
		}
		else if (is not player turn)
		{
			enemy.attack();
		}
		*/
		
	}

	public int getID()
	{
<<<<<<< HEAD
		return 2;
=======
		return 3;
>>>>>>> master
	}
	
}
