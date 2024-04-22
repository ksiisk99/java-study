package design;

public class Proxy implements Subject {
    private Subject subject;

    public Proxy(RealSubject subject) {
        this.subject = subject;
    }

    @Override
    public void action() {
        System.out.println("proxy start");
        subject.action();
        System.out.println("proxy end");
    }

}

class Client {
    public static void main(String[] args) {
        Subject subject = new Proxy(new RealSubject());
        subject.action();
    }
}

interface Subject {
    void action();
}

class RealSubject implements Subject {

    @Override
    public void action() {
        System.out.println("Real Action");
    }
}
