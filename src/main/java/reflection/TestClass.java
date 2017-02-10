package reflection;

public class TestClass extends TestSuperClass {
    private int number;
    private boolean a;
    private TestChildClass childClass;

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public boolean isA() {
        return a;
    }

    public void setA(boolean a) {
        this.a = a;
    }

    public TestChildClass getChildClass() {
        return childClass;
    }

    public void setChildClass(TestChildClass childClass) {
        this.childClass = childClass;
    }
}
