package game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class PlayerBallClone extends GameObject{

	private Handler handler;

	private BufferedImage ballImage;
	private BufferedImage ballShadow;
	
	private int deadCount;
	
	private GameObject playerBall;

	private boolean collided = false;
	private int life;
	private int direction;
	private int count;
	
	public PlayerBallClone(float x, float y, ID id, int dir, Handler handler) {
		
		super(x, y, id);
		this.handler = handler;
		this.direction = dir;
		dead = false;
	
		BufferedImageLoader loader = new BufferedImageLoader();

		ballImage = loader.loadImage("/ballClone.png");
		ballShadow = loader.loadImage("/ballShadow.png");
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.PlayerBall){
					
				playerBall = tempObject;
			}	
		}
		if(dir == 0){
			velX = playerBall.getVelX()*0.5f;
			velY = playerBall.getVelY()*0.8f;;

		}
		else {
			velX = playerBall.getVelX()*1.5f;
			velY = playerBall.getVelY()*1.2f;
		}
		

	}
	

	public void tick() {
		count++;
		//System.out.println(released);
		//life++;
		//if(life> 300) handler.removeObject(this);
		
		//velY *= 0.95;
		//velX *= 0.99;
		
		x += velX;
		y += velY;
		
		velX = Game.clamp(velX, -8, 8);
		velY = Game.clamp(velY, -8, 8);
		
		
		
		if(y > Game.HEIGHT) {
			
			y = 508;
		}
		
		x = Game.clamp(x, 32*2, 32*17);
		
		//if(y >= Game.HEIGHT*2+100) handler.removeObject(this);
		
		
		collision();
		
	} 
	private void collision(){

		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.BlockKill){
				
				
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(this);
				}
			}
			
			
			
			
			if(tempObject.getId() == ID.BlockBreak){
				
				if(getBoundsLeft().intersects(tempObject.getBounds())){
					
					x = tempObject.getX()+ (35);
					//velX = 0;
					velX = velX * -1;
					handler.addObject(new Splash(tempObject.getX(), tempObject.getY(), ID.Splash, handler));
					handler.removeObject(tempObject);
					HUD.points +=100;
					AudioPlayer.getSound("breakBlock").play(1, 23);
					HUD.blocksToBreak--;
					
				} 
				if(getBoundsTop().intersects(tempObject.getBounds())){
					
					y = tempObject.getY()+ (35);
					//velY = 0;
					velY = velY * -1;
					handler.addObject(new Splash(tempObject.getX(), tempObject.getY(), ID.Splash, handler));
					handler.removeObject(tempObject);
					HUD.points +=100;
					AudioPlayer.getSound("breakBlock").play(1, 23);
					HUD.blocksToBreak--;
				}	
				if(getBoundsRight().intersects(tempObject.getBounds())){
					
					x = tempObject.getX()-(19);
					//velX = 0;
					velX = velX * -1;
					handler.addObject(new Splash(tempObject.getX(), tempObject.getY(), ID.Splash, handler));
					handler.removeObject(tempObject);
					HUD.points +=100;
					AudioPlayer.getSound("breakBlock").play(1, 23);
					HUD.blocksToBreak--;
					
				}	
				if(getBoundsBottom().intersects(tempObject.getBounds())){
					
					y = tempObject.getY()- (19);
					//velY = 0;
					velY = velY * -1;
					handler.addObject(new Splash(tempObject.getX(), tempObject.getY(), ID.Splash, handler));
					handler.removeObject(tempObject);
					HUD.points +=100;
					AudioPlayer.getSound("breakBlock").play(1, 23);
					HUD.blocksToBreak--;
				}
				
				
				//if(getBounds().intersects(tempObject.getBounds())){
				//	handler.removeObject(tempObject);
				//}
			}
			
			if(tempObject.getId() == ID.PlayerPaddle){
				if(getBoundsTop().intersects(tempObject.getBounds())){
					
					x = tempObject.getX()+ (35);
					//velX = 0;
					velX = velX * -1;
					velY = velY * -1;
				} 
				
				if(getBoundsLeft().intersects(tempObject.getBounds())){
					
					x = tempObject.getX()+ (35);
					//velX = 0;
					velX = velX * -1;
					velY = velY * -1;
				} 
				
				if(getBoundsRight().intersects(tempObject.getBounds())){
					
					x = tempObject.getX()-(19);
					//velX = 0;
					velX = velX * -1;
					velY = velY * -1;
				}	
				if(getBoundsBottom().intersects(tempObject.getBounds())){
					
					y = tempObject.getY()- (19);
					//velY = 0;
					velY = velY * -1;
				}
				
				
			}
			
			if(tempObject.getId() == ID.PlayerBall){
				if(count > 30){
					
					if(getBounds().intersects(tempObject.getBounds())){
						velX = velX * -1;
						velY = velY * -1;
					}
					
					/*
					if(getBoundsLeft().intersects(tempObject.getBounds())){
						
						x = tempObject.getX()+ (20);
						//velX = 0;
						velX = velX * -1;
						
					} 
					if(getBoundsTop().intersects(tempObject.getBounds())){
						
						y = tempObject.getY()+ (20);
						//velY = 0;
						velY = velY * -1;
					}	
					if(getBoundsRight().intersects(tempObject.getBounds())){
						
						x = tempObject.getX()-(19);
						//velX = 0;
						velX = velX * -1;
						
					}	
					if(getBoundsBottom().intersects(tempObject.getBounds())){
						
						y = tempObject.getY()- (19);
						//velY = 0;
						velY = velY * -1;
					} */
					
				}
				
				/*
				if(getBoundsLeft().intersects(tempObject.getBounds())){
					
					x = tempObject.getX()+ (20);
					//velX = 0;
					velX = velX * -1;
					
				} 
				if(getBoundsTop().intersects(tempObject.getBounds())){
					
					y = tempObject.getY()+ (20);
					//velY = 0;
					velY = velY * -1;
				}	
				if(getBoundsRight().intersects(tempObject.getBounds())){
					
					x = tempObject.getX()-(19);
					//velX = 0;
					velX = velX * -1;
					
				}	
				if(getBoundsBottom().intersects(tempObject.getBounds())){
					
					y = tempObject.getY()- (19);
					//velY = 0;
					velY = velY * -1;
				} */
				
			} 
			
			if(tempObject.getId() == ID.Block){
				
				
				if(getBoundsLeft().intersects(tempObject.getBounds())){
					
					x = tempObject.getX()+ (35);
					//velX = 0;
					velX = velX * -1;
					
				} 
				if(getBoundsTop().intersects(tempObject.getBounds())){
					
					y = tempObject.getY()+ (35);
					//velY = 0;
					velY = velY * -1;
				}	
				if(getBoundsRight().intersects(tempObject.getBounds())){
					
					x = tempObject.getX()-(19);
					//velX = 0;
					velX = velX * -1;
					
				}	
				if(getBoundsBottom().intersects(tempObject.getBounds())){
					
					y = tempObject.getY()- (19);
					//velY = 0;
					velY = velY * -1;
				}
				
			}
			
		}		
	}
	
	
	public Rectangle getBounds() {
		return new Rectangle((int)x-6, (int)y-6, ballImage.getWidth()+10, ballImage.getHeight()+10);
		
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x-2, (int)y+5, 4, 6);
		
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int)x+6, (int)y-2, 4, ballImage.getHeight()/2);
		
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int)x+14, (int)y+5, 4, 6);
		
	}
	public Rectangle getBoundsBottom() {
		return new Rectangle((int)x+6, (int)y+10, 4, ballImage.getHeight()/2);
		
	}
	public void render(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.white);

		g.drawImage(ballImage, (int)x, (int)y, null);
		
		
	}

	@Override
	public Polygon getBoundsPoly() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void drawShadow(Graphics g) {
		g.drawImage(ballShadow, (int)x+8, (int)y+8, null);
		
	}

	


}
