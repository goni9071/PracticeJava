package reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface MyInterface {
    void method();
}

class MyInterfaceImpl implements MyInterface {
    public void method() {
        System.out.println("method");
    }
}

class ProxyClass implements InvocationHandler {
    Object obj;

    public ProxyClass(Object o) {
        obj = o;
    }

    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        Object result = null;
        try {
            System.out.println("before the method is called ");
            result = m.invoke(obj, args);
        } catch (Exception eBj) {
        } finally {
            System.out.println("after the method is called");
        }
        return result;
    }
}

public class ProxyClassTest {
    public static void main(String[] argv) throws Exception {
        MyInterface myintf = (MyInterface) Proxy.newProxyInstance(MyInterface.class.getClassLoader(),
                new Class[] { MyInterface.class }, new ProxyClass(new MyInterfaceImpl()));
        myintf.method();
    }
}
