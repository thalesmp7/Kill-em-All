package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;

import helpers.WindowSize;

public class Menu implements Screen {

	MyGdxGame game;
	
	private Texture menu;
	private Texture play;
	private Texture help;
	private Texture quit;
	private Texture soundOn;
	private Texture soundOff;
	
	Menu menuAux;
	
	private Music music = Gdx.audio.newMusic(Gdx.files.internal("Cravo + Baixo + Strings + Calliope.ogg"));
	
	float x, y;
	
	public static boolean soundState = true;
	
	
	public Menu(final MyGdxGame game){
		
		menu = new Texture("Menu.png");
		play = new Texture("play.png");
		help = new Texture("Help.png");
		quit = new Texture("Quit.png");
		soundOn = new Texture("unmute.png");
		soundOff = new Texture("mute.png");
		
		menuAux = this;
		
		if(Menu.soundState ==  true){
			music.setLooping(true);
			music.play();
		}

		this.game = game;
		
		Gdx.input.setInputProcessor(new InputAdapter() {
			
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				//Exit button
				int x = WindowSize.width / 2 - quit.getWidth() / 2;
				if (game.camera.getInput().x < x + quit.getWidth() && game.camera.getInput().x > x && WindowSize.height - game.camera.getInput().y < 120 + quit.getHeight() && WindowSize.height - game.camera.getInput().y > 120) {
					
					Gdx.app.exit();
				}
				
				x = WindowSize.width / 2 - play.getWidth() / 2;
				if (game.camera.getInput().x < x + play.getWidth() && game.camera.getInput().x > x && WindowSize.height - game.camera.getInput().y < 320 + play.getHeight() && WindowSize.height - game.camera.getInput().y > 320) {
					
					menuAux.music.stop();
					menuAux.dispose();
					game.setScreen(new GameScreen(game));
				}
				
				x = WindowSize.width / 2 - help.getWidth() / 2;
				if (game.camera.getInput().x < x + help.getWidth() && game.camera.getInput().x > x && WindowSize.height - game.camera.getInput().y < 220 + help.getHeight() && WindowSize.height - game.camera.getInput().y > 220) {
					
					menuAux.music.stop();
					menuAux.dispose();
					game.setScreen(new Help(game));
				}
				
				if(game.camera.getInput().x < 400 + soundOn.getWidth() && game.camera.getInput().x > 400 && WindowSize.height - game.camera.getInput().y < 220 + soundOn.getHeight() && WindowSize.height - game.camera.getInput().y > 220){
					
					if(soundState == false){
						soundState = true;
						menuAux.music.play();
					}
					
					else if(soundState == true){
						soundState = false;
						menuAux.music.stop();
					}
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
		
		game.getBatch().draw(menu, 0, 0);
		game.getBatch().draw(play, WindowSize.width / 2 - play.getWidth() / 2, WindowSize.height / 2 - play.getHeight() / 2);
		game.getBatch().draw(help, WindowSize.width / 2 - help.getWidth() / 2, WindowSize.height / 2 - help.getHeight() / 2 - 100);
		game.getBatch().draw(quit, WindowSize.width / 2 - quit.getWidth() / 2, WindowSize.height / 2 - quit.getHeight() / 2 - 200);
		
		if(soundState == true){
			
			game.getBatch().draw(soundOn, 400, WindowSize.height / 2 - soundOn.getHeight() / 2 - 100);
		}
		
		else if(soundState == false){
			game.getBatch().draw(soundOff, 400, WindowSize.height / 2 - soundOff.getHeight() / 2 - 100);
		}
		
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
