package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class BlockBreak extends GameObject{
	
	private Handler handler;
	
	private GameObject player;
	
	private BufferedImage block;
	private BufferedImage blockShadow;
	
	private String type;
	private Color randCol;
	private Random r = new Random();
	private Color colo;
	private int powerUp;
	private int powerChance;
	private int powerChanceSpec;
	
	public BlockBreak(float x, float y, ID id, String typ, Color col, Handler handler) { //1= L, 2 = down, 3 = R, 4 = up, 5 = L/DOWN, 6 = R/DOWN 7= UP/L, 8= UP/R
		super(x, y, id);
		this.handler = handler;
	
		this.colo = col;
		this.type = typ;
		BufferedImageLoader loader = new BufferedImageLoader();
		
		blockShadow = loader.loadImage("/blockShadow.png");
		HUD.blocksToBreak++;
		//powerUp = r.nextInt()
		powerChance = r.nextInt(4); //overall chance of powerup
		powerChanceSpec = r.nextInt(2); //chance of picking specific powerup
		
		/*
		int temp = r.nextInt(4);
		if(temp == 0) randCol = Color.blue;
		else if(temp == 1) randCol = Color.yellow;
		else if(temp == 2) randCol = Color.red;
		else if(temp == 3) randCol = Color.green;
		*/
	}

	public void tick() {
		
		
		collision();
		
		
	}
	private void collision(){

		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.PlayerBall || tempObject.getId() == ID.PlayerBallClone){
					
				if(getBounds().intersects(tempObject.getBounds())){
					//tempObject.setVelX(velX);
					//tempObject.setVelY(velY);
					//AudioPlayer.getSound("breakBlock").play(1, 23);
					if(powerChance == 0){
						if(powerChanceSpec == 0) handler.addObject(new PowerUp(x, y, ID.PowerUp, "shot", handler));
						else if(powerChanceSpec == 1) handler.addObject(new PowerUp(x, y, ID.PowerUp, "mball", handler));
					}
					
					
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
		
		g2d.setColor(colo);
		g2d.fillRect((int)x, (int)y, 32+8, 18);
		
		g2d.setColor(Color.black);
		g2d.drawRect((int)x, (int)y, 32+8, 18);
		//g.setColor(Color.green);

		//g.drawImage(block, (int)x, (int)y, null);

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
	
