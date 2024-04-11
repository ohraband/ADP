package cz.cvut.fit.niadp.mvcgame.observer;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;

import java.util.List;

public interface IObservable {
    void registerObserver(IObserver observer, String aspect);
    void unregisterObserver(IObserver observer, String aspect);
    void notifyObservers(String aspect, Object value);
}
