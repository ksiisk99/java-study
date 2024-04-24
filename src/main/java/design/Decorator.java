package design;

abstract class Decorator implements IComponent {
    private IComponent wrappee;

    public Decorator(IComponent wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void operation() {
        wrappee.operation();
    }

    public static void main(String[] args) {
        Decorator decorator = new ComponentDecorator1(new OriginalComponent());
        decorator.operation();

        System.out.println();

        Decorator decorator2 = new ComponentDecorator1(new ComponentDecorator2(new OriginalComponent()));
        decorator2.operation();

    }
}

class ComponentDecorator1 extends Decorator {

    public ComponentDecorator1(IComponent component) {
        super(component);
    }

    public void operation() {
        super.operation();
        extraOperation();
    }

    private void extraOperation() {
        System.out.println("Decorator1");
    }
}

class ComponentDecorator2 extends Decorator {

    public ComponentDecorator2(IComponent component) {
        super(component);
    }

    public void operation() {
        super.operation();
        extraOperation();
    }

    private void extraOperation() {
        System.out.println("Decorator2");
    }
}

interface IComponent {
    void operation();
}

class OriginalComponent implements IComponent {

    @Override
    public void operation() {
        System.out.println("Original");
    }
}
