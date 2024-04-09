package basic;

public class Nested {
    public static void main(String[] args) {
        OuterClass.StaticNestedClass staticNestedClass = new OuterClass.StaticNestedClass();
        staticNestedClass.print();
        OuterClass.StaticNestedClass.print();

        OuterClass outerClass = new OuterClass();
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
        innerClass.print();
        innerClass.outerPrint();
    }
}

class OuterClass {
    private String outerName = "OUTER";

    class InnerClass {
        String innerName = "INNER";

        public void print() {
            System.out.println(innerName);
        }

        public void outerPrint() {
            System.out.println(outerName);
        }
    }

    static class StaticNestedClass {
        public static void print() {
            System.out.println();
        }
    }
}
