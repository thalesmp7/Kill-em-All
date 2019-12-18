package helpers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Life {

	int state = 3;
	private static Texture texture1;
	
	
	public Life(){
		
		texture1 = new Texture("Life.png");
	}
	
	public void update(SpriteBatch batch){
		if(state == 3){
			batch.draw(texture1, 220, 50);
			batch.draw(texture1, 240, 50);
			batch.draw(texture1, 260, 50);
		}
		
		if(state == 2){
			
			batch.draw(texture1, 220, 50);
			batch.draw(texture1, 240, 50);
		}
		
		if(state == 1){
			
			batch.draw(texture1, 220, 50);
		}
	}
	
	public void setState(int state){
		
		this.state = state;
	}
	
	public int getState(){
		
		return this.state;
	}
}
