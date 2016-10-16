package org.java7recipes.chapter4;

import java.util.Iterator;

public class UnmodifiableIterator {

    public static <E> Iterator<E> wrap(final Iterator<E> iter) {
        return new Iterator<E>() {

            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public E next() {
                return iter.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove not supported.");
            }
        };
    }
}
