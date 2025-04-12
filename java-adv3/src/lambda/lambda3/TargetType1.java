package lambda.lambda3;

public class TargetType1 {
    public static void main(String[] args) {

        FunctionA functionA = i -> "value =" + i;
        FunctionB functionB = i -> "value =" + i;

//        FunctionB targetB = functionA;

    }

    @FunctionalInterface
    interface FunctionA {
        String apply(Integer i);
    }

    @FunctionalInterface
    interface FunctionB {
        String apply(Integer i);
    }
}
