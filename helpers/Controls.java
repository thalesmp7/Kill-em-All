package helpers;


import com.badlogic.gdx.graphics.Texture;

public class Controls {
	
	private Texture up;
	private Texture down;
	private Texture left;
	private Texture right;
	private Texture attack;
	
	public Controls(){
		
		up = new Texture("up.png");
		down = new Texture("down.png");
		left = new Texture ("left.png");
		right = new Texture ("right.png");
		attack = new Texture("attack.png");
		
	}//fim construtor

	public Texture getUp(){
		
		return this.up;
	}
	
	public Texture getDown(){
		
		return this.down;
	}
	
	public Texture getLeft(){
		
		return this.left;
	}
	
	public Texture getRight(){
		
		return this.right;
	}
	
	public int getArrowWidth(){

		return up.getWidth();
	}
	
	public int getArrowHeight(){
		
		return up.getHeight();
	}
	
	public Texture getAttack(){
		
		return this.attack;
	}
	
	public int getAttackWidth(){
		
		return attack.getWidth();
	}
	
	public int getAttackHeight(){
		
		return attack.getHeight();
	}
	
}
