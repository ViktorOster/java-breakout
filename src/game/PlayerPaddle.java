package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class PlayerPaddle extends GameObject{
	
	private Handler handler;
	
	private int sizeX;
	private int sizeY;
	private PlayerHandler parent;
	
	private BufferedImage paddle, turrets;
	private BufferedImage paddleShadow;
	private String type;
	private int velTimer;
	private float xPos1;
	private float xPos2;
	static boolean hasShoot = false;
	private int shootTime;
	
	public PlayerPaddle(float x, float y, ID id, PlayerHandler par, Handler handler) { //1= L, 2 = down, 3 = R, 4 = up, 5 = L/DOWN, 6 = R/DOWN 7= UP/L, 8= UP/R
		super(x, y, id);
		this.handler = handler;
		
		
		this.parent = par;
		width = 32;
		
		BufferedImageLoader loader = new BufferedImageLoader();

		paddle = loader.loadImage("/paddle.png");
		turrets = loader.loadImage("/turrets.png");
		paddleShadow = loader.loadImage("/paddleShadow.png");
	}

	public void tick() {
		
		if(hasShoot) shootTime++;
		if(shootTime > 700) {
			shootTime = 0;
			hasShoot = false;
		}
		x = parent.getMX();
				
		if(velTimer == 0) {
			xPos1 = x;

		}
		velTimer++;
		
		if(velTimer > 3) {
			xPos2 = x;

			//xPos2 = x;
			
			velX = (xPos2-xPos1) / 2;

			//System.out.println(velX + " " + velY);
			
			velTimer = 0;
		}
		
		x = Game.clamp(x, 32*2, 32*15-8);
		
		
		
		collision();
				
	}
	private void collision(){

		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.PlayerBall || tempObject.getId() == ID.PlayerBallClone){
					
				if(getBounds().intersects(tempObject.getBounds())){
				
					
					
					//tempObject.setVelY(velY);
					
					//tempObject.setVelX(velX);
					//tempObject.setVelY(velY);
					
					
				} 
				
				
				
			}	
		}		
	}
	
	@Override
	public void drawShadow(Graphics g){
		g.drawImage(paddleShadow, (int)x+8, (int)y+8, null);
		
	}
	
	public Rectangle getBounds() {

		return new Rectangle((int)x, (int)y, 32*2+8, 16);		
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.blue);

		//g.setColor(Color.green);

		g2d.drawImage(paddle, (int)x, (int)y, null);
		if(hasShoot) g2d.drawImage(turrets, (int)x, (int)y-8, null);
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
	
