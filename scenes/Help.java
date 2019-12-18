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

public class Help implements Screen {

	BitmapFont font;
	
	MyGdxGame game;
	
	Texture background;
	Texture back;
	
	Help helpAux;
	
	private Music music = Gdx.audio.newMusic(Gdx.files.internal("Trabalho POO.ogg"));
	
	public Help(final MyGdxGame game){
		
		font = new BitmapFont();
		this.game = game;
		background = new Texture("helpScreen.png");
		back = new Texture("backHelp.png");
		
		if(Menu.soundState ==  true){
			music.setLooping(true);
			music.play();
		}
		
		helpAux = this;
		
		Gdx.input.setInputProcessor(new InputAdapter() {
			
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				
				
				if(game.camera.getInput().x < 10 + back.getWidth() && game.camera.getInput().x > 10  && WindowSize.height - game.camera.getInput().y < 10 + back.getHeight() && WindowSize.height - game.camera.getInput().y > 10){
					 
					helpAux.dispose();
					helpAux.music.stop();
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
		
		font.draw(game.getBatch(), "Os cristais representam sua vida. \nCaso entre em contato com um inimigo perderá uma vida. \nCaso entre em contato com três inimigos o jogo terminará." , 10, 700);
		font.draw(game.getBatch(), "Há três tipos de inimigos. \nO primeiro inimigo move-se em sentido vertical de cima para baixo. \n e aumenta a pontuação em dez pontos quando derrotado.", 10, 600);
		font.draw(game.getBatch(), "O segundo tipo de inimigo move-se na diagonal, \ncomeçado da esquerda. \nQuando derrotado a pontuação é aumentada em 10", 10, 500);
		font.draw(game.getBatch(), "O terceiro tipo de inimigo também move-se na diagonal porém \ncomeça do lado direito. \nTambém aumenta a pontuação em dez pontos quando derrotado.", 10, 400);
		font.draw(game.getBatch(), "Quando se derrota um inimigo há uma chance de 20% de ele deixar um \nícone de power-up. \nCaso entre-se em contado com este ítem as capacidades do seu tiro \nserão aumentadas. \nO tiro pode ser evoluído em 2 níveis.", 10, 300);
		font.draw(game.getBatch(), "As setas são usadas para locomoção e a mira para o ataque.", 10, 180);
		
		game.getBatch().draw(back, 10, 10);
		
		
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
