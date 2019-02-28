package game;

import java.awt.Color;


import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import game.Game.STATE;

public class PlayerHandler extends MouseAdapter implements MouseListener {
	private Game game;
	private Handler handler;
	private int mx;
	private int my;
	//private GameObject turret;
	
	private AffineTransform at;
	private BufferedImage paddleImg;


    private PlayerBall playerBall;
    private PlayerPaddle playerPaddle;
    private int canShoot;
    private int shootCount;

	public PlayerHandler(Game game, Handler handler){
		
		this.game = game;
		this.handler = handler;
		
		
		BufferedImageLoader loader = new BufferedImageLoader();
	
		paddleImg = loader.loadImage("/paddle.png");
		
		//at = AffineTransform.getTranslateInstance(384, 512);
	
		
		//turret = new Turret(384, 512, ID.Turret, handler);
		//handler.addObject(turret);
		
		
		
		playerPaddle = new PlayerPaddle(game.WIDTH/2, 528, ID.PlayerPaddle, this, handler);
		playerBall = new PlayerBall(game.WIDTH/2+32, 508, ID.PlayerBall, this, handler);
		handler.addObject(playerBall);
		handler.addObject(playerPaddle);
	}
	
	
	public void mousePressed(MouseEvent e){
				
		mx = e.getX();
		my = e.getY();
		playerBall.released = true;
		
		if(PlayerPaddle.hasShoot){
			
			if(shootCount >15) {
				
				shootBullet();
				
			}
		}
	
	}
	@Override
	public void mouseMoved(MouseEvent e){
		mx = e.getX();
		my = e.getY();
		
		
	}
	public void shootBullet(){
		handler.addObject(new PlayerBullet(playerPaddle.getX()+8, 508, ID.PlayerBullet, handler));
		handler.addObject(new PlayerBullet(playerPaddle.getX()+56, 508, ID.PlayerBullet, handler));
		AudioPlayer.getSound("shoot").play(1, 23);
		shootCount = 0;
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			} else return false;
		} else return false;
	}
	
	public void tick(){
		if(PlayerPaddle.hasShoot){
			shootCount++;
		}
		//transformDeg = (mx/4)-90;
		
	
		//Game.clamp(transformDeg, -90, 90);
		
		
		//0 - 768
		
		//turret.setX(mx);
		//turret.setY(my);
	}
	public float getMX(){
		return mx;
	}
	public float getMY(){
		return my;
	}
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.white);
		
		
	}

	
}
