package design;

public class FactoryMethod {
    private static Product createProduct() {
        return new ProductA();
    }

    public static void main(String[] args) {
        Product product = FactoryMethod.createProduct();

        product.print();
    }
}

interface Product {
    void print();
}

class ProductA implements Product {
    @Override
    public void print() {
        System.out.println("ProductA");
    }
}

class ProductB implements Product {
    @Override
    public void print() {
        System.out.println("ProductB");
    }
}