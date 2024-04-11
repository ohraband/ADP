package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.visitor.IGameObjectsVisitor;


public abstract class AbsGameInfo extends GameObject {
    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.visitGameInfo(this);
    }

    public String Text;

    //make a new text option too

    public void setText(String Text){
        this.Text = Text;
    }

    public void setGameInfo(int score, int enemies, int cannonPower, double angle,
                            String shootingMode, int missileNumber, String movingStrategy,
                            String missileType, boolean isInvincible){
        Text = "Score: " + score + "\n" +
                "Number of enemies: " + enemies + "\n" +
                "Cannon power: " + cannonPower + "\n" +
                "Cannon angle: " + Math.round(angle) + "\n" +
                "Shooting mode: " + shootingMode + "\n" +
                "Number of missiles will be shot: " + missileNumber + "\n" +
                "Missile moving strategy: " + movingStrategy + "\n" +
                "Missile Type: " + missileType + "\n" +
                "Invincible missile: " + isInvincible;
    }
    public String getText(){
        return Text;
    }


    @Override
    public AbsGameInfo clone() {
        try {
            AbsGameInfo clonedGameInfo = (AbsGameInfo) super.clone();
            clonedGameInfo.Text = this.Text;
            return clonedGameInfo;
        }
        catch (CloneNotSupportedException e){
            return null;
        }
    }
}
