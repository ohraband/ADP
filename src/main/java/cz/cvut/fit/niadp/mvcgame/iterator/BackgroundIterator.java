package cz.cvut.fit.niadp.mvcgame.iterator;

import java.util.ArrayList;
import java.util.List;


public class BackgroundIterator implements IteratorInterface<Background> {

    private final List<Background> backgrounds;
    private int currentIndex;

    public BackgroundIterator() {
        this.backgrounds = new ArrayList<>();
        this.currentIndex = 0;
    }

    @Override
    public void add(Background background) {
        backgrounds.add(background);
    }

    @Override
    public int getSize() {
        return backgrounds.size();
    }


    @Override
    public boolean hasNext() {
        return getSize() > 0;
    }

    @Override
    public Background next() {
        if (currentIndex < backgrounds.size()) {
            currentIndex++;
        }
        else{
            currentIndex = 0;
        }
        return backgrounds.get(currentIndex);
    }
}
