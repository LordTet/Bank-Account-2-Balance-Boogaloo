import org.newdawn.slick.Image; 
import java.util.Random;
 
public class PlayerBattle
{
    Image spriteNormal;
    public int maxHP;
    public static int HP = 0;
    public int lv = 1;
    public int atk;
    public int def;
    public int spd;
    public int lck;
    public static int exp = 0;
    Random generator = new Random();
   
    public PlayerBattle()
    {
        maxHP = 15;
        atk = 3;
        def = 3;
        spd = 3;
        lck = 3;
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

