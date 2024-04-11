package cz.cvut.fit.niadp.mvcgame.abstractfactory;

import cz.cvut.fit.niadp.mvcgame.builder.MissileABuilder;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;

public class GameObjectsFactoryA implements IGameObjectsFactory {
    private IGameModel model;
    private static IGameObjectsFactory instance;

    private GameObjectsFactoryA(IGameModel model) {
        this.model = model;
    }

    private GameObjectsFactoryA() {
    }

    public static IGameObjectsFactory getInstance() {
        if (instance == null){
            instance = new GameObjectsFactoryA();
        }
        return instance;
    }

    public static IGameObjectsFactory getInstance(IGameModel model) {
        if (instance == null){
            instance = new GameObjectsFactoryA(model);
        }
        return instance;
    }

    @Override
    public CannonA createCannon() {
        return new CannonA(new Position(MvcGameConfig.CANNON_POS_X, MvcGameConfig.CANNON_POS_Y), this);
    }

    @Override
    public MissileA createMissile(double initAngle, int initVelocity, String missileType, boolean invincibleMissile) {
        return new MissileABuilder(new Position(this.model.getCannonPosition().getX(), this.model.getCannonPosition().getY()),
                initAngle, initVelocity, this.model.getMovingStrategy())
                .setInvincible(invincibleMissile)
                .setDesignType(missileType)
                .build();
    }

    @Override
    public EnemyA createEnemy(int posX, int posY) {
        return new EnemyA(new Position(posX, posY));
    }

    @Override
    public GameInfoA createGameInfo() {
        return new GameInfoA(new Position(MvcGameConfig.GAMEINFO_POS_X, MvcGameConfig.GAMEINFO_POS_Y));
    }

}
