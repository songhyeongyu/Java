package lambda.lambda4;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class OperatorMain {

    public static void main(String[] args) {
        Function<Integer, Integer> square1 = x -> x * x;
        UnaryOperator<Integer> square2 = x -> x * x;

        BiFunction<Integer, Integer, Integer> addition = (a, b) -> a + b;
        System.out.println("addition = " + addition.apply(2,3));
        BinaryOperator<Integer> addition2 = (a, b) -> a + b;
        System.out.println("addition2 = " + addition2.apply(1,2));


    }
}
