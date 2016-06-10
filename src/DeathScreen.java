//Class: DeathScreen
//By Jake Holtham
//Due 6/10/16
//Mr Segall | Data Structures | Period 1
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class DeathScreen extends BasicGameState
{
	Image x;
	GameContainer gc;
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException 
	{
		x = new Image("src/txtr/death.png");
		gc = arg0;
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException 
	{
		x.draw(0, 0);
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException 
	{
		
	}

	public int getID() 
	{
		return 4;
	}
    public void keyReleased(int key, char c)
    {
    	if(key == 28)
    	{
    		gc.exit();
    	}
    }

}
