package cz.cvut.fit.niadp.mvcgame.model.gameObjects;


import cz.cvut.fit.niadp.mvcgame.visitor.IGameObjectsVisitor;
import javafx.scene.shape.Rectangle;


public abstract class AbsEnemy extends GameObject {
    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.visitEnemy(this);
    }

    String designType;

    public Rectangle getBoundingBox() {
        // Adjust the width and height as needed
        return new Rectangle(this.getPosition().getX(), this.getPosition().getY(), 20, 20); // Assuming a 10x10 bounding box
    }


    public String getDesignType() {
        return designType;
    }
}
