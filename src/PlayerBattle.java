package src;

import org.newdawn.slick.Image; 
import java.util.Random;
 
public class PlayerBattle
{
    Image spriteNormal;
    public int maxHP;
    public static int HP = 0;
    public static int lv = 1;
    public int atk;
    public int def;
    public int spd;
    public int lck;
    public static int exp = 0;
    Random generator = new Random();
   
    public PlayerBattle()
    {
        maxHP = 10;
        atk = 3;
        def = 3;
        spd = 3;
        lck = 3;
        
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
        if (exp == 0)
        {
        	HP = maxHP;
        }
    }
   
    public int attack(Enemy other)
    {
       
        int plus = generator.nextInt(2);
        if (((atk + plus) - other.def) > 0)
        {
        	int damage = (atk + plus) - other.def;
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
