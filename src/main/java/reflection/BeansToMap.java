package reflection;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeansToMap {
    public static void main(String[] args) {
        TestClass testClass = new TestClass();

        System.out.println(beansToMap(testClass));
    }

    public static Map<String, Object> beansToMap(Object beans) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo info = Introspector.getBeanInfo(beans.getClass());
            PropertyDescriptor pdArray[] = info.getPropertyDescriptors();
            Method method = null;
            if (pdArray == null)
                return null;
            for (PropertyDescriptor pd : pdArray) {
                method = pd.getReadMethod();
                Object value = method.invoke(beans, new Object[0]);
                if (value == null) {
                    pd.getWriteMethod().invoke(beans, Class.forName(pd.getPropertyType().getName()).newInstance());
                    value = method.invoke(beans, new Object[0]);
                }
                System.out.println("pdName:" + pd.getName());
                System.out.println("pdType:" + pd.getPropertyType());
                System.out.println("methodName:" + method.getName());
                System.out.println("value of className:" + (value == null ? "null" : value.getClass().getName()));
                if (!pd.getName().equals("class"))
                    map.put(pd.getName(), value);
                System.out.println("==============");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
