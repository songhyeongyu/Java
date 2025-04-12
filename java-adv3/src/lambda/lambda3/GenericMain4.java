package lambda.lambda3;

public class GenericMain4 {
    public static void main(String[] args) {
        GenericFunction<String,String> upperCase = s -> s.toUpperCase();
        
        String result1 = upperCase.apply("hello");
        System.out.println("result1 = " + result1);

        GenericFunction<Integer,Integer> square = n -> n *  n;

        Integer result2 = square.apply(2);
        System.out.println("apply = " + result2);
    }

    @FunctionalInterface
    interface GenericFunction<T,R> {
        R apply(T s);
    }

}
