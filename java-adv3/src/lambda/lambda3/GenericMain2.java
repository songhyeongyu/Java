package lambda.lambda3;

public class GenericMain2 {
    public static void main(String[] args) {
        ObjectFunction upperCase = s ->((String) s).toUpperCase();
        String result1 = (String) upperCase.apply("hello");
        System.out.println("result1 = " + result1);
        ObjectFunction square = n ->(Integer) n * (Integer) n;
        Integer result2 = (Integer)  square.apply(2);
        System.out.println("apply = " + result2);
    }

    @FunctionalInterface
    interface ObjectFunction {
        Object apply(Object s);
    }

}
