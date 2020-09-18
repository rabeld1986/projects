package theGame;


public class Enemy extends MySprite
{
	public Enemy(int x, int y,int w, int h, MyAnimation anim)
	{
		super(x,y,w,h,anim);
		this.x = x;
		this.y = y;
		
	}
	public void shootFireball(MySprite fireball, MySprite PlayerSprite)
	{
		double ux = LookUpTable.cos[A];
		double uy = LookUpTable.sin[A];
		
		double nx = -uy;
		double ny = ux;
		
		double vx = PlayerSprite.getX() - x;
		double vy = PlayerSprite.getY() - y;
		
		double  d = vx*nx + vy*ny;
		
	
		if ((d < 20) && (d > -20) && fireball.x < 1000)
		{
			super.shoot(fireball);
		}
	}
	
	public void moveTowards(MySprite PlayerSprite)
	{
		TurnToward(PlayerSprite);
		this.moveLeftBy(4);
	}
	public void TurnToward(MySprite PlayerSprite)
	{
		double ux = LookUpTable.cos[A];
		double uy = LookUpTable.sin[A];
		
		double nx = -uy;
		double ny = ux;
		
		double vx = PlayerSprite.getX()- x;
		double vy = PlayerSprite.getY() - y;
		
		double  d = vx*ux + vy*uy;
		
		if (d > 50)
		{
			A += 3;
			if (A >= 360) A -= 360;
		}
		
		if (d < -50)
		{
			A -= 3;
			if (A <= 360) A += 360;
		}
	}

}
