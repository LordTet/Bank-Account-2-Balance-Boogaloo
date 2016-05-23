import org.newdawn.slick.Image;


public class PlayerBattle 
{
	Image spriteNormal;
	public int HP;
	public int lv;
	public int atk;
	public int def;
	public int spd;
	public int lck;
	
	public PlayerBattle(int l)
	{
		lv = l;
		HP = 10;
		atk = 4;
		def = 4;
		spd = 4;
		lck = 2;
		for (int i = lv; i > 1; i--)
		{
			HP+= 2;
			atk++;
			def++;
			spd++;
			lck++;
		}
	}
	
	public void attack(Enemy other)
	{
		
	}
	
	/*public void useItem(Item it)
	{
		
	}*/
	
	public void flee(Enemy other)
	{
		
	}
	
	
}
