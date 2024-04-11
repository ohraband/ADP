package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.abstractfactory.GameObjectsFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractfactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.iterator.Background;
import cz.cvut.fit.niadp.mvcgame.iterator.BackgroundIterator;
import cz.cvut.fit.niadp.mvcgame.iterator.MissileTypeIterator;
import cz.cvut.fit.niadp.mvcgame.iterator.MovingStrategyIterator;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;
import cz.cvut.fit.niadp.mvcgame.observer.IObservable;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.niadp.mvcgame.strategy.*;
import cz.cvut.fit.niadp.mvcgame.visitor.GameObjectsSound;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Stream;

import static cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig.*;

public class GameModel implements IGameModel, IObservable {

    private final AbsCannon cannon;
    private AbsGameInfo gameInfo;
    private IGameObjectsFactory gameObjectsFactory;
    private final List<AbsMissile> missiles;
    private List<AbsEnemy> enemies;
    private List<AbsCollision> collisions;
    private IMovingStrategy movingStrategy;
    private final GameObjectsSound sound;
    private final Queue<AbstractGameCommand> unexecutedCommands;
    private final Stack<AbstractGameCommand> executedCommands;
    private int score;
    private MovingStrategyIterator strategyManager;
    private BackgroundIterator backgroundIterator;
    private Background background;
    private MissileTypeIterator missileTypeIterator;
    private final Map<IObserver, List<String>> observers;

    public enum AvailableAspects {
        CANNON("cannon"),
        MISSILE("missile"),
        BACKGROUND("background"),
        ENEMY("enemy"),
        COLLISION("collision");

        private final String text;
        AvailableAspects(final String text) {
            this.text = text;
        }
        @Override
        public String toString() {
            return text;
        }
    }


    public GameModel() {
        this.observers = new HashMap<>();
        this.gameObjectsFactory = GameObjectsFactoryA.getInstance(this);
        this.cannon = this.gameObjectsFactory.createCannon();
        this.missiles = new ArrayList<>();
        this.movingStrategy = new SimpleMovingStrategy();
        this.unexecutedCommands = new LinkedBlockingQueue<>();
        this.executedCommands = new Stack<>();
        this.sound = new GameObjectsSound();
        this.enemies = new ArrayList<>();
        this.gameInfo = this.gameObjectsFactory.createGameInfo();
        score = 0;
        this.collisions = new ArrayList<>();
        this.strategyManager = new MovingStrategyIterator();
        this.backgroundIterator = new BackgroundIterator();
        this.background = new Background("images/enemy1.png");
        this.missileTypeIterator = new MissileTypeIterator();
    }

    public void update() {
        this.executeCommands();
        this.moveMissiles();
        this.destroyCollisions();
        this.modifyGameInfo();
        // this.notifyObservers();
    }


    private void executeCommands() {
        while(!this.unexecutedCommands.isEmpty()) {
            AbstractGameCommand command = this.unexecutedCommands.poll();
            command.doExecute();
            this.executedCommands.add(command);
        }
    }

    private void moveMissiles() {
        this.missiles.forEach(AbsMissile::move);
        this.destroyMissiles();
        this.findCollisions();
        this.notifyObservers("missile", this.missiles);
    }

    private void destroyCollisions(){
        collisions.removeAll(
                this.collisions.stream().filter(collision -> collision.getAge() > 250).toList()
        );
    }


    private void destroyMissiles() {
        this.missiles.removeAll(
                this.missiles.stream().filter(missile -> missile.getPosition().getX() > MvcGameConfig.MAX_X ||
                        missile.getPosition().getY() > MvcGameConfig.MAX_Y || missile.getPosition().getX() < 0 ||
                        missile.getPosition().getY() < 0 || missile.getAge() > 9999).toList()
        );
    }

