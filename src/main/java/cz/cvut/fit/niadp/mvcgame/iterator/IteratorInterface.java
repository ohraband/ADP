package cz.cvut.fit.niadp.mvcgame.iterator;

public interface IteratorInterface<T> {
    void add(T item);

    int getSize();

    boolean hasNext();

    T next();
}


