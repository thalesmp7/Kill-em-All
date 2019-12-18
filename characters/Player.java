package characters;

import com.badlogic.gdx.graphics.Texture;



public class Player {

	Texture name;
	public static float speed = 0.5f;
	public int playerWidth = 29;
	public int playerHeight = 27;
	
	public Player(){
		
		name = new Texture("Player.png");
		
	}// fim construtor
	
	public Texture getPlayer(){
		
		return this.name;
	}
	
	public int getWidth(){
		
		return playerWidth;
	}
	
	public int getHeight(){
		
		return playerHeight;
	}
	
}
