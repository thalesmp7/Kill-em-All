package scenes;

import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.MyGdxGame;
import Itens.Bullets;
import Itens.PowerUp;
import characters.Enemy;
import characters.Enemy2;
import characters.Enemy3;
import characters.Player;
import helpers.Collision;
import helpers.Controls;
import helpers.Life;
import helpers.WindowSize;



public class GameScreen implements Screen{
	
	//*******VARIAVEIS*****************
	private static final int leftWidth = 30;
	private static final int leftHeight = 40;
	private static final int downWidth = 83;
	private static final int downHeight = 20;
	private static final int rightWidth = 136;
	private static final int rightHeight = 40;
	private static final int upWidth = 83;
	private static final int upHeight = 73;
	private static final int attackWidth = 323;
	private static final int attackHeight = 20;
	private static final int speed = 200;
	private static final float bulletWait = 0.2f;
	private static final float minEnemyTime = 3.2f;
	private static final float maxEnemyTime = 4.0f;
	private static final float minEnemy2Time = 0.7f;
	private static final float maxEnemy2Time = 1.2f;
	private static final float minEnemy3Time = 3.4f;
	private static final float maxEnemy3Time = 4.2f;
	
	private MyGdxGame game;
	private Texture background;
	private Texture pw;
	private Music music = Gdx.audio.newMusic(Gdx.files.internal("Space Sample.ogg"));
	private Player player;
	private Controls control;

	private float auxy = 0;
	private float auxx = 0;
	public float auxPlayerx = 0;
	private float auxPlayery = 0;
	private float bulletTimer;
	private float enemyTimer;
	private float enemy2Timer;
	private float enemy3Timer;
	private int powerUpChance;
	private int powerUpChanceAux;
	private int score = 0;
	private int pwExists = 0;
	private float pwX;
	private float pwY;
	private int pwState = 0;
	CharSequence scoreString = "Score: ";
	CharSequence scoreStringAux;
	
	
	Collision playerCollision;
	Random random;
	Life life;
	PowerUp powerUp;
	Bullets bullet;
	BitmapFont font;
	

	ArrayList<Bullets> bullets;
	ArrayList<Bullets> bulletsRemove;
	ArrayList<Enemy> enemies;
	ArrayList<Enemy> enemyRemove;
	ArrayList<Enemy2> enemies2;
	ArrayList<Enemy2> enemyRemove2;
	ArrayList<Enemy3> enemies3;
	ArrayList<Enemy3> enemyRemove3;
	ArrayList<PowerUp> powerUps;
	ArrayList<PowerUp> powerUpsRemove;
	
	//*********FIM*VARIAVEIS**************
	
	public GameScreen(final MyGdxGame game){
		
		this.game = game;
		
		background = new Texture("SpaceBG.png");
		player = new Player();
		control = new Controls();
		random = new Random();
		
		
		bullets = new ArrayList<Bullets>();
		bulletsRemove = new ArrayList<Bullets>();
		enemies = new ArrayList<Enemy>();
		enemyRemove = new ArrayList<Enemy>();
		enemies2 = new ArrayList<Enemy2>();
		enemyRemove2 = new ArrayList<Enemy2>();
		enemies3 = new ArrayList<Enemy3>();
		enemyRemove3 = new ArrayList<Enemy3>();
		powerUps = new ArrayList<PowerUp>();
		powerUpsRemove = new ArrayList<PowerUp>();
		
		if(Menu.soundState ==  true){
			music.setLooping(true);
			music.play();
		}
		
		playerCollision = new Collision(0, 0, player.getWidth(), player.getHeight());
		life = new Life();
		font = new BitmapFont();

		
		bulletTimer = 0;
		enemyTimer = random.nextFloat() * (maxEnemyTime - minEnemyTime) + minEnemyTime;
		enemy2Timer = random.nextFloat() * (maxEnemy2Time - minEnemy2Time) + minEnemy2Time;
		enemy3Timer = random.nextFloat() * (maxEnemy3Time - minEnemy3Time) + minEnemy3Time;
		
	}
	
	
	
	
	@Override
	public void show() {
		
		
	}
	

