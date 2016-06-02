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
    boolean isPlayerTurn;
   
    public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
    {
        gc = arg0;
        game = arg1;
        isPlayerTurn = true;
        fight = new Image("src/txtr/fight.jpg");
        fight1 = new Image("src/txtr/fightactive.png");
        item = new Image("src/txtr/item.jpg");
        item1 = new Image("src/txtr/itemactive.png");
        escape = new Image("src/txtr/escape.jpg");
        escape1 = new Image("src/txtr/escapeactive.png");
        ply = new PlayerBattle();
        ene = new Enemy("src/data/battle_enemy.txt");
    }
 
    public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException
    {
        arg2.drawRect(50, 400, 700, 150);
        //TODO: Fight, Item, Flee buttons.
        fight.draw(100, 400);
        item.draw(300, 400);
        escape.draw(500, 400);
       
 
    }
 
    public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException
    {
        //Buttons are 160 x 60 and are spaced 50 (x) apart
        inp = gc.getInput();
        //if the mouse is at the fight button and pressed, fight
        if ((inp.getAbsoluteMouseX() > 99 && inp.getAbsoluteMouseX() < 261) && (inp.getAbsoluteMouseY() > 461 && inp.getAbsoluteMouseY() < 399) && inp.isMousePressed(0) && isPlayerTurn)
        {
            ply.attack(ene);
            //drawString to show damage
            isPlayerTurn = false;
            if(ene.HP <= 0)
            {
                ply.exp += ene.exp;
                //change state
            }
        }
        /*else if ((inp.getAbsoluteMouseX() > 299 && inp.getAbsoluteMouseX() < 461) && (inp.getAbsoluteMouseY() > 461 && inp.getAbsoluteMouseY() < 399) && inp.isMousePressed(0) && isPlayerTurn)
        {
            draw different item menu
            when item is clicked
            playerBattle.useItem(the input);
            redraw the normal menu
            isPlayerTurn = false;
        }
        */
        else if ((inp.getAbsoluteMouseX() > 499 && inp.getAbsoluteMouseX() < 661) && (inp.getAbsoluteMouseY() > 461 && inp.getAbsoluteMouseY() < 399) && inp.isMousePressed(0) && isPlayerTurn)
        {
            if(ply.flee(ene))
            {
                //change state
            }
            isPlayerTurn = false;
        }
        else if (!isPlayerTurn)
        {
            ene.attack(ply);
        }
       
       
       
    }
 
    public int getID()
    {
        return 3;
 
    }
   
}