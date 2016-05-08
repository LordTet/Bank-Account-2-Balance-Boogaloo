import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Color;
public class MainMenu extends BasicGameState
{
	public void init(GameContainer container, StateBasedGame game) throws SlickException 
    {
        // TODO Auto-generated method stub
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
    {
        g.setColor(Color.red);
        g.drawString("Ayy lmao",50,10);

    }
 
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
        // TODO Auto-generated method stub 
	}
 
    public int getID()
	{
        return 0;
	}
}