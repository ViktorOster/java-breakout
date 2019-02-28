package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class PowerUp extends GameObject{
	
	private Handler handler;
	
	private GameObject player, playerBall;
	
	private BufferedImage mball, shot, blockShadow;
	
	private String type;
	private Color randCol;
	private Random r = new Random();
	private int powerUp, powerChance;

	
	public PowerUp(float x, float y, ID id, String typ, Handler handler) { //1= L, 2 = down, 3 = R, 4 = up, 5 = L/DOWN, 6 = R/DOWN 7= UP/L, 8= UP/R
		super(x, y, id);
		this.handler = handler;
	
		this.type = typ;
		BufferedImageLoader loader = new BufferedImageLoader();
		
		blockShadow = loader.loadImage("/blockShadow.png");
		mball = loader.loadImage("/mballPower.png");
		shot = loader.loadImage("/shotPower.png");
		
		velY = 3;
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.PlayerBall){
					
				playerBall = tempObject;
			}	
		}
	}

	public void tick() {
		y += velY;
		
		collision();
				
		
		
	}
	private void collision(){

		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.PlayerPaddle){
				
				if(getBounds().intersects(tempObject.getBounds())){
					//tempObject.setVelX(velX);
					//tempObject.setVelY(velY);
					if(playerBall.isReleased()==true){
						AudioPlayer.getSound("breakBlock").play(1, 23);
						if(type.equals("mball")){
							if(!playerBall.released){
								handler.addObject(new PlayerBallClone(playerBall.getX(), playerBall.getY(), ID.PlayerBallClone, 0, handler));
								handler.addObject(new PlayerBallClone(playerBall.getX(), playerBall.getY(), ID.PlayerBallClone, 1, handler));
							}
							
						}
						if(type.equals("shot")){
							PlayerPaddle.hasShoot = true;
						}
						handler.removeObject(this);
					}
					
					
				} 
				
				
				
			}
			
			if(tempObject.getId() == ID.PlayerBall){
					
				if(getBounds().intersects(tempObject.getBounds())){
					//tempObject.setVelX(velX);
					//tempObject.setVelY(velY);
					AudioPlayer.getSound("breakBlock").play(1, 23);
				
				} 
				
				
				
			}	
		}		
	}
	
	@Override
	public void drawShadow(Graphics g){
		g.drawImage(blockShadow, (int)x+8, (int)y+8, null);
		
	}
	
	public Rectangle getBounds() {

		return new Rectangle((int)x, (int)y, 32+8, 18);		
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		//g2d.setColor(Color.black);
		//g2d.fillRect((int)x, (int)y, 32, 16);
		
		//g.setColor(Color.green);
		if(type.equals("shot")) g.drawImage(shot, (int)x, (int)y, null);
		else if(type.equals("mball")) g.drawImage(mball, (int)x, (int)y, null);

		//g2d.draw(getBounds());
		
		
	}

	@Override
	public Polygon getBoundsPoly() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBoundsLeft() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBoundsTop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBoundsRight() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBoundsBottom() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
	
