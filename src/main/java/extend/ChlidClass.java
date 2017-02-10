package extend;

public class ChlidClass extends ParentClass {

    public static void main(String[] args) {
        new ChlidClass().test();
    }

    @Override
    public void setA() {
        System.out.println("Child!!! set");
    }

    @Override
    public void getA() {
        System.out.println("Child!!! get");
    }

    public void test() {
        super.setA();
    }
}
