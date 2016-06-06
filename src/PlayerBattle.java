package src;

import org.newdawn.slick.Image; 
import java.util.Random;
 
public class PlayerBattle
{
    Image spriteNormal;
    public int HP;
    public static int lv = 1;
    public int atk;
    public int def;
    public int spd;
    public int lck;
    public static int exp = 0;
    Random generator = new Random();
   
    public PlayerBattle()
    {
        HP = 10;
        atk = 3;
        def = 3;
        spd = 3;
        lck = 3;
        
        for (int i = exp; i > 10; i-= 10)
        {
            HP+= 2;
            atk++;
            def++;
            spd++;
            lck++;
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
   
    /*public void useItem(Item it)
    {
       
    }*/
   
    public boolean flee(Enemy other)
    {
        int fleeChance = 60 - (other.spd * 10) + (spd * 10);
        double fl = (double)fleeChance / 100;
        if (generator.nextDouble() < fl)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
   
   
}
