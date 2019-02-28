package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

public class Exit extends GameObject{
	
	private Handler handler;
	
	private int sizeX;
	private int sizeY;
	private GameObject player;
	
	private BufferedImage block;

	private String type;
	
	private int collideCount = 0;
	private boolean collide = false;
	
	
	public Exit(float x, float y, ID id, Handler handler) { //1= L, 2 = down, 3 = R, 4 = up, 5 = L/DOWN, 6 = R/DOWN 7= UP/L, 8= UP/R
		super(x, y, id);
		this.handler = handler;
		


	}

	public void tick() {

		collision();
		
	}
	private void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.Ball){
				if(getBounds().intersects(tempObject.getBounds())){	
					
					handler.removeObject(tempObject);
					handler.finishedLvl = true;
					AudioPlayer.getSound("golfCup").play(1, 23);
					//handler.removeObject(this);
				}
			}
		}
	}
	
	@Override
	public void drawShadow(Graphics g){
	
		
	}
	
	public Rectangle getBounds() {

		return new Rectangle((int)x, (int)y, 32, 32);		
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.red);

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
	
