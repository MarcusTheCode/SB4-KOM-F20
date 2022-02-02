package dk.sdu.mmmi.cbse.gamestates;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.cbse.entities.Bullet;
import dk.sdu.mmmi.cbse.entities.Enemy;
import dk.sdu.mmmi.cbse.entities.Player;
import dk.sdu.mmmi.cbse.managers.GameKeys;
import dk.sdu.mmmi.cbse.managers.GameStateManager;

import java.util.ArrayList;

public class PlayState extends GameState {
	
	private ShapeRenderer sr;
	
	private Player player;

	private Enemy enemy;

	private ArrayList<Bullet> bullets;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		
		sr = new ShapeRenderer();
		
		player = new Player();
		enemy= new Enemy();
		bullets = new ArrayList<>();
		
	}
	
	public void update(float dt) {
		
		handleInput();
		
		player.update(dt);

		enemy.update(dt);

		for(int i = 0; i< bullets.size(); i++){
			bullets.get(i).update(dt);
			if(bullets.get(i).shouldRemove()){
				bullets.remove(i);
				i--;
			}
		}


//		for (Bullet bullet: bullets) {
//			if(bullet.shouldRemove()){
//				bullets.remove(bullet);
//			}else {
//				bullet.update(dt);
//			}
//		}
		
	}
	
	public void draw() {
		player.draw(sr);
		enemy.draw(sr);
		bullets = enemy.getBullets();
		for (Bullet bullet: bullets) {
			bullet.draw(sr);
		}
	}
	
	public void handleInput() {
		player.setLeft(GameKeys.isDown(GameKeys.LEFT));
		player.setRight(GameKeys.isDown(GameKeys.RIGHT));
		player.setUp(GameKeys.isDown(GameKeys.UP));
	}
	
	public void dispose() {}
	
}









