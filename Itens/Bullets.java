package Itens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import helpers.Collision;
import helpers.WindowSize;

public class Bullets {

	public static final int speed = 400;
	public static final int bulletWidth = 17;
	public static final int bulletHeight = 17;
	private static Texture texture;
	
	
	float x, y;
	int pwState = 0;
	public boolean remove = false;
	
	public Collision collision;
	

	
	public Bullets(float x, float origem){
		
		this.x = x;
		this.y = origem;
		
		collision = new Collision(x, y, bulletWidth, bulletHeight);
		
		if(texture == null)
			texture = new Texture("bullet.png");
	}
	
	public void Update(float delta){
		
		
		y += speed * delta;
		
		if(y > WindowSize.height)
			remove = true;
		
		collision.Move(x, y);
	}
	
	public void Render(SpriteBatch batch){
		
		batch.draw(texture, x, y);
		
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
	
	public Texture getTexture(){
		
		return this.texture;
	}

}
