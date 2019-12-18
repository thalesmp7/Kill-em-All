package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.MyGdxGame;

import helpers.WindowSize;


public class GameOver implements Screen {
	
	MyGdxGame game;
	
	int score;
	
	Texture background;
	Texture back;
	
	GameOver gameOverAux;
	
	private Music music = Gdx.audio.newMusic(Gdx.files.internal("Strings + Flauta + Baixo + Piano + Cravo + Trompete.ogg"));
	
	BitmapFont font;


	public GameOver(final MyGdxGame game, int score){
		
		this.game = game;
		this.score = score;
		
		background = new Texture("gameOver.png");
		back = new Texture("Home.png");
		
		font = new BitmapFont();
		
		if(Menu.soundState ==  true){
			music.setLooping(true);
			music.play();
		}
		
		gameOverAux = this;

		Gdx.input.setInputProcessor(new InputAdapter() {
			
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				
				if(game.camera.getInput().x < 227  + back.getWidth() && game.camera.getInput().x > 227  && WindowSize.height - game.camera.getInput().y < 297 + back.getHeight() && WindowSize.height - game.camera.getInput().y > 297){
					
					gameOverAux.music.stop();
					gameOverAux.dispose();
					game.setScreen(new Menu(game));
				}
				
			
				
				
				return super.touchUp(screenX, screenY, pointer, button);
			}
			
		});
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.getBatch().begin();
		
		game.getBatch().draw(background, 0, 0);
		game.getBatch().draw(back, WindowSize.width / 2 + 50 - back.getWidth(), WindowSize.height / 2 - back.getHeight());
		
		font.draw(game.getBatch(), "Score: " + this.score , 200, 70);
		
		
		game.getBatch().end();
		
		

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
