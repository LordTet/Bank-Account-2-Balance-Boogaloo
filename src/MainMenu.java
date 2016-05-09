import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.Color;
public class MainMenu extends BasicGameState
{
	private StateBasedGame game;
	private GameContainer container;
	public void init(GameContainer container, StateBasedGame game) throws SlickException 
    {
        this.game = game;
        this.container = container;
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
    {
        g.setColor(Color.red);
        g.drawString("A rediculously bad title screen for Proof of Concept Purposes!",100,50);
        g.setColor(Color.white);
        g.drawString("1. Play game",100,150);
        g.drawString("2. Exit",100,200);

    }
 
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
        
	}
 
    public int getID()
	{
        return 0;
	}
    
    public void keyReleased(int key, char c)
    {
    	System.out.println(key);
    	switch(key)
    	{
    	case 2:
    		game.enterState(1, new EmptyTransition(), new EmptyTransition());
    		break;
    	case 3:
    		container.exit();
    	}
    }
}