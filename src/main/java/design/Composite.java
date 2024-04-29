package design;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component{
    List<Component> components = new ArrayList<>();

    public void add(Component c) {
        components.add(c);
    }

    public void remove(Component c) {
        components.remove(c);
    }

    @Override
    public void operation() {
        System.out.println(this + " 호출");

        for (Component component:components) {
            component.operation();
        }
    }

    public static void main(String[] args) {
        Composite root = new Composite();

        Leaf leaf1 = new Leaf();
        root.add(leaf1);

        Composite composite1 = new Composite();
        root.add(composite1);

        Leaf leaf2 = new Leaf();
        Leaf leaf3 = new Leaf();
        Leaf leaf4 = new Leaf();
        composite1.add(leaf2);
        composite1.add(leaf3);
        composite1.add(leaf4);

        root.operation();
    }
}

interface Component {
    void operation();
}

class Leaf implements Component {

    @Override
    public void operation() {
        System.out.println(this + " 호출");
    }
}
