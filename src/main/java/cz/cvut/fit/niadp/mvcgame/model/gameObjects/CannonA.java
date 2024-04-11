package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.abstractfactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.state.DoubleShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.SingleShootingMode;

import java.util.ArrayList;
import java.util.List;

import static cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig.MAX_X;

public class CannonA extends AbsCannon {

    private IGameObjectsFactory gameObjectsFactory;
    private List<AbsMissile> shootingBatch;

    public CannonA(Position initPosition, IGameObjectsFactory gameObjectsFactory) {
        this.position = initPosition;
        this.gameObjectsFactory = gameObjectsFactory;

        this.power = MvcGameConfig.INIT_POWER;
        this.angle = MvcGameConfig.INIT_ANGLE;

        this.shootingBatch = new ArrayList<>();
        this.shootingMode = SINGLE_SHOOTING_MODE;

        this.missilesNumber = MvcGameConfig.INIT_MISSILES;

        this.missileType = "TypeA";
        this.invincibleMissile = false;
    }

    @Override
    public void moveUp() {
        this.move(new Vector(0, -MvcGameConfig.MOVE_STEP));
    }

    @Override
    public void moveDown() {
        this.move(new Vector(0, MvcGameConfig.MOVE_STEP));
    }

    @Override
    public void aimUp() {
        this.angle -= MvcGameConfig.ANGLE_STEP;
    }

    @Override
    public void aimDown() {
        this.angle += MvcGameConfig.ANGLE_STEP;
    }

    @Override
    public void powerUp() {
        this.power = Math.min(MvcGameConfig.MAX_POWER, this.power + MvcGameConfig.POWER_STEP);
    }

    @Override
    public void powerDown() {
        this.power = Math.max(MvcGameConfig.MIN_POWER, this.power - MvcGameConfig.POWER_STEP);
    }

    @Override
    public void missilesUp() {
        this.missilesNumber = Math.min(MvcGameConfig.MAX_MISSILES,this.missilesNumber + MvcGameConfig.MISSILES_STEP);
    }

    @Override
    public void missilesDown() {
        this.missilesNumber = Math.max(MvcGameConfig.MIN_MISSILES, this.missilesNumber - MvcGameConfig.MISSILES_STEP);
    }

    @Override
    public void primitiveShoot() {
        this.shootingBatch.add(this.gameObjectsFactory.createMissile(this.angle, this.power, this.missileType, this.invincibleMissile
        ));
    }

    @Override
    public List<AbsMissile> shoot() {
        this.shootingBatch.clear();
        this.shootingMode.shoot(this);
        return this.shootingBatch;
    }

    @Override
    public void toggleShootingMode() {
        if(this.shootingMode instanceof SingleShootingMode) {
            this.shootingMode = DOUBLE_SHOOTING_MODE;
        }
        else if (this.shootingMode instanceof DoubleShootingMode) {
            this.shootingMode = DYNAMIC_SHOOTING_MODE;
        }
        else {
            this.shootingMode = SINGLE_SHOOTING_MODE;
        }
    }
    @Override
    public void toggleInvincibleMissile(){
        this.invincibleMissile = !this.invincibleMissile;
    }
}
