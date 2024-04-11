package cz.cvut.fit.niadp.mvcgame.visitor;

import cz.cvut.fit.niadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.iterator.Background;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Random;

public class GameObjectsRender implements IGameObjectsVisitor {
    private IGameGraphics gameGraphics;

    public void setGraphicsContext(IGameGraphics gameGraphics) {
        this.gameGraphics = gameGraphics;
    }

    @Override
    public void visitCannon(AbsCannon cannon) {
        this.gameGraphics.drawImage(MvcGameConfig.CANNON_IMAGE_RESOURCE, cannon.getPosition());
    }

    @Override
    public void visitMissile(AbsMissile missile) {
        if(missile.getDesignType().equals("TypeA")) {
            this.gameGraphics.drawImage((MvcGameConfig.MISSILE_IMAGE_RESOURCE_TYPE_A), missile.getPosition());
        }
        else if(missile.getDesignType().equals("TypeB")){
            this.gameGraphics.drawImage((MvcGameConfig.MISSILE_IMAGE_RESOURCE_TYPE_B), missile.getPosition());
        }
        else{
            this.gameGraphics.drawImage((MvcGameConfig.MISSILE_IMAGE_RESOURCE), missile.getPosition());
        }
    }

    @Override
    public void visitEnemy(AbsEnemy enemy) {
        if(enemy.getDesignType().equals("TypeA")){
            this.gameGraphics.drawImage((MvcGameConfig.ENEMY_1), enemy.getPosition());
        }
        else{
            this.gameGraphics.drawImage((MvcGameConfig.ENEMY_2), enemy.getPosition());
        }

    }

    @Override
    public void visitGameInfo(AbsGameInfo gameInfo) {
        this.gameGraphics.drawText(gameInfo.getText(), gameInfo.getPosition());
    }

    @Override
    public void visitCollision(AbsCollision collision) {
        this.gameGraphics.drawImage((MvcGameConfig.COLLISION_IMAGE), collision.getPosition());
    }

    @Override
    public void visitBackground(Background background){
        this.gameGraphics.drawImage(background.getFilePath(), new Position(0,0));
    }
}
