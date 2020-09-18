package theGame;

import java.awt.Graphics;


public class MyRect 
{
	int x;
	int y;
	
	int w; 
	int h;
	
	int A = 90;
	
	boolean held = false;
	
	public void grab()
	{
	
		held = true;
		
	}
	
	public void drop()
	{
		
		held = false;
		
	}
	public boolean overlaps(MyRect r)
	{
		return (x < r.x + r.w) && (x + w > r.x) && (y < r.y + r.h) &&
				(y + h > r.y);
	}
	
	public boolean contains(int mx, int my)
	{
		return (mx > x ) && (mx < x+w) && (my > y) && (my < y + h);
	}
	public MyRect(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public void draw(Graphics g)
	{
		g.drawRect(x, y, w, h);
	}
	
	public void moveBy(int dx, int dy)
	{
		x += dx;
		y += dy;
	}
	public void moveLeft(int dx)
	{
		x-=dx;
	}
	
	public void moveRight(int dx)
	{
		x+=dx;
	}
	public void resizeBy(int dw,int dh)
	{
		w += dw;
		h += dh;
	}
	public void setLocation(int newX,int newY)
	{
		this.x = newX;
		this.y = newY;
	}
}

