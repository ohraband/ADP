package cz.cvut.fit.niadp.mvcgame.strategy;
import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;

//better with some initial cannon angle
public class ManhattanMovingStrategy implements IMovingStrategy{

    @Override
    public void updatePosition(AbsMissile missile) {
        int initVelocity = missile.getInitVelocity();
        double initAngle = missile.getInitAngle();
        long time = missile.getAge() / 100;

        if(missile.getMoveCount() < 50){
            int dX = (int) (initVelocity * time * Math.cos(initAngle));
            int dY = (int) (initVelocity * time * Math.sin(initAngle));
            missile.move(new Vector(dX, dY));
        }

        if (missile.getMoveCount() % 70 < 35) {
            int dY = (int) (initVelocity * Math.sin(initAngle) * 1.5);
            int dX = 0;
            missile.move(new Vector(dX, dY));
        }
        else
        {
            int dX = (int) (initVelocity * Math.cos(initAngle) / 3);
            int dY = 0;
            missile.move(new Vector(dX, dY));
        }


    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}

