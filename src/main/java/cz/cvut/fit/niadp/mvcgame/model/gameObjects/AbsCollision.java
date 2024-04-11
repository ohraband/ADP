package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IGameObjectsVisitor;
import javafx.scene.shape.Rectangle;



public abstract class AbsCollision extends LifetimeLimitedGameObject {
    protected AbsCollision(Position position) {
        super(position);
    }

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.visitCollision(this);
    }

}
