package extend;

public class ParentClass {

    public void getA() {
        System.out.println("Parent!! get");
    };

    public void setA() {
        System.out.println("Parent!! set");
        getA();
    };
}
