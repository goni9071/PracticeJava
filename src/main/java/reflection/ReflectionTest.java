package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class ReflectionTest {

    /**
     * @param args
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InstantiationException
     */
    public static void main(String[] args) throws SecurityException, NoSuchMethodException, ClassNotFoundException,
            IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String className = String.format("reflection.TestClass");
        Method[] methods = Class.forName(className).getDeclaredMethods();
        for (Method method : methods) {
            System.out.print(method.getName() + "-");
            for (Class<?> clazz : method.getParameterTypes()) {
                System.out.println(clazz.getName());
            }
        }
        System.out.println();
        {
            Method method = Class.forName(className).getDeclaredMethod("getNumber");
            System.out.println(method.invoke(Class.forName(className).newInstance(), null));
        }
        {
            Method method = Class.forName(className).getDeclaredMethod("setNumber", int.class);
            System.out.println(method.invoke(Class.forName(className).newInstance(), 1));
        }

        // String reuslt = (String) method.invoke(1);

    }

}
