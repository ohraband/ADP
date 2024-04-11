package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.niadp.mvcgame.iterator.Background;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.observer.IObservable;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;
import javafx.scene.input.MouseEvent;

import java.util.List;

public interface IGameModel extends IObservable {
    void update();
    Position getCannonPosition();
    void moveCannonUp();
    void moveCannonDown();
    void aimCannonUp();
    void aimCannonDown();
    void cannonPowerUp();
    void cannonPowerDown();
    void cannonShoot();
    List<AbsMissile> getMissiles();
    List<GameObject> getGameObjects();
    IMovingStrategy getMovingStrategy();
    void toggleMovingStrategy();
    void toggleShootingMode();
    Object createMemento();
    void setMemento(Object memento);
    void cannonMissilesUp();
    void cannonMissilesDown();
    void registerCommand(AbstractGameCommand command);
    void undoLastCommand();
    void createEnemy(MouseEvent event);
    void modifyGameInfo();
    void secretMoveCannon();
    Background getBackground();
    void toggleBackground();
    void toggleMissileType();
    void toggleInvincibleMissile();
    String getGameInfo();
}
