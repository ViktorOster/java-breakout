package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class PlayerBullet extends GameObject{
	
	private Handler handler;
	
	
	private BufferedImage bullet;
	private BufferedImage bulletShadow;
	
	private Random r = new Random();
	
	private GameObject playerBall;
	
	public PlayerBullet(float x, float y, ID id, Handler handler) { //1= L, 2 = down, 3 = R, 4 = up, 5 = L/DOWN, 6 = R/DOWN 7= UP/L, 8= UP/R
		super(x, y, id);
		this.handler = handler;
	
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		bulletShadow = loader.loadImage("/bulletShadow.png");
	    bullet = loader.loadImage("/playerBullet.png");
		
		
		velY = -12;
		
	}

	public void tick() {
		y += velY;
		
		collision();
		if (y < 0) handler.removeObject(this);	
		
		
	}
	private void collision(){

		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Block){
				
				if(getBounds().intersects(tempObject.getBounds())){
					
					
					handler.removeObject(this);
					
				} 
			}
			
			if(tempObject.getId() == ID.BlockBreak){
				
				if(getBounds().intersects(tempObject.getBounds())){
					
					handler.addObject(new Splash(tempObject.getX(), tempObject.getY(), ID.Splash, handler));
					handler.removeObject(tempObject);
					HUD.points +=100;
					AudioPlayer.getSound("breakBlock").play(1, 23);
					HUD.blocksToBreak--;
					handler.removeObject(this);
					
				} 
			}
			
		}		
	}
	
	@Override
	public void drawShadow(Graphics g){
		g.drawImage(bulletShadow, (int)x+8, (int)y+8, null);
		
	}
	
	public Rectangle getBounds() {

		return new Rectangle((int)x, (int)y, bullet.getWidth(), bullet.getHeight());		
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		//g2d.setColor(Color.black);
		//g2d.fillRect((int)x, (int)y, 32, 16);
		
		g.drawImage(bullet, (int)x, (int)y, null);

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
	
