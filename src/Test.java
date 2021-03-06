//Class: Test / Main
//By Christian Wettre, Jake Holtham, Andrew Soque
//Due 6/10/16
//Mr Segall | Data Structures | Period 1
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
public class Test extends StateBasedGame
{

    public static void main(String[] args)
    {
        try 
        {
        	//Set up the game container, and set the framerate / screen resolution
        	AppGameContainer container = new AppGameContainer(new Test());
        	container.setTargetFrameRate(60);
            container.setDisplayMode(800,600,false);
            container.setTargetFrameRate(60);
            container.start();
        } 
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
    public Test()
    {
    	//Amazing name
        super("#DankAccount");
    }
    //Initialize the states
    public void initStatesList(GameContainer container) throws SlickException
    {
    	Battle x = new Battle();
        addState(new MainMenu());
       	addState(x);
        addState(new LocalMap(x));
        addState(new DeathScreen());
    }
 
}