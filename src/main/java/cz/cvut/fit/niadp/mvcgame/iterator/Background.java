package cz.cvut.fit.niadp.mvcgame.iterator;

import cz.cvut.fit.niadp.mvcgame.visitor.IGameObjectsVisitor;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitable;

public class Background implements IVisitable {
    public String getFilePath() {
        return filePath;
    }

    String filePath;

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.visitBackground(this);
    }

    public Background(String filePath){
        this.filePath = filePath;
    }



}
