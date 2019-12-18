package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import helpers.Camera;
import helpers.ScrollingBg;
import helpers.WindowSize;
import scenes.Menu;



public class MyGdxGame extends Game {
	
	private SpriteBatch batch;
	public Camera camera;
	public ScrollingBg scrollingBg;
	
	
	
	@Override
	public void create() {
		

		batch = new SpriteBatch();
		camera = new Camera(WindowSize.width, WindowSize.height);
		scrollingBg = new ScrollingBg();
		
		setScreen(new Menu(this));

	}

	@Override
	public void render() {
		
		batch.setProjectionMatrix(camera.combined());
		super.render();
	}
	
	@Override
	public void resize(int x, int y) {
		
		camera.UpdateViewport(x, y);
		super.resize(x, y);
	}
	

	public SpriteBatch getBatch(){
		
		return this.batch;
	}
	

}
