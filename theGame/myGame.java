package theGame;
import java.applet.Applet;
import java.awt.event.*;
import java.io.IOException;
import java.awt.*;
import java.nio.file.*;
public class myGame extends Applet implements KeyListener, Runnable
{
	
	//other stuff
	Image off_screen;
	Graphics off_g;
	MyImageLayer BackGroundImage  = new MyImageLayer("background1222.png",0,0,1);
	Image Image  = Toolkit.getDefaultToolkit().getImage("grass_platform.png");
	Image score_Img = Toolkit.getDefaultToolkit().getImage("score_0.png");
	Image hp_Img = Toolkit.getDefaultToolkit().getImage("HP_0.png");
	Image win = Toolkit.getDefaultToolkit().getImage("youWin.png");
	
	//game states
	int game_state = 100;
	//player's life
	int player_life = 50;
	int velocityX = 0;
	int velocityY = 0;
	
	//bgm-music
	BGM bgm = new BGM("bgm_0");
	BGM coin_bgm = new BGM("coin_sound_0");
	BGM jump_sound = new BGM("Jump-sound");
	BGM ta_da_sound = new BGM("TaDa");
	//tiles
	int numOfTiles = 6;//number of tile types
	
	Image tile_1  = Toolkit.getDefaultToolkit().getImage("grass_platform_0.png");
	Image tile_2  = Toolkit.getDefaultToolkit().getImage("grass_platform_1.png");
	Image tile_3  = Toolkit.getDefaultToolkit().getImage("grass_platform_2.png");
	Image tile_4  = Toolkit.getDefaultToolkit().getImage("grass_platform_3.png");
	Image tile_5  = Toolkit.getDefaultToolkit().getImage("spikes_0.png");
	
	//animations
	MyAnimation rt_anim = new MyAnimation("anim_run_",11, 5);
	MyAnimation lt_anim = new MyAnimation("lft_anim_run_",11, 5);
	MyAnimation still_anim = new MyAnimation("anim_still_",7, 10);
	MyAnimation lft_still_anim = new MyAnimation("lftAanim_still",7,10);
	MyAnimation coin = new MyAnimation("scoin_",8,70);
	MyAnimation enemy = new MyAnimation("enemy_",4,80);
	MyAnimation fireball = new MyAnimation("fireball_",5,20);
	MyAnimation dust_anim = new MyAnimation("dust_",12,5);
	MyAnimation jump_start = new MyAnimation("anim_jump_",16,5);
	MyAnimation jump_fall = new MyAnimation("dust_",12,5);
	MyAnimation flag = new MyAnimation("red-flag",1,1);
	
	//power-up
	
	//sprites
	int start_point;
	int yFloor;
	
	String SpriteType_1 = "coin";
	String SpriteType_2 = "enemy";
	String SpriteType_3 = "goal";
	
	MySprite sprite_run = new MySprite(100,380,250,240,rt_anim);
	MySprite spt_leftAnim = new MySprite(100,380,250,240,lt_anim);
	MySprite spt_stillAnim = new MySprite(100,405,300,216,still_anim);
	MySprite spt_lft_stillAnim = new MySprite(100,405,300,216,lft_still_anim);
	Enemy spt_enemy = new Enemy(520,425,161,120,enemy);
	Coin spt_coin = new Coin(200,425,82,80,coin);
	MySprite spt_fireball = new MySprite(130,150,72,61,fireball);
	MySprite dust = new MySprite(0,505,72,61,dust_anim);
	MySprite sprite_Jump = new MySprite(100,380,300,250,jump_start);
	
	
	Line line = new Line(sprite_run.x,sprite_run.y,spt_enemy.x,spt_enemy.y);
	//hitbox for main character
	
	//MyRect obstacle = new MyRect(550,400,300,250);
	
	//new tile map
	private TilePlatform Map; 
	
	//this is our booleans for our d-pad controls
	boolean lt_Pressed = false;
	boolean rt_Pressed = false;
	boolean up_Pressed = false;
	boolean dn_Pressed = false;
	
	boolean space_Pressed = false;
	
	boolean l_key_released = false;
	boolean r_key_released = false;
	
	//variables
	
	int score;
	boolean won = false;
	boolean isJumping = false;
	boolean itCollides = false;
	boolean itCollides_W_coin = false;
	boolean readyToFire, shoot = false;
	boolean left_spikeCol = false;
	boolean right_spikeCol = false;
	boolean left_tileCol = false;
	boolean right_tileCol = false;
	boolean isMoving = false;
	boolean shooting = false;
	
