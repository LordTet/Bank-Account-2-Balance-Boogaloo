import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

//Class: PlayerBattle
//By Christian Wettre
//Due 6/10/16
//Mr Segall | Data Structures | Period 1
public class Battle extends BasicGameState
{
	GameContainer gc;
	StateBasedGame game;
	LocalMap map;
	Image player;
	Image opponent;
	Image fight;
	Image fight1;
	Image item;
	Image item1;
	Image escape;
	Image escape1;
	
	Input inp;
	
	PlayerBattle ply;
	Enemy ene;
	
	boolean isPlayerTurn;
	
	boolean activeFight = false;
	boolean activeItem = false;
	boolean activeEscape = false;
	
	int ed;
	int d;
	int heal;
	int edcount;
	int dcount;
	int healcount;
	
	public void give(LocalMap x)
	{
		map = x;
	}
	
	//sprite stuff
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
	{
		gc = arg0;
		game = arg1;
		
		//reset variables
		ed = 0;
		d = 0;
		heal = 0;
		edcount = 0;
		dcount = 0;
		healcount = 0;
		
		//images
		fight = new Image("src/txtr/fight.jpg");
		fight1 = new Image("src/txtr/fightactive.png");
		item = new Image("src/txtr/item.jpg");
		item1 = new Image("src/txtr/itemactive.png");
		escape = new Image("src/txtr/escape.jpg");
		escape1 = new Image("src/txtr/escapeactive.png");
		player = new Image("src/txtr/pcbattle1.png");
		
		//player and enemy constructors; enemy is constructed from the previously written to battle_enemy.txt file
		ply = new PlayerBattle();
		ene = new Enemy("src/data/battle_enemy.txt");
		opponent = ene.sprite;
		if (ply.spd > ene.spd)
			isPlayerTurn = true;
		else
			isPlayerTurn = false;
	}

	//renders buttons, player, enemy, box, damage, and stats
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException
	{
		arg2.drawRect(50, 400, 700, 150);
		arg2.setColor(Color.white);
		//Fight, Item, Flee buttons.
		//glitch sprite if the booleans are true
		if (!activeFight)
		{
			fight.draw(100, 450);
		}
		else
		{
			fight1.draw(100, 450);
		}
		if (!activeItem)
		{
			item.draw(300, 450);
		}
		else
		{
			item1.draw(300, 450);
		}	
		if (!activeEscape)
		{
			escape.draw(500, 450);
		}
		else
		{
			escape1.draw(500, 450);
		}
		escape.draw(500, 450);
		//Enemy and player sprites
		opponent.draw(100, 100);
		player.draw(500, 100);
		
		//writes the damage for 30 frames
		if (dcount != 0)
		{
			arg2.drawString(d + " damage!", 300, 100);
			dcount--;
		}
		
		if (edcount != 0)
		{
			arg2.drawString(ed + " damage!", 300, 250);
			edcount--;
		}
		
		if (heal != 0)
		{
			arg2.drawString(heal + " HP recovered!", 300, 100);
			healcount--;
		}
		
		//writes current HP and level
		arg2.drawString(ply.HP + " / " + ply.maxHP + " HP", 500, 300);
		arg2.drawString("LV " + ply.lv + ", " + ply.exp + " EXP", 500, 350);
		
		//writes enemy name and HP
		arg2.drawString(ene.name + ": " + ene.HP + " / " + ene.maxHP + " HP", 100, 300);
		
	}

	//update method
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException
	{
		//Buttons are 160 x 60 and are spaced 50 (x) apart
		inp = gc.getInput();
		
		//does the cool glitch effect if the mouse is over the button
		if((inp.getAbsoluteMouseX() > 99 && inp.getAbsoluteMouseX() < 261) && (inp.getAbsoluteMouseY() > 449 && inp.getAbsoluteMouseY() < 511))
		{
			activeFight = true;
		}
		else
		{
			activeFight = false;
		}
		if((inp.getAbsoluteMouseX() > 299 && inp.getAbsoluteMouseX() < 459) && (inp.getAbsoluteMouseY() > 449 && inp.getAbsoluteMouseY() < 511))
		{
			activeItem = true;
		}
		else
		{
			activeItem = false;
		}
		if((inp.getAbsoluteMouseX() > 499 && inp.getAbsoluteMouseX() < 661) && (inp.getAbsoluteMouseY() > 449 && inp.getAbsoluteMouseY() < 511))
		{
			activeEscape = true;
		}
		else
		{
			activeEscape = false;
		}
		
		//if the mouse is at the fight button and pressed, fight
		if ((inp.getAbsoluteMouseX() > 99 && inp.getAbsoluteMouseX() < 261) && (inp.getAbsoluteMouseY() > 449 && inp.getAbsoluteMouseY() < 511) && inp.isMousePressed(Input.MOUSE_LEFT_BUTTON) && isPlayerTurn)
		{
			d = ply.attack(ene);
			
			dcount = 30;
			isPlayerTurn = false;
			//ends battle and gives exp
			if(ene.HP <= 0)
			{
				//ends game if boss is defeated
				if (ene.name.equals("Cyber Angel"))
				{
					game.enterState(0);
				}
				ply.exp += ene.exp;
				
				game.enterState(1);
			}
		}
		
		//"item" method; heals the player
		else if ((inp.getAbsoluteMouseX() > 299 && inp.getAbsoluteMouseX() < 459) && (inp.getAbsoluteMouseY() > 461 && inp.getAbsoluteMouseY() < 499) && inp.isMousePressed(0) && isPlayerTurn)
		{
			int heal = ply.heal();
			healcount = 30;
			isPlayerTurn = false;
		}
		
		//escape method, generates a boolean that is random and depends on both creatures' speed
		else if ((inp.getAbsoluteMouseX() > 499 && inp.getAbsoluteMouseX() < 661) && (inp.getAbsoluteMouseY() > 461 && inp.getAbsoluteMouseY() < 499) && inp.isMousePressed(Input.MOUSE_LEFT_BUTTON) && isPlayerTurn)
		{
			boolean flee = ply.flee(ene);
			if(flee && !(ene.name.equals("Cyber Angel")))
			{
				game.enterState(1);
			}
			isPlayerTurn = false;
		}
		
		//enemy attack
		if (!isPlayerTurn)
		{
			ed = ene.attack(ply);
			edcount = 30;
			if (ply.HP <= 0)
			{
				ply.exp = 0;
				game.enterState(0);
			}
			isPlayerTurn = true;
		}
	
		
		
		
		
	}
	
	

	public int getID()
	{
		return 2;

	}
	
}
