package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
public class ToggleInvincibleMissileCommand extends AbstractGameCommand {

    public ToggleInvincibleMissileCommand(IGameModel model) {
        this.subject = model;
    }

    @Override
    protected void execute() {
        this.subject.toggleInvincibleMissile();
    }
}
