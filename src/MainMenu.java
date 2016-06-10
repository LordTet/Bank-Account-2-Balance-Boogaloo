//State:Main Menu
//By Jake Holtham and Andrew Soque
//Due 6/10/16
//Mr. Segall | Data Structures | Period 1
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import java.io.File;
import org.newdawn.slick.Color;

//Constructor initializes the title image
public class MainMenu extends BasicGameState
{
	private StateBasedGame game;
	private GameContainer contain;
	Image title;
	public void init(GameContainer container, StateBasedGame game) throws SlickException 
    {
        this.game = game;
        contain = container;
        title = new Image("src/txtr/titleImage.png");
    }
	//Draws the title image and draws the controls for starting the game or exiting
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
    {
        g.setColor(Color.white);
        g.scale(2, 2);
        g.drawString("1. Play game",10 ,250);
        g.drawString("2. Exit",300,250);
        title.draw(0,0,(float).805);

    }
    //Did not need update, however it was a requirement for the State interface
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
        
	}
    //Returns the state id, in this case 0.
    public int getID()
	{
        return 0;
	}
    //Polls for input
    public void keyReleased(int key, char c)
    {
    	System.out.println(key);
    	switch(key)
    	{
    	case 2:
    		game.enterState(1, new EmptyTransition(), new EmptyTransition());
    		break;
    	case 3:
    		contain.exit();
    		
    	}
    }
}