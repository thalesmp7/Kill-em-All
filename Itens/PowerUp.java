package Itens;

import com.badlogic.gdx.graphics.Texture;


import helpers.Collision;

public class PowerUp {

	private static final int powerUpWidth = 29;
	private static final int powerUpHeight = 29;
	private static Texture texture;
	
	float x;
	float y;
	int state;
	
	
	public boolean remove = false;
	public int exists = 0;
	public Collision collision;
	
	public PowerUp(float x, float y){
		
		this.x = x;
		this.y = y;
		
		collision = new Collision(x, y, powerUpWidth, powerUpHeight);
		
		if(texture == null)
			texture = new Texture("PowerUp.png");
	}
	
	public Texture getTexture(){
		
		return this.texture;
	}
	
	public Collision getCollision(){
		
		return collision;
	}
	
	public float getX(){
		
		return this.x;
	}
	
	public float getY(){
		
		return this.y;
	}
	
}
