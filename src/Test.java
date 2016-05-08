import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
 
public class Test extends StateBasedGame
{

    public static void main(String[] args)
    {
        System.out.println("lmao");
    }
 
    public Test(String name)
    {
        super(name);
    }
 
    public void initStatesList(GameContainer container) throws SlickException
    {
        addState(new MainMenu());
 
    }
 
}