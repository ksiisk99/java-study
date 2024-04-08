package generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenericWildCard {

    public static void main(String[] args) {
        List<MyGrandParent> myGrandParents = new ArrayList<>();
        List<MyParent> myParents = new ArrayList<>();
        List<MyChild> myChildren = new ArrayList<>();

        List<? extends MyParent> extendsList = myParents;
        //extendsList = myGrandParents; // TODO: MyParent가 상한이기 때문에 그 조상으로는 할당 불가능
        extendsList = myChildren;

        List<? super MyParent> superList = myParents;
        superList = myGrandParents;
        //superList = myChildren; // TODO: MyParent가 하한이기 때문에 그 자손으로는 할당 불가능
    }

    // TODO: MyParent와 이를 상속한 부모 클래스만 조회가능
    private static void upperBoundGet(Collection<? extends MyParent> c) {
        for (MyParent e : c) {
            System.out.println(e);
        }

        for (MyGrandParent e : c) {
            System.out.println(e);
        }

        for (Object e : c) {
            System.out.println(e);
        }

        // TODO: 어떤 자식 클래스 인지 알 수 없으므로 컴파일 에러
        // for(MyChild e: c) {
        // 	System.out.println(e);
        // }
    }

    // TODO: 어떤 타입의 객체를 넣어야 할지 모르기 때문에 컴파일 에러
    private static void upperBoundAdd(Collection<? extends MyParent> c) {
        // c.add(new MyGrandParent());
        // c.add(new MyParent());
        // c.add(new MyChild();
        // c.add(new Object());
    }

    // TODO: 와일드카드 최상위 범위인 Object 타입으로만 안전하게 꺼낼 수 있음.
    private static void lowerBoundGet(Collection<? super MyParent> c) {
        for (Object e : c) {
            System.out.println(e);
        }

        // for(MyParent e: c) {
        // 	System.out.println(e);
        // }

        // for(MyGrandParent e: c) {
        // 	System.out.println(e);
        // }

        // for(MyChild e: c) {
        // 	System.out.println(e);
        // }
    }

    // TODO: 부모 클래스를 상속받은 그 자식 클래스들은 컬렉션에 삽입 가능 업캐스팅 가능한 타입으로만 제한
    private static void lowerBoundAdd(Collection<? super MyParent> c) {
        c.add(new MyParent());
        c.add(new MyChild());
        //c.add(new MyGrandParent());
        //c.add(new Object());
    }
}

class MyGrandParent {
}

class MyParent extends MyGrandParent {
}

class MyChild extends MyParent {
}
