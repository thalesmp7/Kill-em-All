package helpers;

public class Collision {

	float x;
	float y;
	int w;
	int h;
	
	public Collision(float x, float y, int w, int h){
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public void Move(float x, float y){
		
		this.x = x;
		this.y = y;
	}
	
	public boolean Collides(Collision collision){
		
		return x < collision.x + collision.w && y < collision.y + collision.h && x + w > collision.x && y + h > collision.y;
	}
}
