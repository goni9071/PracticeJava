package string;

public class SubstringTest {
    public static void main(String[] args) {
        String hi = "hi/hey";
        System.out.println(hi.indexOf("/"));
        System.out.println(hi.substring(0, hi.indexOf("/")));
        System.out.println(hi.substring(2, 2));
        System.out.println("Last Character:"+hi.substring(hi.length() - 1));
        System.out.println(hi.substring(2, 4));
        System.out.println(hi.substring(hi.indexOf("/")));

    }
}
