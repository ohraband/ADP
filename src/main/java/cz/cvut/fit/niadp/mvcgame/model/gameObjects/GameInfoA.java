package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Position;

public class GameInfoA extends AbsGameInfo{

    public GameInfoA(Position initPosition) {
        this.position = initPosition;
    }

    @Override
    public GameInfoA clone() {
        GameInfoA clonedGameInfoA = new GameInfoA(this.getPosition());
        clonedGameInfoA.setText(this.getText());
        return clonedGameInfoA;
    }
}