    private void findCollisions(){
        List<AbsEnemy> enemiesToRemove = new ArrayList<>();
        List<AbsMissile> missilesToRemove = new ArrayList<>();
        for (AbsMissile missile : missiles) {
            Rectangle missileBoundingBox = missile.getBoundingBox();

            for (AbsEnemy enemy : enemies) {
                Rectangle enemyBoundingBox = enemy.getBoundingBox();

                if (missileBoundingBox.getBoundsInParent().intersects(enemyBoundingBox.getBoundsInParent())) {
                    System.out.println("Collision detected between missile and enemy!");
                    AbsCollision collision = new CollisionA(enemy.getPosition());
                    collision.acceptVisitor(this.sound);
                    collisions.add(collision);
                    score += 100;
                    enemiesToRemove.add(enemy);
                    if(!missile.isInvincible()){
                        missilesToRemove.add(missile);
                    }
                }
            }
        }
        enemies.removeAll(enemiesToRemove);
        missiles.removeAll(missilesToRemove);
    }

    public Position getCannonPosition() {
        return this.cannon.getPosition();
    }

    public void moveCannonUp() {
        this.cannon.moveUp();
        this.cannon.acceptVisitor(this.sound);
        this.notifyObservers("cannon", this.cannon.getPosition());
    }

    public void moveCannonDown() {
        this.cannon.moveDown();
        this.cannon.acceptVisitor(this.sound);
        this.notifyObservers("cannon", List.of(this.cannon));
    }

    public void cannonShoot() {
        List<AbsMissile> missiles = this.cannon.shoot();
        this.missiles.addAll(missiles);
        for (AbsMissile missile : missiles) {
            missile.acceptVisitor(this.sound);
        }
        this.notifyObservers("cannon", this.missiles);
    }

    public void addMissile() {
        missileTypeIterator.add("TypeA");
        missileTypeIterator.add("TypeB");
        missileTypeIterator.add("TypeC");
    }

    public void toggleMissileType() {
        if (missileTypeIterator.getSize() == 0) {
            addMissile();
        }

        if (missileTypeIterator.hasNext()) {
            this.cannon.setMissileType(missileTypeIterator.next());
        }
    }

    public void toggleInvincibleMissile(){
        this.cannon.toggleInvincibleMissile();
    }

    public void aimCannonUp() {
        this.cannon.aimUp();
        this.notifyObservers("cannon", List.of(this.cannon));
    }

    public void aimCannonDown() {
        this.cannon.aimDown();
        this.notifyObservers("cannon", List.of(this.cannon));
    }

    public void cannonPowerUp() {
        this.cannon.powerUp();
        this.notifyObservers("cannon", List.of(this.cannon));
    }

    public void secretMoveCannon(){
        this.cannon.getPosition().setX((this.cannon.getPosition().getX() + MOVE_STEP) % MAX_X);
        System.out.println("Secret cannon moved.");
        this.notifyObservers("cannon", List.of(this.cannon));
    }

    public void cannonPowerDown() {
        this.cannon.powerDown();
        this.notifyObservers("cannon", List.of(this.cannon));
    }

    public void cannonMissilesUp() {
        this.cannon.missilesUp();
        this.notifyObservers("cannon", List.of(this.cannon));
    }

    public void cannonMissilesDown() {
        this.cannon.missilesDown();
        this.notifyObservers("cannon", List.of(this.cannon));
    }



    @Override
    public void registerObserver(IObserver observer, String aspect) {
        if(!this.observers.containsKey(observer)){
            this.observers.put(observer, new ArrayList<>());
        }
        this.observers.get(observer).add(aspect);
    }

    @Override
    public void unregisterObserver(IObserver observer, String aspect) {
        this.observers.remove(observer);
    }


    @Override
    public void notifyObservers(String aspect, Object value) {
        for (Map.Entry<IObserver, List<String>> entry : observers.entrySet()) {
            List<String> values = entry.getValue();
            for (String v : values) {
                if(v.equals(aspect)){
                    IObserver observer = entry.getKey();
                    observer.update(aspect, value);
                }
            }
        }
    }

    public List<AbsMissile> getMissiles() {
        return this.missiles;
    }

    public List<AbsEnemy> getEnemies() {
        return this.enemies;
    }

    public List<GameObject> getGameObjects() {
        return Stream.concat(Stream.concat(Stream.concat(Stream.concat(
                Stream.of(this.gameInfo),Stream.of(this.cannon)),
                        this.missiles.stream()),
                        this.enemies.stream()),
                        this.collisions.stream()).toList();
    }