	Thread t;
	
	public void init()
	{
		off_screen = this.createImage(2000,2000);
		off_g  = off_screen.getGraphics();
		Map = new TilePlatform("map11.txt",32);
		
		requestFocus();
		addKeyListener(this);
		t = new Thread(this);
		t.start();
	}
	public void run()
	{
		sprite_run.setVelocity(1, 1);
		
		bgm.Play();
 		while(true)
		{
 			if (sprite_run.y == 380 || spt_leftAnim.y == 380){isJumping = false;}
 			if(lt_Pressed){
 				isMoving = true;
				spt_leftAnim.moveLeftBy(5);
				start_point = spt_leftAnim.x;
				spt_stillAnim.setX(start_point);
				spt_leftAnim.setX(start_point);
				sprite_run.setX(start_point);
				dust.setX(start_point);
				
				//System.out.println(spt_leftAnim.x);
				
				BackGroundImage.moveRightBy(5);
				Map.moveRightBy(3);
				if (BackGroundImage.getX() > 0){BackGroundImage.moveLeftBy(5);Map.moveLeftBy(3);}
								
			}
			
			if(rt_Pressed){
				isMoving = true;
				sprite_run.moveRightBy(5);	
				start_point = sprite_run.x;
				
				spt_stillAnim.setX(start_point);
				spt_leftAnim.setX(start_point);
				sprite_run.setX(start_point);
				dust.setX(start_point);
				
				//System.out.println(sprite_run.x);
				if (itCollides == false){
					BackGroundImage.moveLeftBy(5);
					Map.moveLeftBy(3);
				}
				else{
					BackGroundImage.moveLeftBy(0);
					Map.moveLeftBy(0);
				}
				
				if (BackGroundImage.getX() <= -5000 && itCollides == false)
				{
					BackGroundImage.moveRightBy(5);
					Map.moveRightBy(3);
				}
				else{
					BackGroundImage.moveLeftBy(0);
					Map.moveLeftBy(0);
				}
				//System.out.println("start point below");
				//System.out.println(Map.getTile(24, 0));
				//System.out.println(Map.getHeight());
				
			}
			if (Map.spriteCollision(sprite_run, SpriteType_1) == true || Map.spriteCollision(spt_leftAnim, SpriteType_1) == true){
				itCollides_W_coin = true;
				score += spt_coin.getScore(itCollides_W_coin);
				//coin_bgm.Play();			
			}
			
			else {itCollides_W_coin = false;}
			//collisions
			right_spikeCol = Map.tileCollision(sprite_run,"spike");
			left_spikeCol = Map.tileCollision(spt_leftAnim,"spike");
			left_tileCol = Map.tileCollision(sprite_run,"tile");
			right_tileCol =Map.tileCollision(spt_leftAnim,"tile");
			
			
			Map.getGoal(sprite_run);
			Map.spriteCollision(sprite_run, SpriteType_2);
			if (left_tileCol == true || right_tileCol == true){
				itCollides = true;
			}
			else{itCollides = false;}
			
			
			if ( spt_leftAnim.x < 0 ){spt_leftAnim.x = 0;}//left bounds
			
			if ( sprite_run.x == 550 ){sprite_run.x = 545;}
			if (BackGroundImage.getX()  <= -4435 && sprite_run.x == 545)
			{
				sprite_run.x = 551;
				//sprite_run.moveRightBy(6);
				
			}
			if ( sprite_run.x >= 1071 )
			{
				sprite_run.x = 1070;

			}			
			if(up_Pressed){
				isJumping = true;
				jump_sound.Play();
				sprite_run.StartJump(isJumping);
				
			}			
			
 			if(up_Pressed == false){isMoving = true;sprite_run.EndJump();}
			if(dn_Pressed){ }
			if(space_Pressed){sprite_run.shoot(spt_fireball);shooting = true;}
			//System.out.println(line.GetDistance(sprite_run.x,sprite_run.y,spt_enemy.x,spt_enemy.y));
			//System.out.println(BackGroundImage.getX());
			
			sprite_run.UpdateJump(isJumping);
			spt_leftAnim.UpdateJump(isJumping);
			sprite_Jump.UpdateJump(isJumping);
			repaint();
			
			try
			{
				t.sleep(15);
			}
			catch(Exception x){}
			if(Map.spriteCollision(sprite_run, SpriteType_2) == true|| left_spikeCol == true
					|| right_spikeCol == true){
				itCollides = true;
				player_life -= 1;
				if(player_life < 0){
					player_life = 0;
				}
			}
			else{itCollides = false;}
			if (player_life == 0){game_state = 1;}
			if (Map.getGoal(sprite_run) == true){
				won = true;
				game_state -= 1;}//ends the game if goal reached
			if(game_state == 49){ta_da_sound.Play();}
			if (game_state == 1){
				System.exit(0);
			}
			System.out.println(game_state);
		}
	}
	public void update(Graphics g)
	{
		off_g.clearRect(0, 0, 1200,1200 );
		paint(off_g);
		g.drawImage(off_screen, 0, 0, null);
		Map.update();
		
	}
	
