package cz.cvut.fit.niadp.mvcgame.iterator;

import java.util.ArrayList;
import java.util.List;

public class MissileTypeIterator implements IteratorInterface<String> {

    private final List<String> missiles;
    private int currentIndex;

    public MissileTypeIterator() {
        this.missiles = new ArrayList<>();
        this.currentIndex = 0;
    }
    @Override
    public void add(String missile) {
        missiles.add(missile);
    }

    @Override
    public int getSize() {
        return missiles.size();
    }

    @Override
    public boolean hasNext() {
        return getSize() > 0;
    }

    @Override
    public String next() {
        if (currentIndex < missiles.size()) {
            currentIndex++;
        } else {
            currentIndex = 0;
        }
        return missiles.get(currentIndex);
    }
}
