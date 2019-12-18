package helpers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Camera {

	private OrthographicCamera camera;
	private StretchViewport viewport;
	
	public Camera(int x, int y){
		
		camera = new OrthographicCamera();
		viewport = new StretchViewport(x, y, camera);
		viewport.apply();
		camera.position.set(x / 2, y / 2, 0);
		camera.update();
		
	}//fim construtor
	
	public Matrix4 combined(){// retorna matriz da camera
		
		return camera.combined;
	}//fim Matrix4
	
	public void UpdateViewport(int x, int y){//atualiza viewport
		
		viewport.update(x, y);
		
	}//fim UpdateViewport

	
	//Recebe input
	public Vector2 getInput() {
		Vector3 inputScreen = new Vector3(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 0);
		Vector3 unprojected = camera.unproject(inputScreen);
		return new Vector2(unprojected.x, unprojected.y);
}
	
}// fim Camera
