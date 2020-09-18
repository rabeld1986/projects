package theGame;

import java.awt.*;

public class MySprite extends MyRect
{
	MyAnimation anim;
	
	int x;
	int y;
	
	int dx;
	int dy;
	
	boolean selected = false;

	boolean moving = false;

	static final int LEFT   = 0;
	static final int RIGHT  = 1;
	static final int UP     = 2;
	static final int DOWN   = 3;

	int pose = LEFT;
	
		int A = 90;
		
		float yVel = 0;
		float G = 0.5f;
		boolean OnGround = false;
		double time = 0.016;
		String action;

		public MySprite(int x, int y,int w, int h, MyAnimation anim)
		{
			super(x,y, w,h);
			this.anim = anim;
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
			
		}
		
		public int getX()
		{
			return x;
		}
		
		public int getY()
		{
			return y;
		}
		
		public void setX(int newX)
		{
			this.x = newX;
			super.x = newX;
		}
		
		
		public void setH(int newH)
		{
			this.h = newH;
		}
		
		public void setW(int newW)
		{
			this.w = newW;
		}
		
		
		public void setY(int newY)
		{
			this.y = newY;
			super.y = newY;
		}
		
		public void draw(Graphics g)
		{
			
				g.drawImage(anim.nextImage(),x, y, null);
				//super.draw(g);
		
		}
		
		public void moveLeftBy(int dx)
		{
					
			x -= dx;
					
		}
		public void moveRightBy(int dx)
		{
		
			x += dx;
			
		}
		
		public void moveUPby(int dy)
		{
			
			y -= dy;
		}	
		public void moveDownby(int dy)
		{
			
			y += dy;
		}
		public int getHeight(int frame)
		{
			return anim.getImage(frame).getHeight(null);
		}
		
		public int getWidth(int frame)
		{
			return anim.getImage(frame).getWidth(null);
		}
		public void setLocation(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		public void setVelocity(int vx, int vy)
		{
			dx = vx;
			dy = vy;
		}
		public int getVelocityX(){
			return dx;
		}
		public int getVelocityY(){
			return dy;
		}
		public void StartJump(boolean jump)
		{			
			if (jump){
				yVel = -20.0f;
				OnGround = false;
			}
		}
		public void EndJump()
		{
		    if(yVel < -6.0)
		        yVel = -6.0f;
		}
		
		public void UpdateJump(boolean jump)
		{
		    yVel += G;
		    y += yVel;
		    super.y += yVel;
		    
		    if(y > 380.0)
		    {
		        y = 380;
		        yVel = 0.0f;
		        OnGround = true;
		        jump = false;
		    }
		    if(super.y > 380.0)
		    {
		        y = 380;
		        yVel = 0.0f;
		        OnGround = true;
		        jump = false;
		    }
		    
		}

		public void shoot(MySprite shell)
		{
			int unitX = (int)(Math.cos(A*Math.PI/180));
			int unitY = (int)(Math.sin(A*Math.PI/180));
			
			shell.setLocation((int)x* unitX, (int)y * unitY);
			int vx = (int)(10 *Math.cos(A*Math.PI/180));
			int vy = (int)(10 *Math.sin(A*Math.PI/180));
			
			shell.setVelocity(vx, vy);
			shell.x += vy;
			
		}
  		public void RemoveSprite()
		{
			this.x = -100000;
			this.y = -1000000;
			super.moveBy(x, y);
		}
}
