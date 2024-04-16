package design;

public class State {
    private AbstractState abstractState;

    public void changeState(AbstractState abstractState) {
        this.abstractState = abstractState;
    }

    public void function() {
        abstractState.print();
        changeState(new StateB());
    }

    public static void main(String[] args) {
        State state = new State();
        state.changeState(new StateA());

        state.function();
        state.function();
    }
}

interface AbstractState  {
    void print();
}

class StateA implements AbstractState {
    @Override
    public void print() {
        System.out.println("StateA");
    }
}

class StateB implements AbstractState {
    @Override
    public void print() {
        System.out.println("StateB");
    }
}
