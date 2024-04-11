package cz.cvut.fit.niadp.mvcgame.iterator;

import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;

import java.util.ArrayList;
import java.util.List;

public class MovingStrategyIterator implements IteratorInterface<IMovingStrategy> {

    private final List<IMovingStrategy> strategies;
    private int currentIndex;

    public MovingStrategyIterator() {
        this.strategies = new ArrayList<>();
        this.currentIndex = 0;
    }

    @Override
    public void add(IMovingStrategy strategy) {
        strategies.add(strategy);
    }

    @Override
    public int getSize() {
        return strategies.size();
    }

    @Override
    public boolean hasNext() {
        return getSize() > 0;
    }

    @Override
    public IMovingStrategy next() {
        if (currentIndex < strategies.size()) {
            currentIndex++;
        }
        else{
            currentIndex = 0;
        }
        return strategies.get(currentIndex);
    }
}
