package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.TimerPart;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mock;

public class BulletTest {



    BulletTest(){

    }

    @Test
    public void createBullet(){
        GameData gameData = new GameData();
        World world = new World();
        Entity MockPlayer = new Entity();
        MockPlayer.add(new PositionPart(95,52,5));
        BulletControlSystem controlSystem = new BulletControlSystem();
        assertNotNull(controlSystem);
        Entity bullet = controlSystem.createBullet(MockPlayer, gameData);
        controlSystem.process(gameData,world);
        assertNotNull(bullet);
        bullet.setRadius(8);
        assertNotEquals(0,bullet.getRadius());
    }

    @Test
    public void bulletMovementTest(){
        GameData gameData = new GameData();
        World world = new World();
        Entity MockPlayer = new Entity();
        MockPlayer.add(new PositionPart(95,52,5));
        BulletControlSystem controlSystem = new BulletControlSystem();
        assertNotNull(controlSystem);
        Entity bullet = controlSystem.createBullet(MockPlayer, gameData);
        controlSystem.process(gameData,world);
        assertNotEquals(bullet.getRadius(),0.0);
        PositionPart p = bullet.getPart(PositionPart.class);
        float x = p.getX();
        float y = p.getY();
        world.addEntity(bullet);
        for(int i = 0;i<10;i++){
            controlSystem.process(gameData,world);
        }
        assertNotEquals(x,p.getX());
        assertNotEquals(y,p.getY());
    }

    @Test
    @Timeout(10000)
    public void bulletDeathTest(){
        GameData gameData = new GameData();
        World world = new World();
        Entity MockPlayer = new Entity();
        MockPlayer.add(new PositionPart(95,52,5));
        BulletControlSystem controlSystem = new BulletControlSystem();
        assertNotNull(controlSystem);
        Entity bullet = controlSystem.createBullet(MockPlayer, gameData);
        controlSystem.process(gameData,world);
        assertNotEquals(bullet.getRadius(),0.0);
        world.addEntity(bullet);

        LifePart life = bullet.getPart(LifePart.class);
        TimerPart timerPart = bullet.getPart(TimerPart.class);

        for(int i = 0;i<100000;i++){
            controlSystem.process(gameData,world);
        }

        while (timerPart.getExpiration() > 0){
            controlSystem.process(gameData,world);
        }

        for(Entity b: world.getEntities(Bullet.class)){
            assertNotEquals(b,bullet);
        }


    }
}
