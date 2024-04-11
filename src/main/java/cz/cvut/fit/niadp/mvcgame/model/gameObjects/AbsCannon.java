package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.state.DoubleShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.DynamicShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.SingleShootingMode;
import cz.cvut.fit.niadp.mvcgame.visitor.IGameObjectsVisitor;

import java.util.List;

public abstract class AbsCannon extends GameObject {

    protected IShootingMode shootingMode;
    protected static IShootingMode SINGLE_SHOOTING_MODE = new SingleShootingMode();
    protected static IShootingMode DOUBLE_SHOOTING_MODE = new DoubleShootingMode();
    protected static IShootingMode DYNAMIC_SHOOTING_MODE = new DynamicShootingMode();

    protected String missileType;
    protected boolean invincibleMissile;

    public int getMissilesNumber() {
        return missilesNumber;
    }

    public int getPower() {
        return power;
    }

    public double getAngle() {
        return angle;
    }

    public boolean isInvincibleMissile() {
        return invincibleMissile;
    }

    public String getMissileType() {
        return missileType;
    }


    protected int missilesNumber;
    protected int power;
    protected double angle;

    public IShootingMode getShootingMode() {
        return shootingMode;
    }

    public void setShootingMode( IShootingMode shootingMode) {
        this.shootingMode = shootingMode;
    }

    public void setMissileType (String missileType){ this.missileType = missileType;}

    public abstract void moveUp();

    public abstract void moveDown();
    public abstract void aimUp();
    public abstract void aimDown();
    public abstract void powerUp();
    public abstract void powerDown();
    public abstract void missilesUp();
    public abstract void missilesDown();

    public abstract void primitiveShoot();
    public abstract List<AbsMissile> shoot();

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.visitCannon(this);
    }

    public abstract void toggleShootingMode();

    public abstract void toggleInvincibleMissile();
}
