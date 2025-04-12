package lambda.lambda3;

public class GenericMain6 {
    public static void main(String[] args) {
        GenericFunction<String,String> upperCase = s -> s.toUpperCase();
        GenericFunction<String, Integer> stringLength = s -> s.length();



    }

    @FunctionalInterface
    interface GenericFunction<T,R> {
        R apply(T s);
    }

}
