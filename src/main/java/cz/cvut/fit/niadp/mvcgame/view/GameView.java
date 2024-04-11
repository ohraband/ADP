package cz.cvut.fit.niadp.mvcgame.view;

import cz.cvut.fit.niadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.visitor.GameObjectsRender;

import java.util.Objects;

public class GameView implements IObserver {

    private final IGameModel model;
    private final GameController controller;
    private IGameGraphics gameGraphics;
    private final GameObjectsRender render;

    public GameView(IGameModel model) {
        this.model = model;
        this.controller = new GameController(this.model);
        this.model.registerObserver(this, GameModel.AvailableAspects.CANNON.toString());
        this.model.registerObserver(this, GameModel.AvailableAspects.ENEMY.toString());
        this.model.registerObserver(this, GameModel.AvailableAspects.MISSILE.toString());
        this.model.registerObserver(this, GameModel.AvailableAspects.BACKGROUND.toString());
        this.model.registerObserver(this, GameModel.AvailableAspects.COLLISION.toString());
        this.render = new GameObjectsRender();
    }

    public GameController getController() {
        return this.controller;
    }

    private void render() {
        this.gameGraphics.clear();
        this.model.getBackground().acceptVisitor(this.render);
        this.model.getGameObjects().forEach(gameObject -> gameObject.acceptVisitor(this.render));
    }

    public void setGraphicsContext(IGameGraphics gameGraphics) {
        this.gameGraphics = gameGraphics;
        this.render.setGraphicsContext(gameGraphics);
        this.render();
    }

    @Override
    public void update(String aspect, Object value) {
        //System.out.println("Aspect: " + aspect);
        this.render();
    }
}
