package generic;

public class GenericTest {
    public static void main(String[] args) {
        GenericTest clazz = new GenericTest();
        // ClazzGeneric classGeneric =
        // clazz.getClassGeneric(ClazzGeneric.class);
        String classGeneric = clazz.setString(clazz.getClassGenerics(String.class));
        System.out.println(classGeneric);
    }

    public String setString(String string) {
        return string;
    }

    @SuppressWarnings("unchecked")
    public <T> T getClassGeneric(Class<T> type) {
        ClazzGeneric classGeneric = new ClazzGeneric() {

            @Override
            public int getPlusOne(int param) {

                return param + 1;
            }
        };
        return (T) classGeneric;
    }

    public <T> T getClassGenerics(Class<T> type) {
        Object result = "hah";
        return (T) result;
    }

    public interface ClazzGeneric {
        public int getPlusOne(int param);
    }
}
