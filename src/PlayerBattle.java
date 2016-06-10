import org.newdawn.slick.Image; 
import java.util.Random;

//Class: PlayerBattle
//By Christian Wettre
//Due 6/10/16
//Mr Segall | Data Structures | Period 1
public class PlayerBattle
{
    Image spriteNormal;
    
    //player stats
    public int maxHP;
    public static int HP = 0;
    public int lv = 1;
    public int atk;
    public int def;
    public int spd;
    public int lck;
    public static int exp = 0;
    
    Random generator = new Random();
   
    //constructor; has starting stats which go up based on amount of exp
    public PlayerBattle()
    {
        maxHP = 15;
        atk = 3;
        def = 3;
        spd = 3;
        lck = 3;
        //for loop making stats go up
        int tempexp = exp;
        for (int i = exp; i > 10; i-= 10)
        {
            maxHP+= 2;
            atk++;
            def++;
            spd++;
            lck++;
            lv++;
            exp-=10;
        }
        exp = tempexp;
        if (exp == 0)
        {
        	HP = maxHP;
        }
    }
   
    //attacks an enemy
    public int attack(Enemy other)
    {
       
        int plus = generator.nextInt(lck);
        if (((atk + plus) - other.def) > 0)
        {
        	int damage = (atk + plus) - other.def;
            if (other.HP - damage < 0)
            {
            	damage = other.HP;
            	other.HP = 0;
            }
            else
            	other.HP -= damage;
            return damage;
        }
        else
        	return 0;
    }
   
    //used for the item button, heals 5 hp
    public int heal()
    {
       int diff = maxHP - HP;
       HP += 5;
       if (HP > maxHP)
       {
    	   HP = maxHP;
    	   return diff;
       }
       return 5;
    }   
    
    //used for the escape button, generates a number based on enemy and player speed and a random number. if the random number is
    //below the speed number, the player flees successfully, otherwise it wastes the turn
    public boolean flee(Enemy other)
    {
        int fleeChance = 60 - (other.spd * 10) + (spd * 10);
        int rand = generator.nextInt(100);
        if (rand < fleeChance)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

