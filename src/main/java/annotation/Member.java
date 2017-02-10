package annotation;

@Anno(val = "Annotation!!")
public class Member {
    public static void main(String[] args) {
        Member member = new Member();
        System.out.println(member.getClass().getAnnotation(Anno.class).val());
        System.out.println(member.getClass().getAnnotation(Anno.class).defaultVal());
        System.out.println("".getClass().getAnnotation(Anno.class));

    }
}
