//Object: Player
//By Jake Holtham
//Due 6/10/16
//Mr. Segall | Data Structures | Period 1
import org.newdawn.slick.Image;
public class Player 
{
	public Image sprite;
	public Image leftSprite;
	public Image movingLeftSprite;
	public Image rightSprite;
	public Image movingRightSprite;
	public Image upSprite;
	public Image movingUpSprite;
	public Image downSprite;
	public Image movingDownSprite;
	public int x;
	public int y;
	public int direction;
	public boolean moving = false;
	public double between = 0;
	
	public Player(int dir,int x,int y)
	{
		this.x = x;
		this.y = y;
		//load images
		try
		{
			leftSprite = new Image("src/txtr/pcleft1.png");
			rightSprite = new Image("src/txtr/pcright1.png");
			downSprite = new Image("src/txtr/pcfront1.png");
			upSprite = new Image("src/txtr/pcback1.png");
			movingUpSprite = new Image("src/txtr/pcback2.png");
			movingDownSprite = new Image("src/txtr/pcfront2.png");
			movingLeftSprite = new Image("src/txtr/pcleft2.png");
			movingRightSprite = new Image("src/txtr/pcright2.png");
		}
		catch(Exception e)
		{
			System.out.println("Image not found!!");
		}
		
		sprite = upSprite;
		direction = 0;
		
		switch(dir)
		{
		
		//facing right
		case 1:
			direction = 1;
			sprite = rightSprite;
			break;
		//facing down
		case 2:
			direction = 2;
			sprite = downSprite;
			break;
			
		//facing left
		case 3:
			direction = 3;
			sprite = leftSprite;
			break;
			
		}
		
		
	}
	//handles sprite drawing based on tile and its location
	public void draw(Tile g)
	{
		sprite.draw(g.cornerX, g.cornerY);
	}
	
	
	
}
