package lambda.lambda3;

public class GenericMain1 {
    public static void main(String[] args) {
        StringFunction upperCase = s ->s.toUpperCase();
        String result1 = upperCase.apply("hello");

        System.out.println("result1 = " + result1);

        NumberFunction square = s -> s * s;

        Integer result2 = square.apply(2);
        System.out.println("apply = " + result2);


    }

    @FunctionalInterface
    interface StringFunction {
        String apply(String s);
    }

    @FunctionalInterface
    interface NumberFunction{
        Integer apply(Integer s);
    }
}
