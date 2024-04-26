package design;

import java.util.HashSet;
import java.util.Set;

public interface Iterator {
    boolean hasNext();

    Object next();

    static void main(String[] args) {
        ConcreteAggregate aggregate = new ConcreteAggregate(5);
        aggregate.add(1);
        aggregate.add(2);
        aggregate.add(3);
        aggregate.add(4);
        aggregate.add(5);

        Iterator iter = aggregate.iterator();

        while(iter.hasNext()) {
            System.out.printf("%s ", iter.next());
        }

        Set<Integer> set = new HashSet<>();
        set.add(2);
        java.util.Iterator<Integer> ite = set.iterator();
        while(ite.hasNext()) {
            System.out.println(ite.next());
        }
    }
}

class ConcreteIterator implements Iterator {
    Object[] arr;
    private int nextIndex = 0;

    public ConcreteIterator(Object[] arr) {
        this.arr = arr;
    }

    @Override
    public boolean hasNext() {
        return nextIndex < arr.length;
    }

    @Override
    public Object next() {
        return arr[nextIndex++];
    }
}

interface Aggregate {
    Iterator iterator();
}

class ConcreteAggregate<T> implements Aggregate {
    Object[] arr;
    int index = 0;

    public ConcreteAggregate(int size) {
        this.arr = new Object[size];
    }

    public void add(Object o) {
        if(index < arr.length) {
            arr[index++] = o;
        }
    }

    @Override
    public Iterator iterator() {
        return new ConcreteIterator(arr);
    }
}
