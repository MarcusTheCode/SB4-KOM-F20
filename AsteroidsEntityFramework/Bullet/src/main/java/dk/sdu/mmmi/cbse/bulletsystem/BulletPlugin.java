package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class BulletPlugin implements IGamePluginService {

    private Entity bullet;

    public BulletPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

//        // Add entities to the world
//        bullet = createBullet(gameData);
//        world.addEntity(bullet);
    }

//    private Entity createBullet(GameData gameData) {
//
//        float deacceleration = 0;
//        float acceleration = 300;
//        float maxSpeed = 300;
//        float rotationSpeed = 5;
//        float x = gameData.getDisplayWidth() / 3;
//        float y = gameData.getDisplayHeight() / 3;
//        float radians = 3.1415f / 2;
//
//        Entity bullet = new Bullet();
//        bullet.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
//        bullet.add(new PositionPart(x, y, radians));
//
//        return bullet;
//    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Bullet.class) {
                world.removeEntity(e);
            }
        }
    }

}