    public IMovingStrategy getMovingStrategy() {
        return this.movingStrategy;
    }

    public void addMovingStrategies(){
        strategyManager.add(new SimpleMovingStrategy());
        strategyManager.add(new RealisticMovingStrategy());
        strategyManager.add(new ManhattanMovingStrategy());
        strategyManager.add(new NewtonMovingStrategy());
    }

    public void toggleMovingStrategy(){
        if (strategyManager.getSize() == 0){
            addMovingStrategies();
        }

        if (strategyManager.hasNext()) {
            this.movingStrategy = strategyManager.next();
        }
    }

    public void addBackground(){
      backgroundIterator.add(new Background(BACKGROUND_1));
      backgroundIterator.add(new Background(BACKGROUND_2));
      backgroundIterator.add(new Background(BACKGROUND_3));
      backgroundIterator.add(new Background(BACKGROUND_4));
      backgroundIterator.add(new Background(BACKGROUND_5));
      backgroundIterator.add(new Background(BACKGROUND_6));
    }

    public void toggleBackground(){
        if (backgroundIterator.getSize() == 0){
            addBackground();
        }

        if (backgroundIterator.hasNext()) {
            this.background = backgroundIterator.next();
        }
        this.notifyObservers("background", this.background);
    }

    public Background getBackground() {
        return background;
    }

    public void toggleShootingMode() {
        this.cannon.toggleShootingMode();
        this.notifyObservers("cannon", this.cannon);
    }

    private static class Memento {
        private int cannonPositionX;
        private int cannonPositionY;
        private List<AbsEnemy> enemies;
        private AbsGameInfo gameInfo;
        private IMovingStrategy movingStrategy;
        private IShootingMode shootingMode;
        // game model snapshot (enemies, gameInfo, strategy, state), try to reset score
    }

    public Object createMemento() {
        Memento gameModelSnapshot = new Memento();
        gameModelSnapshot.cannonPositionX = this.getCannonPosition().getX();
        gameModelSnapshot.cannonPositionY = this.getCannonPosition().getY();
        gameModelSnapshot.enemies = new ArrayList<>(this.enemies);
        gameModelSnapshot.gameInfo = this.gameInfo.clone();
        gameModelSnapshot.movingStrategy = this.movingStrategy;
        gameModelSnapshot.shootingMode = this.cannon.getShootingMode();
        return gameModelSnapshot;
    }

    public void setMemento(Object memento) {
        Memento gameModelSnapshot = (Memento) memento;
        this.cannon.getPosition().setX(gameModelSnapshot.cannonPositionX);
        this.cannon.getPosition().setY(gameModelSnapshot.cannonPositionY);
        this.enemies = gameModelSnapshot.enemies;
        this.gameInfo = gameModelSnapshot.gameInfo;
        this.movingStrategy = gameModelSnapshot.movingStrategy;
        this.cannon.setShootingMode(gameModelSnapshot.shootingMode);
    }

    @Override
    public void registerCommand(AbstractGameCommand command) {
        this.unexecutedCommands.add(command);
    }

    @Override
    public void undoLastCommand() {
        if (!this.executedCommands.isEmpty()) {
            this.executedCommands.pop().unExecute();
            this.notifyObservers("missiles", this.missiles);
        }
    }

    @Override
    public void createEnemy(MouseEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();
        AbsEnemy enemy = this.gameObjectsFactory.createEnemy(x, y);
        enemy.acceptVisitor(this.sound);
        this.enemies.add(enemy);
        //System.out.println("Model Mouse Click at Coordinates: X=" + x + ", Y=" + y);
    }

    @Override
    public void modifyGameInfo(){
        this.gameInfo.setGameInfo(
                score, this.enemies.size() , this.cannon.getPower() ,
                Math.round(this.cannon.getAngle()) ,
                this.cannon.getShootingMode().getName() ,
                this.cannon.getMissilesNumber() , this.getMovingStrategy().getName(),
                this.cannon.getMissileType(), this.cannon.isInvincibleMissile()
        );
    }

    @Override
    public String getGameInfo(){
        return gameInfo.getText();
    }
}
