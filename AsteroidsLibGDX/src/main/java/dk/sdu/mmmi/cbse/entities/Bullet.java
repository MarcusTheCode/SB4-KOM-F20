package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Bullet extends SpaceObject{

    private float lifeTime;
    private float lifeTimer;

    private boolean remove;

    Bullet() {
        width = height = 2;
        lifeTimer = 0;
        lifeTime = 5;

    }

    public void setCords(float x, float y, float radians, float dx, float dy){
        this.x = x;
        this.y = y;
        this.radians = radians;
        this.dx = dx+50;
        this.dy = dy+50;
    }

    public boolean shouldRemove(){
        return  remove;
    }

    public void update(float dt) {

        updateRotationSpeed(dt);
        wrap();

        updateLifeTime(dt);
    }

    private void updateRotationSpeed(float dt) {
        x += dx * dt;
        y += dy * dt;
    }


    private void updateLifeTime(float dt) {
        // How long time the bullet is supposed to live
        lifeTimer += dt;
        if (lifeTimer > lifeTime) {
            remove = true;
        }
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(0, 1, 0, 1);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.circle(x - width / 2, y - height / 2, width / 2);
        shapeRenderer.end();
    }


}
