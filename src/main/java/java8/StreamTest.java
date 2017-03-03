package java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Audio;
import model.Car;
import model.Menu;

public class StreamTest {
  public static void main(String[] args) {
    example12();
  }

  // forEach Test
  public static void example12() {
    List<String> strList = Arrays.asList("가", "나", "다");
    System.out.println(strList);
    strList.forEach(o -> o += "1");
    System.out.println(strList);
    strList.forEach(o -> {
      o = "ㅜㅜ";
    });
    System.out.println(strList);
  }

  // filter isPresent
  public static void example11() {
    List<Car> carList = getCarList();

    List<Car> colorList = carList.stream()
        .filter(o -> o.getColor().equals("흰색") || o.getColor().equals("빨강"))
        .collect(Collectors.toList());
    colorList.forEach(System.out::println);

    boolean isExists = carList.stream().filter(o -> o.getColor().equals("흰색")).findAny()
        .isPresent();
    System.out.println(isExists);
    isExists = carList.stream().filter(o -> o.getColor().equals("검정")).findAny().isPresent();
    System.out.println(isExists);

  }

  // groupingBy LinkedHashMap LinkedHashMap List
  public static void example10() {
    List<Car> carList = getCarList();
    LinkedHashMap<String, LinkedHashMap<String, List<Integer>>> collect = carList//
        .stream()//
        .collect//
    (//
        Collectors.groupingBy(//
            Car::getColor, //
            LinkedHashMap::new, //
            Collectors.mapping(//
                o -> o,
                Collectors.groupingBy(//
                    Car::getCarName, //
                    LinkedHashMap::new, //
                    Collectors.mapping(//
                        Car::getCarNo, Collectors.toList()//
                    )//
                )//
            )//
        )//
    );
    System.out.println(collect);
  }

  // groupingBy LinkedHashMap
  public static void example9() {
    List<Car> carList = getCarList();
    LinkedHashMap<String, List<Integer>> collect = carList//
        .stream()//
        .collect//
    (//
        Collectors.groupingBy(//
            Car::getColor, //
            LinkedHashMap::new, //
            Collectors.mapping(//
                Car::getCarNo, Collectors.toList()//
            )//
        )//
    );
    System.out.println(collect);
  }

  // groupingBy
  public static void example8() {
    List<Car> carList = getCarList();
    Map<String, List<Integer>> collect = carList//
        .stream()//
        .collect//
    (//
        Collectors.groupingBy(//
            Car::getColor, //
            Collectors.mapping(//
                Car::getCarNo, Collectors.toList()//
            )//
        )//
    );
    System.out.println(collect);
  }

  // set data
  public static void example7() {
    List<Car> carList = getCarList();
    carList.stream().forEach(e -> {
      e.setCarName("바보");
      System.out.println(e);
    });
  }

  // sort
  public static void example6() {
    Comparator<Car> comparator = (e1, e2) -> Integer.compare(e1.getCarNo(), e2.getCarNo());
    List<Car> carList = getCarList();
    System.out.println("---------------------원본");
    carList.stream().forEach(e -> System.out.println(e));
    List<Car> sortedCarList = carList.stream().sorted(comparator).collect(Collectors.toList());
    sortedCarList.get(1).setCarName("새거를 바꿨지롱");
    System.out.println("---------------------정렬한 후 원본");
    carList.forEach(e -> System.out.println(e));
    carList.get(1).setCarName("원본을 바꿨지롱");
    carList.get(1).getAudio().setModelNo(0000);
    System.out.println("---------------------정렬한 후 새거");
    sortedCarList.forEach(e -> System.out.println(e));
    Collections.sort(carList, comparator);//
    System.out.println("---------------------자체 정렬.");
    carList.stream().forEach(e -> System.out.println(e));
  }

  // filter
  public static void example5() {
    List<Car> carList = getCarList();

    List<Car> colorList = carList.stream()
        .filter(o -> o.getColor().equals("흰색") || o.getColor().equals("빨강"))
        .collect(Collectors.toList());
    colorList.forEach(System.out::println);

    Car car = carList.stream().filter(o -> o.getColor().equals("흰색")).findFirst().orElse(null);
    System.out.println(car);

    String color = carList.stream().filter(o -> {
      if (o.getColor().equals("흰색")) {
        return true;
      } else {
        return false;
      }
    }).map(Car::getColor).findFirst().orElse(null);
    System.out.println(color);
  }

  // List to Array
  public static void example4() {
    List<Car> carList = getCarList();

    List<String> colorList = carList.stream().map(o -> o.getColor()).collect(Collectors.toList());
    System.out.println(colorList);

    String[] arr = colorList.stream().toArray(String[]::new);
    System.out.println(arr);
  }

