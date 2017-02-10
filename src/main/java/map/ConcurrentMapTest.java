package map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentMapTest {
    private static ConcurrentMap<Integer, String> testMap = new ConcurrentHashMap<Integer, String>();

    public static void main(String[] args) {
        String result = testMap.putIfAbsent(1, "a");
        System.out.println(result);
        System.out.println(testMap.putIfAbsent(1, "B"));
        System.out.println(testMap);

    }
}
