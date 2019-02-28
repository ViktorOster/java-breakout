package game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Splash extends GameObject {

	private Handler handler;
	
	private BufferedImage expl1, expl2, expl3;
	
	
	private int animCount = 0;
	private String type;

	public Splash(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

		
		BufferedImageLoader loader = new BufferedImageLoader();
	
		expl1 = loader.loadImage("/expl1.png");
		expl2 = loader.loadImage("/expl2.png");
		expl3 = loader.loadImage("/expl3.png");
		
		//alienTrail = loader.loadImage("/alienTrail.png");
	}

	@Override
	public void tick() {
		animCount++;
		if(animCount >10) handler.removeObject(this);
		
	}

	@Override
	public void render(Graphics g) {
		
		if(animCount >= 0 && animCount <= 4) g.drawImage(expl1, (int)x-10, (int)y-4, null);
		if(animCount >= 5 && animCount <= 7) g.drawImage(expl2, (int)x-10, (int)y-4, null);
		if(animCount >= 8 && animCount <= 10) g.drawImage(expl3, (int)x-10, (int)y-4, null);
		
		
		
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

	@Override
	public void drawShadow(Graphics g) {
		// TODO Auto-generated method stub
		
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
