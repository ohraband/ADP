package cz.cvut.fit.niadp.mvcgame.visitor;

import cz.cvut.fit.niadp.mvcgame.iterator.Background;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;


public interface IGameObjectsVisitor {
    void visitCannon(AbsCannon cannon);
    void visitMissile(AbsMissile missile);
    void visitEnemy(AbsEnemy missile);
    void visitGameInfo(AbsGameInfo gameInfo);
    void visitCollision(AbsCollision collision);
    void visitBackground(Background background);


}
