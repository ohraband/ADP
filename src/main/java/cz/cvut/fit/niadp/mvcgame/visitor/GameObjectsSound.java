package cz.cvut.fit.niadp.mvcgame.visitor;


import cz.cvut.fit.niadp.mvcgame.iterator.Background;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.InputStream;


public class GameObjectsSound implements IGameObjectsVisitor {

    @Override
    public void visitCannon(AbsCannon cannon)  {
        try {
            Clip clip = AudioSystem.getClip();
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream("sounds/move.wav");
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(stream);
            clip.open(inputStream);
            clip.start();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public void visitMissile(AbsMissile missile) {
        try {
            Clip clip = AudioSystem.getClip();
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream("sounds/projectile-shoot.wav");
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(stream);
            clip.open(inputStream);
            clip.start();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    @Override
    public void visitEnemy(AbsEnemy enemy) {
        try {
            Clip clip = AudioSystem.getClip();
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream("sounds/pop.wav");
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(stream);
            clip.open(inputStream);
            clip.start();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void visitGameInfo(AbsGameInfo gameInfo) {

    }

    @Override
    public void visitCollision(AbsCollision collision) {
        //TODO add explosions sound and fix he other sounds too
        try {
            Clip clip = AudioSystem.getClip();
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream("sounds/whoosh.wav");
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(stream);
            clip.open(inputStream);
            clip.start();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void visitBackground(Background background) {

    }

}
