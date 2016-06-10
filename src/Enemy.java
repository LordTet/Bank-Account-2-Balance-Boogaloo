import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import java.util.Random;

//Class: Enemy
//By Christian Wettre
//Due 6/10/16
//Mr Segall | Data Structures | Period 1
public class Enemy
{
    Image sprite;
    
    //enemy name and sprite location
    public String spritename;
    public String name;
    
    //enemy stats
    public int maxHP;
    public int HP;
    public int atk;
    public int def;
    public int spd;
    public int exp;
    
    private Random generator;
    
    //ultimately unused text in the enemy files
    public String [] flavorText;
    
    //constructor; reads enemy from a file
    public Enemy(String enemyFileName)
    {
       
        try
        {
            File enemyFile = new File(enemyFileName);
            Scanner sc = new Scanner(enemyFile);
            generator = new Random();
            spritename = sc.nextLine();
            try
            {
                sprite = new Image(spritename);
            }
            catch (SlickException e)
            {
                System.out.println("Enemy img not found!");
            }
            name = sc.nextLine();
            maxHP = sc.nextInt();
            atk = sc.nextInt();
            def = sc.nextInt();
            spd = sc.nextInt();
            exp = sc.nextInt();
            flavorText = new String[4];
            int i = 0;
            while(sc.hasNextLine())
            {
            	flavorText[i] = sc.nextLine();
            	i++;
            }
            HP = maxHP;
            sc.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Enemy file not found!");
        }
       
    }
   
    //attacks the player
    public int attack(PlayerBattle ply)
    {

    	if((atk - ply.def > 0))
    	{
    		int dam = (atk + generator.nextInt(3)) - ply.def;
    		ply.HP -= dam;
    		return dam;
    	}
    	else
    		return 0;
    }
}
