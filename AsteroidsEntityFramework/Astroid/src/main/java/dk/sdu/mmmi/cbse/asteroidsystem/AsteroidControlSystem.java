package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

/**
 *
 * @author jcs
 */
public class AsteroidControlSystem implements IEntityProcessingService {

    int numPoints = 6;
    Random rnd = new Random(10);
    float angle = 0;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity enemy : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);

            if(Math.random()>0.9) {
                movingPart.setUp(true);
            }else {
                movingPart.setUp(false);
            }
            
            
            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);

            updateShape(enemy);
        }
    }

    private void updateShape(Entity asteroid) {
        PositionPart positionPart = asteroid.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        float[] shapex = new float[6];
        float[] shapey = new float[6];
        Asteroid asAsteroid = (Asteroid) asteroid;
        //       SplitterPart splitter = asAsteroid.getPart(SplitterPart.class);
        if (asAsteroid.getSize().equals("LARGE")) {
            for (int i = 0; i < numPoints; i++) {
                shapex[i] = x + (float) Math.cos(angle + radians) * 26;
                shapey[i] = y + (float) Math.sin(angle + radians) * 26;
                angle += 2 * 3.1415f / numPoints;
            }
        }
        if (asAsteroid.getSize().equals("MEDIUM")) {
            for (int i = 0; i < numPoints; i++) {
                shapex[i] = x + (float) Math.cos(angle + radians) * 16;
                shapey[i] = y + (float) Math.sin(angle + radians) * 16;
                angle += 2 * 3.1415f / numPoints;
            }
        }
        if (asAsteroid.getSize().equals("SMALL")) {
            for (int i = 0; i < numPoints; i++) {
                shapex[i] = x + (float) Math.cos(angle + radians) * 26;
                shapey[i] = y + (float) Math.sin(angle + radians) * 26;
                angle += 2 * 3.1415f / numPoints;
            }
        }


        asteroid.setShapeX(shapex);
        asteroid.setShapeY(shapey);
    }
}
