import org.newdawn.slick.Image;
public class Player 
{
	public Image sprite;
	public Image leftSprite;
	public Image rightSprite;
	public Image upSprite;
	public Image downSprite;
	public int x;
	public int y;
	public int direction;
	public boolean moving = false;
	public double between = 0;
	
	public Player(int dir,int x,int y)
	{
		this.x = x;
		this.y = y;
		try
		{
			leftSprite = new Image("src/txtr/pcleft1.png");
			rightSprite = new Image("src/txtr/pcright1.png");
			downSprite = new Image("src/txtr/pcfront1.png");
			upSprite = new Image("src/txtr/pcback1.png");
			
		}
		catch(Exception e)
		{
			System.out.println("Image not found!!");
		}
		
		sprite = upSprite;
		direction = 0;
		
		switch(dir)
		{
		
		case 1:
			direction = 1;
			sprite = rightSprite;
			break;
			
		case 2:
			direction = 2;
			sprite = downSprite;
			break;
		case 3:
			direction = 3;
			sprite = leftSprite;
			break;
			
		}
		
		
	}
	public void draw(Tile g)
	{
		//System.out.println(g.cornerX);
		sprite.draw(g.cornerX, g.cornerY);
	}
	
	
	
}
