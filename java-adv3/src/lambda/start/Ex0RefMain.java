package lambda.start;

public class Ex0RefMain {


    public static void main(String[] args) {
        helloFunc("Spring");
        helloFunc("Java");
    }


    public static void helloFunc(String name) {
        System.out.println("프로그램 시작");
        System.out.println("Hello " + name);
        System.out.println("프로그램 종료");
    }
}
