import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
 
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
public class Enemy
{
    Image sprite;
    public String spritename;
    public String name;
    public int HP;
    public int atk;
    public int def;
    public int spd;
    public int exp;
    public String [] flavorText;
    public Enemy(String enemyFileName)
    {
       
        try
        {
            File enemyFile = new File(enemyFileName);
            Scanner sc = new Scanner(enemyFile);
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
            HP = sc.nextInt();
            atk = sc.nextInt();
            def = sc.nextInt();
            spd = sc.nextInt();
            exp = sc.nextInt();
            flavorText = new String[3];
            int i = 0;
            while(sc.hasNextLine())
            {
                flavorText[i] = sc.nextLine();
                i++;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Enemy file not found!");
        }
       
    }
   
    public int attack(PlayerBattle ply)
    {
        ply.HP -= (atk - ply.def);
        return (atk - ply.def);
    }
}