package game;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import game.Game.STATE;

public class Handler {

	private int deadTimer = 0;
	static boolean finishedLvl = false;
	private GameObject ball;
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick(){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			if(tempObject.getId() == ID.PlayerBall){
				ball = tempObject;
				
				
			}	
		}
		
		
		
		
		if(HUD.TIME <= 0){
			
			while(HUD.TIME <= 0){
				String input = JOptionPane.showInputDialog("Time's up, play again? (Y/N)");
				if(input.equals("Y")){
					removeObject(ball);
					clearEverything();
					Game.gameState = STATE.Game;
				} else System.exit(0);
				
			}
			
		} 
	 
		
		for(int i = 0; i < object.size(); i++){
			
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}			
	}
	
	public void render(Graphics g){

		for(int i = 0; i < object.size(); i++){
			
			
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}	
		
	}
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}

	public void clearEnemies(){
		object.clear();
	}
	
	public void clearEverything(){
		//object.clear();
		//Menu menu = new Menu(game, handler);
		for(int i = 0; i < object.size(); i++){
			
			
			GameObject tempObject = object.get(i);
			if(tempObject.getId() == ID.PlayerBallClone){
				object.remove(tempObject);
				
				
			}
			
		}	
		//HUD.clearScore();
		finishedLvl = false;
		PlayerPaddle.hasShoot = false;
		
		Game.gameState = STATE.Game;
		
		//addObject(new Ball(100, 100, ID.Ball, this));
		//handler.addObject(new Player(70, 150, ID.Player, handler));
		
		//AudioPlayer.getMusic("music").stop();
		//AudioPlayer.getMusic("game_over").loop();
	}
	
}
