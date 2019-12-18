package characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import helpers.Collision;

public class Enemy3 {
	public static final int speed = 300;
	public static final int enemyWidth = 29;
	public static final int enemyHeight = 29;
	private static Texture texture;
	
	float x, y;
	
	public boolean remove = false;
	public Collision collision;
	
	public Enemy3 (float x, float y) {
		
		this.x = x;
		this.y = y;
		
		collision = new Collision(x, y, enemyWidth, enemyHeight);
		
		if (texture == null)
			texture = new Texture("Enemy3.png");
	}
	
	public void update (float delta) {
		
		x -= speed * delta;
		y -= speed * delta;
		
		if (y < 160)
			remove = true;

		if(x < 0)
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
