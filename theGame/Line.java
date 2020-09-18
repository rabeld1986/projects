package theGame;

import java.awt.Graphics;

public class Line
{
	int player_x;
	int player_y;
	
	int enemy_x;
	int enemy_y;
	
	int x0;
	int x1;
	
	int y0;
	int y1;
	
	double xN;
	double yN;
	public Line(int player_x, int player_y, int enemy_x, int enemy_y)
	{
		this.player_x = player_x;
		this.player_y = player_y;
		
		this.enemy_x = enemy_x;
		this.enemy_y = enemy_y;
		
		int x0 = Math.min(player_x, enemy_x);
		int y0 = Math.min(player_y, enemy_y);
		
		int x1 = Math.max(player_x, enemy_x);
		int y1 = Math.max(player_y, enemy_y);
		
		this.x0 =x0;
		this.x1 =x1;
		this.y0 =y0;
		this.y1 =y1;
		
		double dx = x1- x0;
		double dy = y1 - y0;
		
		double mag = Math.sqrt((dx*dx) + (dy*dy));
	
		double xU = dx/mag;
		double yU = dy/mag;
		
		xN = -yU; //get the normal vector
		yN = xU;
	}
	
	public double distanceTo(double x, double y)
	{
		return xN * (x - x0) + yN * (y - y0);
		
		
	}
	public double GetDistance(int player_x, int player_y, int enemy_x, int enemy_y)
	{
		this.player_x = player_x;
		this.player_y = player_y;
		
		this.enemy_x = enemy_x;
		this.enemy_y = enemy_y;
		
		int x0 = Math.min(player_x, enemy_x);
		int y0 = Math.min(player_y, enemy_y);
		
		int x1 = Math.max(player_x, enemy_x);
		int y1 = Math.max(player_y, enemy_y);
		
		this.x0 =x0;
		this.x1 =x1;
		this.y0 =y0;
		this.y1 =y1;
		
		double dx = x1- x0;
		double dy = y1 - y0;
		
		double pX = dx*dx;
		double pY = dy*dy;
		
		double T = pX + pY;
		
		double d = Math.sqrt(T);
		return d;
	}
	public void draw(Graphics g)
	{
		g.drawLine(x0, y0 ,x1 ,y1);
	}
}
