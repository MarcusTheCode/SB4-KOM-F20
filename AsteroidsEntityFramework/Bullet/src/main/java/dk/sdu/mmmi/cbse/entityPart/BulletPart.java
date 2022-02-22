package dk.sdu.mmmi.cbse.entityPart;

import dk.sdu.mmmi.cbse.bulletsystem.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.EntityPart;

public class BulletPart implements EntityPart {
    public void create(World world){
        world.addEntity(new Bullet());
    }

    @Override
    public void process(GameData gameData, Entity entity) {

    }
}
