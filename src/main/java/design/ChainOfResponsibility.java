package design;

public class ChainOfResponsibility {
    public static void main(String[] args) {
        Handler handlerChain = new ConcreteHandler3(new ConcreteHandler2(new ConcreteHandler(null)));
        handlerChain.process();

        System.out.println();

        Handler handlerChain2 = new ConcreteHandler(new ConcreteHandler2(new ConcreteHandler3(null)));
        handlerChain2.process();
    }
}

abstract class Handler {
    private Handler nextHandler;

    public Handler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void process() {
        if (nextHandler != null) {
            nextHandler.process();
        }
    }
}

class ConcreteHandler extends Handler {
    private Handler nextHandler;

    public ConcreteHandler(Handler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void process() {
        System.out.println("ConcreteHandler1");

        super.process();
    }
}

class ConcreteHandler2 extends Handler {
    private Handler nextHandler;

    public ConcreteHandler2(Handler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void process() {
        System.out.println("ConcreteHandler2");

        super.process();
    }
}

class ConcreteHandler3 extends Handler {
    private Handler nextHandler;

    public ConcreteHandler3(Handler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void process() {
        System.out.println("ConcreteHandler3");

        super.process();
    }
}
