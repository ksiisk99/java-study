package design;

public class TemplateMethod {
    public static void main(String[] args) {
        Ramyun ramyun = new JinRamyun();
        ramyun.cook();

        System.out.println("————————");

        ramyun = new SinRamyun();
        ramyun.cook();

    }
}

abstract class Ramyun {
    public void cook() {
        System.out.println("냄비에 물을 받고 끓인다.");

        getRamyun();

        System.out.println("스프와 면을 넣는다.");
    }

    protected abstract void getRamyun();
}

class JinRamyun extends Ramyun {

    @Override
    protected void getRamyun() {
        System.out.println("진라면을 꺼낸다.");
    }
}

class SinRamyun extends Ramyun {

    @Override
    protected void getRamyun() {
        System.out.println("신라면을 꺼낸다.");
    }
}