	public void paint(Graphics g)
	{
		BackGroundImage.draw(g);
		g.drawImage(Image,550,100,this);
		//obstacle.draw(g);
		if (rt_Pressed == false && lt_Pressed == false){
			if (rt_Pressed){spt_stillAnim.setX(sprite_run.x);}
			if (lt_Pressed){spt_stillAnim.setX(spt_leftAnim.x);}
			
		spt_stillAnim.draw(g);		
		}
		
		if (l_key_released == true && r_key_released == false && rt_Pressed == false && lt_Pressed == false)
		{
			spt_lft_stillAnim.draw(g);
		}
		if(rt_Pressed){sprite_run.draw(g); }
		if(lt_Pressed){spt_leftAnim.draw(g);}
		for (int i = 0; i < numOfTiles;i++ ){
			Map.drawMap(g, "grass_platform_", numOfTiles,i);
			
		}
		if (up_Pressed&&rt_Pressed){sprite_run.draw(g);}///jump right
		if (up_Pressed&&lt_Pressed){spt_leftAnim.draw(g);}///jump left
		//if (up_Pressed&& rt_Pressed == false && lt_Pressed == false && isJumping == true){sprite_Jump.draw(g);}//neutral jump
		//new_sprite.draw(g);
		if (isJumping == true){dust.draw(g);}
		Map.drawMap(g,"spikes_",5,0);
		Map.drawMap(g, "red-flag", 5, 0);
		Map.drawSprite(g, coin,"coin",sprite_run,null);
		Map.drawSprite(g, enemy,"enemy",sprite_run,spt_fireball);
		Map.drawSprite(g, flag, "goal", sprite_run, spt_fireball);
		if (space_Pressed && shooting == true){spt_fireball.draw(g);}
		g.drawImage(score_Img, 10, 10, null);
		g.drawImage(hp_Img, 10, 65, null);
		g.drawString(Integer.toString(score) + " ", 130, 50);
		g.drawString(Integer.toString(player_life) + " ", 95, 100);
		g.setFont(new Font("times roman", Font.BOLD, 12));
		if (won){g.drawImage(win, 550, 250, null);}
		
	}
	public void keyPressed(KeyEvent e)
	{
		int code = e.getKeyCode();
		
		if (code == e.VK_LEFT) 		lt_Pressed = true;
		if (code == e.VK_RIGHT) 	rt_Pressed = true;
		if (code == e.VK_UP){
			sprite_run.StartJump(isJumping = true);
			spt_leftAnim.StartJump(isJumping = true);
			sprite_Jump.StartJump(isJumping = true);
			isJumping = true;
			up_Pressed = true;
			}
		if (code == e.VK_DOWN) 		dn_Pressed = true;
		
		if (code == e.VK_SPACE){
			
			space_Pressed = true;
			shooting = true;
			}
		//if (code == e.KEY_PRESSED) 	key_pressed = true;
		//if (code == e.KEY_RELEASED) key_released = true;
	}
	public void keyReleased(KeyEvent e)
	{
		int code = e.getKeyCode();
		
		if (code == e.VK_LEFT) 		lt_Pressed = false;
		if (code == e.VK_RIGHT) 	rt_Pressed = false;
		if (code == e.VK_UP){
			sprite_run.EndJump();
			spt_leftAnim.EndJump();
			sprite_Jump.EndJump();
			up_Pressed = false;
			
			}
		if (code == e.VK_DOWN) 		dn_Pressed = false;
		if (code == e.VK_SPACE)		space_Pressed = false;
	}
	
}