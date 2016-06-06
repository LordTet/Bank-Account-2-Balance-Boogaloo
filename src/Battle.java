package src;

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
public class Battle extends BasicGameState
{
	GameContainer gc;
	StateBasedGame game;
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
	Graphics gr;
	boolean isPlayerTurn;
	
	
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
	{
		gc = arg0;
		game = arg1;
		gr = new Graphics();
		isPlayerTurn = true;
		fight = new Image("src/txtr/fight.jpg");
		fight1 = new Image("src/txtr/fightactive.png");
		item = new Image("src/txtr/item.jpg");
		item1 = new Image("src/txtr/itemactive.png");
		escape = new Image("src/txtr/escape.jpg");
		escape1 = new Image("src/txtr/escapeactive.png");
		player = new Image("src/txtr/pcbattle1.png");
		ply = new PlayerBattle();
		ene = new Enemy("src/data/battle_enemy.txt");
		opponent = ene.sprite;
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException
	{
		arg2.drawRect(50, 400, 700, 150);
		//TODO: Fight, Item, Flee buttons.
		fight.draw(100, 450);
		item.draw(300, 450);
		escape.draw(500, 450);
		opponent.draw(100, 100);
		player.draw(500, 100);
		
		

	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException
	{
		//Buttons are 160 x 60 and are spaced 50 (x) apart
		inp = gc.getInput();
		gr.setColor(Color.white);
		
		if((inp.getAbsoluteMouseX() > 99 && inp.getAbsoluteMouseX() < 261) && (inp.getAbsoluteMouseY() > 449 && inp.getAbsoluteMouseY() < 511))
		{
			fight1.draw(100, 450);
		}
		
		//if the mouse is at the fight button and pressed, fight
		if ((inp.getAbsoluteMouseX() > 99 && inp.getAbsoluteMouseX() < 261) && (inp.getAbsoluteMouseY() > 449 && inp.getAbsoluteMouseY() < 511) && inp.isMousePressed(Input.MOUSE_LEFT_BUTTON) && isPlayerTurn)
		{
			int d = ply.attack(ene);
			
			
			isPlayerTurn = false;
			//ends battle
			if(ene.HP <= 0)
			{
				ply.exp += ene.exp;
				game.enterState(1);
			}
		}
		/*else if ((inp.getAbsoluteMouseX() > 299 && inp.getAbsoluteMouseX() < 561) && (inp.getAbsoluteMouseY() > 461 && inp.getAbsoluteMouseY() < 499) && inp.isMousePressed(0) && isPlayerTurn)
		{
			draw different item menu
			when item is clicked
			playerBattle.useItem(the input);
			redraw the normal menu
			isPlayerTurn = false;
		}
		*/
		else if ((inp.getAbsoluteMouseX() > 499 && inp.getAbsoluteMouseX() < 661) && (inp.getAbsoluteMouseY() > 561 && inp.getAbsoluteMouseY() < 499) && inp.isMousePressed(Input.MOUSE_LEFT_BUTTON) && isPlayerTurn)
		{
			if(ply.flee(ene))
			{
				//change state
			}
			isPlayerTurn = false;
		}
		if (!isPlayerTurn)
		{
			int ed = ene.attack(ply);
			gr.drawString(ed + " damage!", 300, 100);
			if (ply.HP <= 0)
			{
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