	@Override
	public void render(float delta) {
		
		scoreStringAux = "" +score;
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//******************MOVIMENTO***********************************************
		//Cima
		if(game.camera.getInput().x < upWidth + control.getArrowWidth() && game.camera.getInput().x > upWidth  && WindowSize.height - game.camera.getInput().y < upHeight + control.getArrowHeight() && WindowSize.height - game.camera.getInput().y > upHeight){
			 auxy += speed * Gdx.graphics.getDeltaTime();
		}
		
		//baixo
		if(game.camera.getInput().x < downWidth + control.getArrowWidth() && game.camera.getInput().x > downWidth  && WindowSize.height - game.camera.getInput().y < downHeight + control.getArrowHeight() && WindowSize.height - game.camera.getInput().y > downHeight){
			 auxy -= speed * Gdx.graphics.getDeltaTime();
		}
		
		//direita
		if(game.camera.getInput().x < rightWidth + control.getArrowWidth() && game.camera.getInput().x > rightWidth  && WindowSize.height - game.camera.getInput().y < rightHeight + control.getArrowHeight() && WindowSize.height - game.camera.getInput().y > rightHeight){
			 auxx += speed * Gdx.graphics.getDeltaTime();
		}
		
		//esquerda
		if(game.camera.getInput().x < leftWidth + control.getArrowWidth() && game.camera.getInput().x > leftWidth  && WindowSize.height - game.camera.getInput().y < leftHeight + control.getArrowHeight() && WindowSize.height - game.camera.getInput().y > leftHeight){
			 auxx -= speed * Gdx.graphics.getDeltaTime();
		}
		//***************FIM*MOVIMENTO******************************************

		//******TIRO************************************************************
		if(game.camera.getInput().x < attackWidth + control.getAttackWidth() && game.camera.getInput().x > attackWidth  && WindowSize.height - game.camera.getInput().y < attackHeight + control.getAttackHeight() && WindowSize.height - game.camera.getInput().y > attackHeight){
			
			
			bulletTimer += delta;
			if(bulletTimer > bulletWait){
				bulletTimer = 0;
				bullets.add(new Bullets(auxPlayerx, auxPlayery + player.getHeight() / 2 ));
				
			}
			
			for(Bullets bullet : bullets){
				
				bullet.Update(delta);
				if(bullet.remove)
					bulletsRemove.add(bullet);
			}
			
		}
		
		//*********FIM*TIRO*****************************************************
		
		//*******INIMIGO********************************************************
		
		enemyTimer -= delta;
		enemy2Timer -= delta;
		enemy3Timer -= delta;
		
		if(enemyTimer <= 0){
			enemyTimer = random.nextFloat() * (maxEnemyTime - minEnemyTime) + minEnemyTime;
			enemies.add(new Enemy(0, random.nextInt(WindowSize.height - WindowSize.height / 4 + 160)));
		}
		
		if(enemy2Timer <= 0){
			
			enemy2Timer = random.nextFloat() * (maxEnemy2Time - minEnemy2Time) + minEnemy2Time;
			enemies2.add(new Enemy2(random.nextInt(WindowSize.width - Enemy2.enemyWidth)));
		}
		
		if(enemy3Timer <= 0){
			
			enemy3Timer = random.nextFloat() * (maxEnemy3Time - minEnemy3Time) + minEnemy3Time;
			enemies3.add(new Enemy3(480, random.nextInt(WindowSize.height - WindowSize.height / 4 + 160)));
		}
		
		for(Enemy enemy : enemies){
			
			enemy.update(delta);
			
			if(enemy.remove)
				enemyRemove.add(enemy);
			
		}
		for(Enemy2 enemy : enemies2){
			
			enemy.update(delta);
			
			if(enemy.remove)
				enemyRemove2.add(enemy);
		}
		
		for(Enemy3 enemy : enemies3){
			
			enemy.update(delta);
			
			if(enemy.remove)
				enemyRemove3.add(enemy);
		}
		
		enemies.removeAll(enemyRemove);
		enemies2.removeAll(enemyRemove2);
		enemies3.removeAll(enemyRemove3);
		//*********FIM*INIMIGO**************************************************
		
		//Movimento aux para que o detector de colisao acompanhe o jogador
		auxPlayerx = WindowSize.width / 2 - player.getWidth() / 2 + auxx;
		auxPlayery = 160 + auxy;
		if(auxPlayerx > 450)
			auxPlayerx = 450;
		if(auxPlayerx < 0)
			auxPlayerx = 0;
		if(auxPlayery > 695)
			auxPlayery = 695;
		if(auxPlayery < 160)
			auxPlayery = 160;
		//Trata colisão com bordas
		
		//*******COLISAO*******************************
		//o detector de colisao deve acompanhar o jogador
		playerCollision.Move(auxPlayerx, auxPlayery);
		
		for(Bullets bullet : bullets){
			
			for(Enemy enemy : enemies){
				
				if(bullet.getCollision().Collides(enemy.getCollision())){
					
					score += 10;
					
					powerUpChance = random.nextInt(5);
					powerUpChanceAux = random.nextInt(5);
					
					if(powerUpChance == powerUpChanceAux){
						
						powerUps.add(new PowerUp(enemy.getX(), enemy.getY()));
						
						pwExists = 1;
						
					}
					bulletsRemove.add(bullet);
					enemyRemove.add(enemy);
				}
			}
			
			for(Enemy2 enemy : enemies2){
				
				if(bullet.getCollision().Collides(enemy.getCollision())){
					
					score += 5;
					
					powerUpChance = random.nextInt(5);
					powerUpChanceAux = random.nextInt(5);
					
					if(powerUpChance == powerUpChanceAux){
						
						powerUps.add(new PowerUp(enemy.getX(), enemy.getY()));
						
						pwExists = 1;
					}
					bulletsRemove.add(bullet);
					enemyRemove2.add(enemy);
				}
			}
			
			for(Enemy3 enemy : enemies3){
				
				if(bullet.getCollision().Collides(enemy.getCollision())){
					
					score += 10;
					
					powerUpChance = random.nextInt(5);
					powerUpChanceAux = random.nextInt(5);
					
					if(powerUpChance == powerUpChanceAux){
						
						powerUps.add(new PowerUp(enemy.getX(), enemy.getY()));
						
						pwExists = 1;
					}
					bulletsRemove.add(bullet);
					enemyRemove3.add(enemy);
				}
			}
		}
		bullets.removeAll(bulletsRemove);
		
		//COLISAO*INIMIGOS*********************************************
		for(Enemy enemy : enemies){
			
			if(enemy.getCollision().Collides(playerCollision)){
				
				pwState = 0;
				
				enemyRemove.add(enemy);
				
				life.setState(life.getState() - 1);
				
				if(life.getState() == 0){
					
					this.dispose();
					game.setScreen(new GameOver(game, score));
					return;
				}
			}
		}
		enemies.removeAll(enemyRemove);
		
		for(Enemy2 enemy : enemies2){
			
			if(enemy.getCollision().Collides(playerCollision)){
				
				pwState = 0;
				
				enemyRemove2.add(enemy);
				
				life.setState(life.getState() - 1);
				
				if(life.getState() == 0){
					
					this.dispose();
					game.setScreen(new GameOver(game, score));
					return;
				}
				
			}
		}
		enemies2.removeAll(enemyRemove2);
		
		for(Enemy3 enemy : enemies3){
			
			if(enemy.getCollision().Collides(playerCollision)){
				
				pwState = 0;
				
				enemyRemove3.add(enemy);
				
				life.setState(life.getState() - 1);
				
				if(life.getState() == 0){
					
					this.dispose();
					game.setScreen(new GameOver(game, score));
					return;
				}
				
			}
		}
		enemies3.removeAll(enemyRemove3);
		
		//COLISAO*POWER-UP*******************************
		for(PowerUp powerUp : powerUps){
			
			if(powerUp.getCollision().Collides(playerCollision)){
				
				if(pwState == 0)
					pwState = 1;
				
				else if(pwState == 1)
					pwState = 2;
				
				powerUpsRemove.add(powerUp);
			}
		}
		powerUps.removeAll(powerUpsRemove);
		//**********FIM*COLISAO************************

		game.getBatch().begin();
		
		
		game.getBatch().draw(background, 0, 160);
		game.scrollingBg.updateAndRender(delta, game.getBatch());
		life.update(game.getBatch());
		font.draw(game.getBatch(), scoreString, 220, 120);
		font.draw(game.getBatch(), scoreStringAux, 220, 100);
		
		game.getBatch().draw(player.getPlayer(), auxPlayerx, auxPlayery);
		game.getBatch().draw(control.getLeft(), leftWidth, leftHeight);
		game.getBatch().draw(control.getDown(), downWidth, downHeight);
		game.getBatch().draw(control.getRight(), rightWidth, rightHeight);
		game.getBatch().draw(control.getUp(), upWidth, upHeight);
		game.getBatch().draw(control.getAttack(), attackWidth, attackHeight);
		
		if(bullets != null){
			for(Bullets bullet : bullets){
				bullet.Update(delta);
				bullet.Render(game.getBatch());
				
				if(pwState == 1 || pwState == 2){
					game.getBatch().draw(bullet.getTexture(), bullet.getX() + 10, bullet.getY());
					game.getBatch().draw(bullet.getTexture(), bullet.getX() - 10, bullet.getY());
				}
				
				if(pwState == 2){
					game.getBatch().draw(bullet.getTexture(), bullet.getX() + 20, bullet.getY());
					game.getBatch().draw(bullet.getTexture(), bullet.getX() - 20, bullet.getY());
				}
			}
		}
		for(Enemy enemy : enemies)
			enemy.render(game.getBatch());
		for(Enemy2 enemy : enemies2)
			enemy.render(game.getBatch());
		for(Enemy3 enemy : enemies3)
			enemy.render(game.getBatch());
		
		for(PowerUp powerUp: powerUps){
			
			if(pwExists == 1){
				pw = powerUp.getTexture();
				pwX = powerUp.getX();
				pwY = powerUp.getY();
				game.getBatch().draw(pw, pwX, pwY);
			}
		}
		
		game.getBatch().end();
		

		
	}
	

	@Override
	public void resize(int width, int height) {
		
		
		
	}

	@Override
	public void pause() {
		
		
	}

	@Override
	public void resume() {
		
		
	}

	@Override
	public void hide() {
		
		
	}

	@Override
	public void dispose() {
		
		background.dispose();
		music.dispose();
		
	}

}
