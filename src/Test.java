package src;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import java.util.Scanner;
public class Test extends StateBasedGame
{

    public static void main(String[] args)
    {
    	System.out.println("lmao");
        try 
        {
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
        super("Test");
    }
 
    public void initStatesList(GameContainer container) throws SlickException
    {
    	Battle x = new Battle();
        addState(new MainMenu());
       	addState(x);
        addState(new LocalMap(x));
    }
 
}