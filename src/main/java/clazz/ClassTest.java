package clazz;

public class ClassTest {
    public static void main(String[] args) {
        Class<?> i = Integer.class;
        Class<?> s = String.class;
        if (s.equals(String.class)) {
            System.out.println("ok1");
        }
        if (i.equals(String.class)) {
            System.out.println("ok2");
        }

    }
}
