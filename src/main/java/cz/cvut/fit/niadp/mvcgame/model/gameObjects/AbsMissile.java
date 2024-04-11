package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IGameObjectsVisitor;
import javafx.scene.shape.Rectangle;

public abstract class AbsMissile extends LifetimeLimitedGameObject {

    private final double initAngle;
    private final int initVelocity;
    private boolean invincible;
    private String designType;
    public int getMoveCount() {
        return moveCount;
    }

    protected int moveCount;

    protected AbsMissile(Position initPosition, double initAngle, int initVelocity) {
        super(initPosition);
        this.initAngle = initAngle;
        this.initVelocity = initVelocity;
        this.moveCount = 0;
    }

    public abstract void move();

    public double getInitAngle() {
        return this.initAngle;
    }

    public int getInitVelocity() {
        return this.initVelocity;
    }

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.visitMissile(this);
    }

    public Rectangle getBoundingBox() {
        return new Rectangle(this.getPosition().getX(), this.getPosition().getY(), 20, 20); // Assuming a 10x10 bounding box
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    public void setDesignType(String designType) {
        this.designType = designType;
    }

    public boolean isInvincible() {
        return invincible;
    }

    public String getDesignType() {
        return designType;
    }
}
