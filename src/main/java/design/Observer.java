package design;

import java.util.ArrayList;
import java.util.List;

public class Observer {
    public static void main(String[] args) {
        ISubject publisher = new ConcreteSubject();

        IObserver observer1 = new ObserverA();
        IObserver observer2 = new ObserverB();
        publisher.registerObserver(observer1);
        publisher.registerObserver(observer2);
        System.out.println();

        publisher.notifyObserver();
        System.out.println();

        publisher.removeObserver(observer2);

        publisher.notifyObserver();
    }
}

interface ISubject {
    void registerObserver(IObserver o);

    void removeObserver(IObserver o);

    void notifyObserver();
}

class ConcreteSubject implements ISubject {
    List<IObserver> observers = new ArrayList<>();

    @Override
    public void registerObserver(IObserver o) {
        observers.add(o);
        System.out.println(o + " subscribe success");
    }

    @Override
    public void removeObserver(IObserver o) {
        observers.remove(o);
        System.out.println(o + " subscribe cancel");
    }

    @Override
    public void notifyObserver() {
        for (IObserver observer : observers) {
            observer.update();
        }
    }
}

interface IObserver {
    void update();
}

class ObserverA implements IObserver {

    @Override
    public void update() {
        System.out.println("ObserverA event");
    }

    @Override
    public String toString() {
        return "ObserverA";
    }
}

class ObserverB implements IObserver {

    @Override
    public void update() {
        System.out.println("ObserverB event");
    }

    @Override
    public String toString() {
        return "ObserverB";
    }
}
