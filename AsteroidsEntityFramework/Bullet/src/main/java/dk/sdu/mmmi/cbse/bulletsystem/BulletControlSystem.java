package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletInt;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.LEFT;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.RIGHT;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.UP;
import static java.lang.Math.*;

import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;

/**
 *
 * @author jcs
 */
public class BulletControlSystem implements IEntityProcessingService, BulletInt{

    @Override
    public void process(GameData gameData, World world) {

        for (Entity enemy : world.getEntities(Bullet.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);

            if(Math.random()>0.6) {
                movingPart.setLeft(true);
            }else {
                movingPart.setLeft(false);
            }

            if(Math.random()>0.6) {
                movingPart.setRight(true);
            }else {
                movingPart.setRight(false);
            }

            if(Math.random()>0.6) {
                movingPart.setUp(true);
            }else {
                movingPart.setUp(false);
            }
            
            
            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);

            updateShape(enemy);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 2);
        shapey[0] = (float) (y + Math.sin(radians) * 2);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 2);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 2);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 2);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 2);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 2);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 2);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

    @Override
    public Entity newBullet(Entity entity, GameData gameData) {
        PositionPart shooterPos = entity.getPart(PositionPart.class);
        MovingPart shooterMovingPart = entity.getPart(MovingPart.class);



        float x = shooterPos.getX();
        float y = shooterPos.getY();
        float radians = shooterPos.getRadians();
        float dt = gameData.getDelta();
        float speed = 350;

        Entity bullet = new Bullet();
        bullet.setRadius(2);

        float bx = (float) cos(radians) * entity.getRadius() * bullet.getRadius();
        float by = (float) sin(radians) * entity.getRadius() * bullet.getRadius();

        bullet.add(new PositionPart(bx + x, by + y, radians));
        //bullet.add(new LifePart(1));
        bullet.add(new MovingPart(0, 5000000, speed, 5));
        //bullet.add(new TimerPart(1));

        bullet.setShapeX(new float[2]);
        bullet.setShapeY(new float[2]);

        return bullet;
    }
}
