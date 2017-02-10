package interfaces;

public class InterfaceTester implements InterfaceTest {
    public InterfaceTester(String b) {
        this.b = b;
    }

    private String b;

    public String getB() {
        return b;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        InterfaceTest interfaceTest = new InterfaceTester("자식");
        InterfaceTester interfaceTester = (InterfaceTester) interfaceTest;
        System.out.println(interfaceTester.getB());
        if (interfaceTester instanceof InterfaceTest) {
            System.out.println("ok");
        }
        if (interfaceTester instanceof InterfaceTester) {
            System.out.println("ok");
        }
    }
}
