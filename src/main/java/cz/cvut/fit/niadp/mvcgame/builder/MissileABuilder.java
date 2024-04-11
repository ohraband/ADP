package cz.cvut.fit.niadp.mvcgame.builder;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.MissileA;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;

public class MissileABuilder {
    private final Position initPosition;
    private final double initAngle;
    private final int initVelocity;
    private final IMovingStrategy movingStrategy;
    private boolean invincible;
    private String designType;

    public MissileABuilder(Position initPosition, double initAngle, int initVelocity, IMovingStrategy movingStrategy) {
        this.initPosition = initPosition;
        this.initAngle = initAngle;
        this.initVelocity = initVelocity;
        this.movingStrategy = movingStrategy;
    }

    public MissileABuilder setInvincible(boolean invincible) {
        this.invincible = invincible;
        return this;
    }

    public MissileABuilder setDesignType(String designType) {
        this.designType = designType;
        return this;
    }

    public MissileA build() {
        MissileA missile = new MissileA(initPosition, initAngle, initVelocity, movingStrategy);
        missile.setInvincible(invincible);
        missile.setDesignType(designType);
        return missile;
    }
}

