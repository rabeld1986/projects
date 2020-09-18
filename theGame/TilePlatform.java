package theGame;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class TilePlatform 
{
	int x =  0;
	int y = 0;
	
	Image [] image;
	
	private TilePlatform Map;
	
	private LinkedList sprites;
	
	private int tileSize;
	private int [][] map;
	private int mapWidth;
	private int mapHeight;
	
	
	public TilePlatform(String fileName, int tileSize)
	{
		this.tileSize = tileSize;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			mapWidth = Integer.parseInt(br.readLine());
			mapHeight = Integer.parseInt(br.readLine());
			map = new int[mapHeight][mapWidth];
			
			String delimiters = " ";
			for(int row = 0; row < mapHeight;row++){
				String line = br.readLine();
				String[] tokens = line.split(delimiters);
				for(int col =0; col < mapWidth;col++){
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
		}
		catch(Exception e){}
	}
	
	public int getWidth()
	{
		return map.length;
	}
	
	public int getHeight()
	{
		return map[0].length;
	}
	
	public int getTileWidth(int FromImageNum)
	{
		return image[FromImageNum].getWidth(null);
	}
	
	public int getTileHeight(int FromImageNum)
	{
		return image[FromImageNum].getHeight(null);
	}
	public int getTile(int x, int y) 
	{
		if (x < 0 || x >= getWidth() ||
			y < 0 || y >= getHeight())
		{
			return 0;
		}
		else {
			return map[x][y];
		}
	}
	public void update(){
		 
	}
	public void drawMap(Graphics g,String file, int tileCount, int Img_num)
	{
	
		for (int row = 0; row < mapHeight;row++){
			for(int col = 0;col<mapWidth; col++){
				int rc = map[row][col];
				if (rc == 1 && file == "grass_platform_"){
					image = new Image[tileCount];
					image[Img_num] = Toolkit.getDefaultToolkit().getImage(file + Img_num +".png");
					g.drawImage(image[0],x + col * tileSize,y + row *tileSize,null);
				}
				if (rc == 2 && file == "grass_platform_"){
					image = new Image[tileCount];
			
					image[Img_num] = Toolkit.getDefaultToolkit().getImage(file + Img_num +".png");
					g.drawImage(image[1],x + col * tileSize,y + row *tileSize,null);
				}	
				if (rc == 3 && file == "grass_platform_"){
					image = new Image[tileCount];
				
					image[Img_num] = Toolkit.getDefaultToolkit().getImage(file + Img_num +".png");
					g.drawImage(image[2],x + col * tileSize,y + row *tileSize,null);
				}
				if (rc == 4 && file == "grass_platform_"){
					image = new Image[tileCount];
				
					image[Img_num] = Toolkit.getDefaultToolkit().getImage(file + Img_num +".png");
					g.drawImage(image[3],x + col * tileSize,y + row *tileSize,null);
				}
				if (rc == 5){
					image = new Image[tileCount];
				
					image[4] = Toolkit.getDefaultToolkit().getImage(file + Img_num +".png");
					g.drawImage(image[4],x + col * tileSize,y + row *tileSize,null);
				}
				if (rc == 8 && file == "red-flag"){
					Image img;
					img = Toolkit.getDefaultToolkit().getImage(file +".png"); 
					g.drawImage(img,x + col * tileSize,y + row *tileSize,null);
				}
				if (rc == 0){
					image = null;
				}
				if (rc > 0)
				{
					//MyRect r = new MyRect(x + col   * tileSize,y+55 + row  * tileSize,tileSize , tileSize);
					//r.draw(g);
				}	
				else {image = null;}	
				}
			
			}
		}
	public void drawSprite(Graphics g, MyAnimation anim,String SpriteType,MySprite playerSprite,MySprite fireball)
	{
		ArrayList<MySprite> coin_stack = new ArrayList<MySprite>();
		for (int row = 0; row < mapHeight;row++){
			for(int col = 0;col<mapWidth; col++){
				int rc = map[row][col];
				if (rc == 6 && SpriteType == "coin"){
					Coin spt_coin = new Coin(x + col   * tileSize,y + row  * tileSize,82,80,anim);
					for (int coins = 0;coins==0;coins++){
						
						coin_stack.add(coins, spt_coin);
						coin_stack.get(coins).draw(g);
						
						//spt_coin.setLocation(-100000, -10000);
						//spt_coin.setLocation(-100000, -10000);
						//spt_coin.moveBy(10000, 20000);
						if (coin_stack.get(coins).overlaps(playerSprite))
						{
							coin_stack.remove(coin_stack.get(coins ));
							
							
						}
					}
					
				}
				if (rc == 7 && SpriteType == "enemy"){
					Enemy spt_enemy = new Enemy(x+ + col   * tileSize-40,y + row  * tileSize,131,110,anim);
					
					spt_enemy.draw(g);
					
					spt_enemy.shootFireball(fireball,playerSprite);
					//fireball.draw(g);
					Line line = new Line(playerSprite.x,playerSprite.y,x + col   * tileSize-40,y + row  * tileSize);
					//line.draw(g);
					if (line.GetDistance(playerSprite.x,playerSprite.y,x + col   * tileSize-40,y + row  * tileSize) == 10){
						spt_enemy.shootFireball(fireball,playerSprite);
						fireball.draw(g);
					}
	
				}
			}
		}
	}	
	Point NewPos = new Point();
	public boolean tileCollision(MySprite playerSprite,String tileType)
	{
		boolean overlaps = false;
		
		for (int row = 0; row < mapHeight;row++){
			for(int col = 0;col<mapWidth; col++){
				int rc = map[row][col];
				if (rc != 0 && rc != 6 && rc != 7 && rc != 5&&tileType == "tile")
				{
					MyRect r = new MyRect(x + col   * tileSize,y+55 + row  * tileSize,tileSize , tileSize);
					//r.draw(g);
					
					if (playerSprite.overlaps(r)){
						overlaps = true;
						playerSprite.setLocation(r.x - playerSprite.w, r.y - playerSprite.h);
						
						return overlaps;
					}
				}
				if(rc == 5 && tileType == "spike"){
					MyRect r = new MyRect(x + col   * tileSize,y-15 + row  * tileSize,tileSize , tileSize);
					//r.draw(g);
					
					if (playerSprite.overlaps(r)){
						overlaps = true;
						playerSprite.setLocation(r.x - playerSprite.w, r.y - playerSprite.h);
						return overlaps;
					}
				}
			
			}
			
		}
		return overlaps;
		
	}
	
	public boolean spriteCollision(MySprite playerSprite,String SpriteType)
	{
		boolean overlaps = false;
		
		for (int row = 0; row < mapHeight;row++){
			for(int col = 0;col<mapWidth; col++){
				int rc = map[row][col];
				if (rc == 6 && SpriteType == "coin"){
					MyRect r = new MyRect(x + col   * tileSize,y+55 + row  * tileSize,82,80);
					if (playerSprite.overlaps(r)){
						overlaps = true;
					}
				}
				if (rc == 7 && SpriteType == "enemy"){
					MyRect r = new MyRect(x + col   * tileSize,y+55 + row  * tileSize,161,120);	
					if (playerSprite.overlaps(r)){
						playerSprite.setLocation(r.x - playerSprite.w, r.y - playerSprite.h);
						overlaps = true;
						
					}
				}
				
			}
		}
		return overlaps;
	}
	public boolean getGoal(MySprite playerSprite)
	{
		boolean overlaps = false;
		
		for (int row = 0; row < mapHeight;row++){
			for(int col = 0;col<mapWidth; col++){
				int rc = map[row][col];
			
				if(rc == 8 ){
					MyRect r = new MyRect(x + col   * tileSize,y-15 + row  * tileSize,tileSize , tileSize);
					//r.draw(g);
					
					if (playerSprite.overlaps(r)){
						overlaps = true;
						
					}
				}
			}	
		}
		return overlaps;
	}	
	public void moveLeftBy(int dx)
	{
		x -= dx;
	}
	
	public void moveRightBy(int dx)
	{
		x += dx;
	}
	

}
