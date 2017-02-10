package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        test8();
    }

    private static void 부정전방탐색() {
        System.out.println("jenkins?12".replaceFirst("jenkins(?!/)", "jenkins/"));
        System.out.println("jenkins/?12".replaceFirst("jenkins(?!/)", "jenkins/"));
        System.out.println("jenkins/".replaceFirst("jenkins(?!/)", "jenkins/"));
        System.out.println("jenkins".replaceFirst("jenkins(?!/)", "jenkins/"));
    }

    private static void test8() {
        Pattern p = Pattern.compile("\\w+");
        Matcher m = p.matcher("6c9559c94a9d3b6ed4ce2a225f85c21\nsdf");
        if (m.find())
            System.out.println(m.group());
    }

    private static void test7() {
        System.out.println("asdf/df/add/mod".replaceAll(".*/(add|mod|del|get)$", "$1"));
    }

    private static void test6() {
        System.out.println("017-2340-0000".replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3"));
    }

    private static void test5() {
        System.out.println("201-203".replaceAll("^(\\d{3})(\\d{3})$", "$1-$2"));
    }

    private static void test4() {
        System.out.println("201203457".matches("\\d{8}"));
    }

    private static void test1() {
        System.out.println("지급07/26[06:08] 8,000원".matches("지급\\d{2}"));
    }

    private static void test2() {
        String regex = "삼성카드(승인)?\\s*\\r\\n(\\d{2}/\\d{2}) \\d{2}:\\d{2}\\s*\\r\\n([^\\r]*)\\s*\\r\\n([\\d,]+)원.*";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher("삼성카드\r\n09/04 17:08\r\nGS수퍼부평산곡점\r\n17,100원\r\nㄴㅇㄹㅇ\r\nㅇㄴㄹㄴㅇㄹ");
        System.out.println(m.find());
        System.out.println("전체:" + m.group(0));
        System.out.println("1:" + m.group(1));
        System.out.println("2:" + m.group(2));
        System.out.println("3:" + m.group(3));
    }

    private static void test3() {
        String regex = "..(..)..";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher("123456123456");
        System.out.println(m.find());
        System.out.println(m.group(1));
        System.out.println(m.replaceFirst("00"));
    }
}
