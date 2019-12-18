package characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import helpers.Collision;
import helpers.WindowSize;

public class Enemy2 {

	public static final int speed = 150;
	public static final int enemyWidth = 29;
	public static final int enemyHeight = 29;
	private static Texture texture;
	
	float x, y;
	
	public boolean remove = false;
	public Collision collision;
	
	public Enemy2 (float x) {
		this.x = x;
		this.y = WindowSize.height;
	
		collision = new Collision(x, y, enemyWidth, enemyHeight);
		
		if (texture == null)
			texture = new Texture("Enemy2.png");
	}
	
	public void update (float delta) {
		
		y -= speed * delta;
		
		if (y < 160)
			remove = true;

		collision.Move(x, y);
	}
	
	public void render (SpriteBatch batch) {
		batch.draw(texture, x, y, enemyWidth, enemyHeight);
	}
	
	public float getX () {
		return x;
	}
	
	public float getY () {
		return y;
	}
	
	public Collision getCollision(){
		
		return collision;
	}
}
