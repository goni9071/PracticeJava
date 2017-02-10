package map;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class SortedMapTest {
  public static void main(String[] args) {
    SortedMap<Integer, String> sm = new TreeMap<Integer, String>();

    sm.put(new Integer(2), "Two");
    sm.put(new Integer(1), "One");
    sm.put(new Integer(4), "Four");
    sm.put(new Integer(3), "Three");
    sm.put(new Integer(5), "Five");
 
    Set s = sm.entrySet();
    // Using iterator in SortedMap
    Iterator i = s.iterator();

    while (i.hasNext()) {
      Map.Entry m = (Map.Entry) i.next();

      int key = (Integer) m.getKey();
      String value = (String) m.getValue();

      System.out.println("Key :" + key + "  value :" + value);
    }

  }
}
