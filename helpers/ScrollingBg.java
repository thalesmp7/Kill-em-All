package helpers;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class ScrollingBg {
	
	
	Texture image;
	float y1, y2;
	int speed;

	
	public ScrollingBg () {
		image = new Texture("stars.png");
		
		y1 = 0;
		y2 = image.getHeight();
		speed = 200;
	}
	
	public void updateAndRender (float deltaTime, SpriteBatch batch) {
		//Speed adjustment to reach goal
		
			y1 -= speed * deltaTime;
			y2 -= speed * deltaTime;
			
		
		
		//if image reached the bottom of screen and is not visible, put it back on top
		if (y1 + image.getHeight() < 160)
			y1 = y2 + image.getHeight();
		
		if (y2 + image.getHeight() < 160)
			y2 = y1 + image.getHeight();
		
		if(y1 < 160)
			y1 = WindowSize.height / 4;
		
		if(y2 < 160)
			y2 = WindowSize.height / 3;
		
		//Render
		batch.draw(image, 0, y1, WindowSize.width, image.getHeight());
		
		batch.draw(image, 0, y2, WindowSize.width, image.getHeight());
	}
	

}
