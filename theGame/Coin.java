package theGame;
import java.lang.reflect.Constructor;
import java.awt.*;
public class Coin extends MySprite 
{
	int points = 0;
	boolean collision;
	public Coin(int x, int y,int w, int h, MyAnimation anim)
	{
		super(x,y,w,h,anim);
		this.x = x;
		this.y = y;
	}
	
	public int getScore(boolean colision)
	{
		collision = false;
		collision = colision;
		points = 0;
		if (colision)
		{
			this.points =10;
			///also it has to remove sprite from map
			return points;
		}
		
		return points;
	}	

}
