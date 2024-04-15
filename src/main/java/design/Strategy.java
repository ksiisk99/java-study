package design;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Strategy {
    IStrategy IStrategy;

    void setStrategy(IStrategy IStrategy) {
        this.IStrategy = IStrategy;
    }

    void doSomething() {
        this.IStrategy.function();
    }

    public static void main(String[] args) {
        Strategy strategy = new Strategy();

        strategy.setStrategy(new StrategyA());
        strategy.doSomething();

        strategy.setStrategy(new StrategyB());
        strategy.doSomething();

        // 정렬 기법을 변경하는 전략패턴
        List<Integer> list = Arrays.asList(9, 7, 5, 3, 1);
        Collections.sort(list, Comparator.comparingInt(a -> a));
    }
}

class StrategyA implements IStrategy {
    @Override
    public void function() {
        System.out.println("StrategyA");
    }
}

class StrategyB implements IStrategy {
    @Override
    public void function() {
        System.out.println("StrategyB");
    }
}

interface IStrategy {
    void function();
}