  public static void example3() {
    List<Car> carList = getCarList();
    Map<Integer/* 차번호 */, Map<String/* 차이름 */, Integer/* 속도 */>> carMap = //
        carList//
            .stream()//
            .collect//
        (//
            Collectors.groupingBy(//
                o -> o.getCarNo(), //
                Collectors.toMap(o -> o.getCarName(), o -> o.getSpeed())//
            )//
        );
    System.out.println(carMap);

    Map<String, String> carNameColorMap = carList.stream()
        .collect(Collectors.toMap(o -> o.getCarName(), o -> o.getColor()));
    System.out.println(carNameColorMap);

    Map<Integer, Integer> sumMap = carMap.entrySet().stream().collect(//
        Collectors.toMap( //
            entry -> entry.getKey(), //
            entry -> (//
            entry.getValue().values().stream().mapToInt(Integer::intValue).sum()//
            )//
        )//
    );
    System.out.println(sumMap);

    List<String> colorList = carList.stream().map(o -> o.getColor()).collect(Collectors.toList());
    System.out.println(colorList);

    int sum = carList.stream().filter(o -> o.getSpeed() > 50).mapToInt(o -> o.getSpeed()).sum();
    System.out.println(sum);
  }

  private static List<Car> getCarList() {
    List<Car> carList = new ArrayList<Car>();
    {
      Car car = new Car();
      car.setCarName("차1");
      car.setCarNo(1111);
      car.setColor("흰색");
      car.setSpeed(100);
      Audio audio = new Audio();
      audio.setModelNo(123);
      car.setAudio(audio);
      carList.add(car);
    }
    {
      Car car = new Car();
      car.setCarName("차2");
      car.setCarNo(2222);
      car.setColor("노랑색");
      car.setSpeed(50);
      Audio audio = new Audio();
      audio.setModelNo(456);
      car.setAudio(audio);
      carList.add(car);
    }
    {
      Car car = new Car();
      car.setCarName("차3");
      car.setCarNo(333);
      car.setColor("노랑색");
      car.setSpeed(200);
      Audio audio = new Audio();
      audio.setModelNo(789);
      car.setAudio(audio);
      carList.add(car);
    }
    return carList;
  }

  public static void example2() {
    Menu menu = new Menu("홈", "/");
    menu.addMenu(new Menu("게시판", "/board")//
        .addMenu(new Menu("공지사항", "/board/notice"))//
        .addMenu(new Menu("FAQ", "/board/faq"))//
    );
    List<Menu> subMenuList = new ArrayList<Menu>();
    for (Menu subMenu : menu.getMenuList()) {
      subMenuList.addAll(subMenu.getMenuList());
    }
    System.out.println(subMenuList);

    List<Menu> collect = menu.getMenuList().stream().flatMap(o -> o.getMenuList().stream())
        .collect(Collectors.toList());
    System.out.println(collect);
  }

  public static void example1() {
    List<String> programing = Arrays.asList("Javascript", "C", "C++", "Nodejs", "Java", "Oracle",
        "MariaDB", "PHP", "ASP");

    // 1-2 Java8 에서 필터링 후 카운팅
    long count2 = programing.stream().filter(str -> (str.indexOf("Java") > -1)).count();
    System.out.println("1-2.count2 = " + count2);

    // 1-3 Java8 에서 필터링 후 카운팅을 병렬로 수행
    long count3 = programing.parallelStream().filter(str -> str.indexOf("Java") > -1).count();
    System.out.println("1-3.count3 = " + count3);

    // 3-1. filter
    // filter 메서드의 인자는 Predicate<T>
    // T 타입를 받아서 Boolean 타입을 리턴
    System.out.print("\n3-1.filter(str.length > 3) :");
    programing.stream().filter(str -> (str.length() > 3))
        .forEach(str -> System.out.print("[" + str + "]"));

    // 3-2. map
    // stream에 들어있는 값을 변환하고 싶을때 사용
    // toUpperCase : 문자를 대문자로 변환
    System.out.print("\n3-2.map(toUpperCase) :");
    programing.stream().map(String::toUpperCase).forEach(str -> System.out.print("[" + str + "]"));

    // 3-3. flatMap
    // Stream의 문자열을 나열 :
    // map으로 구현시 [[[C],[+],[+]],[[P],[H],[P]],[[A],[S],[P]]]으로 출력
    // flatMap으로 구현시 [C][+][+][P][H][P][A][S][P]으로 출력
    System.out.print("\n3-3.flatMap() :");
    programing.stream().filter(str -> str.length() == 3).flatMap(str -> {
      List<String> result = new ArrayList<>();
      for (int i = 0; i < str.length(); i++) {
        result.add(str.substring(i, i + 1));
      }
      return result.stream();
    }).forEach(str -> System.out.print("[" + str + "]"));

  }
}
