package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class SecretCannonCommand extends AbstractGameCommand {

    public SecretCannonCommand(IGameModel model) {
        this.subject = model;
    }

    @Override
    protected void execute() {
        this.subject.secretMoveCannon();
    }
}
