package theGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class MyImageLayer 
{
		Image Image;
		
		int x;
		int y;
		int z;
		
		public MyImageLayer(String File, int x, int y,int z)
		{
			
			Image = Toolkit.getDefaultToolkit().getImage(File);
			this.x= x;
			this.y = y;
			this.z = z;
			
		}
		public int getX()
		{
			return x;
		}
		
		public int getY()
		{
			return y;
		}
		
		public int getZ()
		{
			return z;
		}
		public void moveLeftBy(int dx)
		{
			x -= dx;
		}
		
		public void moveRightBy(int dx)
		{
			x += dx;
		}
		
		public void draw(Graphics g)
		{
			for (int i = 0; i < 10; i++ )
				g.drawImage(Image, x/z + 600*i, y,null);
		}
}
	

